from fastapi import FastAPI, HTTPException, Depends, Header
from pydantic import BaseModel
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain.memory import ConversationBufferWindowMemory
import mysql.connector
from contextlib import contextmanager
from dotenv import load_dotenv
import os
import uuid
import json
from datetime import date
import jwt
from typing import Optional
from . import crawler

# 환경 설정
load_dotenv()
openai_api_key = os.getenv("OPENAI_API_KEY")
jwt_secret = os.getenv("JWT_SECRET")
mysql_config = {
    'host': os.getenv("MYSQL_HOST"),
    'user': os.getenv("MYSQL_USER"),
    'password': os.getenv("MYSQL_PASSWORD"),
    'database': os.getenv("MYSQL_DATABASE")
}

# FastAPI 앱
app = FastAPI()

# JWT 검증 함수
async def verify_token(authorization: str = Header(...)) -> str:
    try:
        if not authorization.startswith("Bearer "):
            raise HTTPException(status_code=401, detail="Invalid authorization header")
            
        token = authorization.split(" ")[1]
        payload = jwt.decode(token, jwt_secret, algorithms=["HS256"])
        return payload["sub"]  # user_id 반환
    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=401, detail="Token has expired")
    except jwt.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Invalid token")
    except Exception as e:
        raise HTTPException(status_code=401, detail=str(e))

# DB 설정
@contextmanager
def get_db():
    conn = mysql.connector.connect(**mysql_config)
    try:
        yield conn
    finally:
        conn.close()

# DB 헬퍼
def create_session(session_id: str, topic: str, user_id: str):
    with get_db() as conn:
        cursor = conn.cursor()
        cursor.execute(
            """
            INSERT INTO session_logs (
                session_id, 
                started_at, 
                completed, 
                topic,
                user_id
            )
            VALUES (%s, NOW(), 0, %s, %s)
            """,
            (session_id, topic, user_id)
        )
        conn.commit()

def mark_session_completed(session_id: str):
    with get_db() as conn:
        cursor = conn.cursor()
        cursor.execute(
            """
            UPDATE session_logs
            SET completed = 1, completed_at = NOW()
            WHERE session_id = %s
            """,
            (session_id,)
        )
        conn.commit()

def get_current_question(session_id: str) -> dict:
    with get_db() as conn:
        cursor = conn.cursor(dictionary=True)
        cursor.execute(
            """
            SELECT topic, question, bloom_level, is_answered
            FROM questions
            WHERE session_id = %s AND is_answered = 0
            ORDER BY bloom_level ASC LIMIT 1
            """,
            (session_id,)
        )
        row = cursor.fetchone()
        if row:
            return row
        return None

def save_question(session_id: str, topic: str, question: str, bloom_level: int):
    with get_db() as conn:
        cursor = conn.cursor()
        cursor.execute(
            """
            INSERT INTO questions (session_id, topic, question, bloom_level, is_answered)
            VALUES (%s, %s, %s, %s, 0)
            """,
            (session_id, topic, question, bloom_level)
        )
        conn.commit()

def mark_answered(session_id: str, bloom_level: int, user_answer: str):
    with get_db() as conn:
        cursor = conn.cursor()
        cursor.execute(
            """
            UPDATE questions
            SET is_answered = 1, user_answer = %s
            WHERE session_id = %s AND bloom_level = %s
            """,
            (user_answer, session_id, bloom_level)
        )
        conn.commit()

def get_daily_topic() -> dict:
    today = str(date.today())
    with get_db() as conn:
        cursor = conn.cursor(dictionary=True)
        cursor.execute(
            """
            SELECT topic, topic_prompt
            FROM daily_topic
            WHERE topic_date = %s
            """,
            (today,)
        )
        row = cursor.fetchone()
        if row:
            return row
        return None

def set_daily_topic():
    topic_json = crawler.main()
    topic = topic_json["topic"]
    topic_prompt = topic_json["topic_prompt"]
    today = str(date.today())
    with get_db() as conn:
        cursor = conn.cursor()
        cursor.execute(
            """
            INSERT INTO daily_topic (topic_date, topic, topic_prompt)
            VALUES (%s, %s, %s)
            ON DUPLICATE KEY UPDATE
            topic = VALUES(topic),
            topic_prompt = VALUES(topic_prompt)
            """,
            (today, topic, topic_prompt)
        )
        conn.commit()
    question = question_chain.invoke({"topic": topic, "bloom_level": 1}).content
    return {"topic": topic, "topic_prompt": topic_prompt, "bloom1_question": question}

# LLM 설정
llm1 = ChatOpenAI(model="gpt-4o-mini", openai_api_key=openai_api_key)
llm2 = ChatOpenAI(model="gpt-4o-mini", openai_api_key=openai_api_key)

# 질문 생성 프롬프트
question_prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            """
        당신은 Bloom's Taxonomy(기억, 이해, 적용, 분석, 평가, 창조)에 기반한 질문을 생성하는 전문가입니다.
        주어진 주제({topic})와 Bloom 단계({bloom_level})에 맞는 질문을 하나만 생성하세요.
        질문은 간결하고 명확하며, 해당 단계의 인지적 요구를 정확히 반영해야 합니다.
        - 1단계: Remember
        - 사고 수준: 낮음
        - 설명: 정보를 기억하거나 회상
        - 예시 질문: 정부가 발표한 AI 정책의 핵심은 무엇인가요?
        - 2단계: Understand
            - 사고 수준: 낮음 ~ 중간
            - 설명: 정보를 해석, 요약, 설명
            - 예시 질문: 이 정책이 왜 필요한지 설명해보세요.
        - 3단계: Apply
            - 사고 수준: 중간
            - 설명: 배운 지식을 실제 문제에 적용
            - 예시 질문: 이 정책을 학교 교육 현장에 어떻게 적용할 수 있을까요?
        - 4단계: Analyze
            - 사고 수준: 중간 ~ 높음
            - 설명: 정보를 분해, 관계 파악
            - 예시 질문: 이 정책의 각 조항은 어떤 목적을 갖고 있나요?
        - 5단계: Evaluate
            - 사고 수준: 높음
            - 설명: 주장을 비판적 판단, 가치 평가
            - 예시 질문: 이 정책이 사회에 미칠 영향을 어떻게 평가하나요?
        - 6단계: Create
            - 사고 수준: 최고
            - 설명: 새로운 아이디어, 구조, 해법 창출
            - 예시 질문: 더 나은 AI 정책을 설계해 본다면 어떻게 하시겠어요?
        """,
        ),
        ("user", "주제: {topic}, Bloom 단계: {bloom_level}"),
    ]
)

question_chain = question_prompt | llm1

# 응답 평가 프롬프트
eval_prompt = ChatPromptTemplate.from_messages(
    [
        (
            "system",
            """
        당신은 사용자의 응답이 질문의 의도에 부합하는지 체크하고, 응답이 부족하다면 피드백을 제공합니다.
        만약 사용자가 잘 모르는 내용이라 도움을 요청한다면, 답변을 제공하진 말고 사용자의 답변을 유도할 수 있도록 힌트를 제공해주세요.
        
        주어진 질문({question})은 Bloom 단계({bloom_level})에 해당합니다.
        사용자의 응답({user_answer})을 분석해 다음을 판단하세요:
        1. 응답이 질문의 의도에 부합하는가?
        2. 응답이 충분히 구체적인가?
        3. 사용자가 도움을 요청했는가?
        결과는 JSON으로 반환하세요:
        {{
            "is_appropriate": true/false,
            "feedback": "응답이 적절한 이유 또는 부족한 점"
            "is_looking_for_help": true/false,
            "hint": "사용자의 답변을 이끌어내기 위한 힌트"
        }}
        (단, is_appropriate와 is_looking_for_help가 동시에 true가 될 수 없음을 명심하세요.)
        """,
        ),
        (
            "user",
            """
        질문: {question}
        Bloom 단계: {bloom_level}
        사용자 응답: {user_answer}
        """,
        ),
    ]
)

eval_chain = eval_prompt | llm2

# FastAPI 엔드포인트
class UserInput(BaseModel):
    sessionId: Optional[str] = None
    userAnswer: Optional[str] = None

@app.on_event("startup")
async def startup_event():
    set_daily_topic()

@app.post("/chat")
async def chat(user_input: UserInput, user_id: str = Depends(verify_token)):
    # 세션 ID 초기화
    session_id = user_input.sessionId or str(uuid.uuid4())
    
    # 오늘의 주제 가져오기
    daily_topic = get_daily_topic()
    if not daily_topic:
        raise HTTPException(
            status_code=500, detail="오늘의 주제가 설정되지 않았습니다."
        )

    topic = daily_topic["topic"]
    topic_prompt = daily_topic["topic_prompt"]

    # 새 세션인 경우 session_logs에 먼저 생성
    if not user_input.sessionId:
        create_session(session_id, topic, user_id)

    # 현재 질문 확인
    current_question = get_current_question(session_id)

    # 첫 대화: Bloom 1단계 질문 제공
    if not current_question:
        question = question_chain.invoke({"topic": topic, "bloom_level": 1}).content
        save_question(session_id, topic, question, 1)
        return {
            "sessionId": session_id,
            "topicPrompt": topic_prompt,
            "question": question,
            "message": "자, 시작해볼까요?",
        }

    # 사용자 응답 처리
    if user_input.userAnswer and current_question:
        evaluation = json.loads(
            eval_chain.invoke(
                {
                    "question": current_question["question"],
                    "bloom_level": current_question["bloom_level"],
                    "user_answer": user_input.userAnswer,
                }
            ).content
        )

        if evaluation["is_appropriate"]:
            # 답변 저장 및 다음 질문 진행
            mark_answered(
                session_id, current_question["bloom_level"], user_input.userAnswer
            )
            next_level = current_question["bloom_level"] + 1
            if next_level <= 6:
                next_question = question_chain.invoke(
                    {"topic": topic, "bloom_level": next_level}
                ).content
                save_question(session_id, topic, next_question, next_level)
                return {
                    "sessionId": session_id,
                    "topicPrompt": topic_prompt,
                    "question": next_question,
                    "message": "잘했어요, 다음 질문으로 넘어갈게요.",
                }
            else:
                mark_session_completed(session_id)
                return {
                    "sessionId": session_id,
                    "topicPrompt": topic_prompt,
                    "message": "오늘의 질문이 모두 끝났어요! 내일 새로운 주제로 만나요.",
                }
        else:
            # 힌트 제공
            if evaluation["is_looking_for_help"]:
                return {
                    "sessionId": session_id,
                    "topicPrompt": topic_prompt,
                    "question": current_question["question"],
                    "message": f"{evaluation['hint']}",
                }
            # 추가 유도
            else:
                return {
                    "sessionId": session_id,
                    "topicPrompt": topic_prompt,
                    "question": current_question["question"],
                    "message": f"조금 더 생각해볼까요? {evaluation['feedback']}",
                }

    raise HTTPException(status_code=400, detail="잘못된 요청입니다.") 