# 🧠 Wider Server

> **Wider**는 AI가 질문을 던지고 사용자가 사고하며 성장하는, "생각의 흐름 아카이빙 플랫폼"입니다.

---

## 🏗️ 프로젝트 구성
WIDER-Server/
├── spring-backend/       # Spring Boot 서비스 (사용자 관리, 세션, 통계, 리포트 등)
├── chatbot-api/          # FastAPI 챗봇 서버 (LLM 기반 질문 생성 및 응답 평가)
├── docker-compose.yaml    # 전체 서버 구동용 도커 설정
└── README.md             # 현재 문서

---

## 🧠 주요 기능

| 모듈 | 역할 |
|------|------|
| ✅ FastAPI 챗봇 서버 | GPT 기반 Bloom 단계 질문 생성, 사용자 응답 평가, 힌트 제시 |
| ✅ Spring Boot 서버 | 사용자/세션 관리, 사고 흐름 저장, 리포트 생성, 통계 집계 |
| ✅ Docker MySQL DB | 모든 사용자/대화/리포트/통계 데이터 저장소 |
| ✅ LangChain + GPT-4o | AI 질문 생성 및 응답 분석용 모델 연동 |

---

## ⚙️ 기술 스택

- **Spring Boot 3.3.1**
- **FastAPI (Python 3.10)**
- **MySQL 8.0**
- **LangChain + OpenAI GPT API**
- **Docker**
