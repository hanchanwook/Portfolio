# Portfolio backend

Java 21 타깃, Spring Boot 4.1.1, Spring Data JPA, MySQL 8.4, Maven Wrapper 기반입니다. 로컬에 설치된 JDK 26으로도 빌드할 수 있습니다.

## 구조

```text
src/main/java/com/chanuk/portfolio/
├── project/  (controller, service, repository, entity, dto)
├── career/   (controller, service, repository, entity, dto)
├── skill/    (controller, service, repository, entity, dto)
└── common/   (config, exception)
```

Controller는 입력 검증과 HTTP 응답, Service는 트랜잭션과 업무 처리, Repository는 DB 접근을 담당합니다. Entity를 직접 응답하지 않고 Request/Response DTO를 사용합니다. 업데이트는 트랜잭션 내 JPA 변경 감지를 사용합니다.

## MySQL 최초 설정

MySQL에 관리자 계정으로 로그인한 뒤 아래 SQL을 실행합니다. 예시 비밀번호를 실제 비밀번호로 바꿔 사용하고 Git에 저장하지 마세요.

```sql
CREATE DATABASE IF NOT EXISTS portfolio CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'portfolio_app'@'localhost' IDENTIFIED BY '여기에_직접_설정할_비밀번호';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, INDEX, REFERENCES
ON portfolio.* TO 'portfolio_app'@'localhost';
```

이미 계정이 있다면 기존 계정을 사용하세요. `application-local.properties.example`을 `application-local.properties`로 복사해 DB 계정과 비밀번호를 로컬에서 입력합니다. 또는 `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` 환경 변수를 사용합니다. 설정 파일은 backend 폴더에서 실행할 때 읽으며 Git 제외 대상입니다.

## 실행

```powershell
cd C:\workspace\Portfolio\backend
$env:JAVA_HOME = 'C:\Users\hanch\.jdks\openjdk-26.0.2.1'
.\mvnw.cmd spring-boot:run
```

IntelliJ에서는 `backend/pom.xml`을 Maven 프로젝트로 열고 SDK를 JDK 21 이상(현재 로컬 JDK 26)으로, 실행 작업 디렉터리를 `backend`로 설정한 뒤 `PortfolioBackendApplication`을 실행합니다.

서버 주소는 `http://localhost:8080`입니다. 최초 기동 때 Flyway가 테이블과 확인된 포트폴리오 기초 데이터를 생성합니다. 재시작할 때 같은 데이터를 다시 삽입하지 않습니다. JPA는 스키마를 검증만 하며 자동 삭제·재생성하지 않습니다.

## API

리소스는 `projects`, `careers`, `skills`입니다. 목록은 `displayOrder`, `id` 순으로 정렬합니다.

| 메서드 | 경로 | 결과 |
|---|---|---|
| GET | `/api/{리소스}` | 전체 목록, 200 |
| GET | `/api/{리소스}/{id}` | 단건 조회, 200 / 404 |
| POST | `/api/{리소스}` | 등록, 201 + Location |
| PUT | `/api/{리소스}/{id}` | 전체 필드 수정, 200 / 404 |
| DELETE | `/api/{리소스}/{id}` | 삭제, 204 / 404 |

조회는 공개입니다. 쓰기는 32자 이상 무작위 `ADMIN_API_KEY` 환경 변수 또는 `portfolio.admin-key` 로컬 설정과 일치하는 `X-Admin-Key` 헤더가 있어야 합니다. 미설정 시 쓰기는 모두 차단됩니다. 키는 React 코드나 VITE 환경 변수에 넣지 마세요. 현재는 로컬 API 관리용 인증이며 사용자 로그인·관리자 UI는 구현 전입니다. 외부 운영 시 HTTPS를 사용해야 합니다.

프로젝트 요청 예시:

```json
{
  "title": "S-IN",
  "category": "FREELANCE",
  "description": "실제 프로젝트 소개",
  "role": "직접 담당한 역할",
  "period": "2026",
  "pending": true,
  "displayOrder": 1
}
```

프로젝트 category: `COMPANY`, `FREELANCE`, `TEAM`. 경력 요청: `company`, `role`, `period`, `description`, `current`, `displayOrder`. 기술 요청: `name`, `category`, `displayOrder`이며 category는 `BACKEND`, `FRONTEND`, `DATABASE`, `TOOLS`입니다. 검증 실패는 400과 필드별 `errors`, 없는 데이터는 404 ProblemDetail을 반환합니다.

CORS는 기본적으로 `localhost:3000`, `127.0.0.1:3000`만 허용하며 `CORS_ORIGINS`로 변경할 수 있습니다. React의 프로젝트·경력·기술 목록은 조회 API와 연결되어 있습니다. 자기소개와 이력서 역량 설명 등은 프론트엔드 로컬 콘텐츠를 사용합니다. DB 변경 후 화면을 새로고침하면 조회 결과가 반영됩니다.

## 검증

```powershell
.\mvnw.cmd verify
```

테스트는 테스트 전용 H2의 MySQL 호환 모드에서 Flyway 마이그레이션, HTTP CRUD, 재조회에 따른 저장 확인, 인증, 입력 검증, CORS를 검사합니다. 로컬 MySQL 비밀번호를 사용하거나 실제 DB 데이터를 수정하지 않습니다. 이 검증이 실제 MySQL 연결 검증을 대신하지는 않습니다.
