# 🧠 Wider Server

> **Wider**는 AI가 질문을 던지고 사용자가 사고하며 성장하는, "생각의 흐름 아카이빙 플랫폼"입니다.

---

## 🏗️ 프로젝트 구성
```
WIDER-Server/
├── wider/                            # Spring Boot 백엔드 (Java)
│   ├── controller/                   # REST API 컨트롤러
│   ├── config/                       # 전역 설정 (CORS, Swagger, Security 등)
│   └── model/
│       ├── entity/                   # JPA Entity 클래스
│       ├── dto/                      # 요청/응답 DTO 클래스
│       ├── dao/                      # DAO 인터페이스
│       ├── repository/               # JPA Repository
│       └── service/                  # 비즈니스 로직 서비스
│
├── chatbot-api/                      # FastAPI 챗봇 서버 (Python)
│   ├── src/                          # main.py 및 라우터, 평가 로직 등
│   ├── prompts/                      # 프롬프트 템플릿 (YAML)
│   └── data/                         # 대화 로그, 캐시, 저장용 데이터 등
│
├── docker-compose.yaml               # 전체 백엔드 통합 실행을 위한 도커 컴포즈
└── README.md                         # 프로젝트 문서
```
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
