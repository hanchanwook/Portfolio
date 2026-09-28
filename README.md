# 한찬욱 포트폴리오

React + JavaScript(JSX) + Vite로 만든 정적 포트폴리오입니다. Node.js는 개발·빌드 도구로만 사용하며, 사이트 운영에는 Java 서버나 MySQL이 필요하지 않습니다.

## 실행

Node.js 설치 후 Git Bash에서 실행합니다.

```bash
cd /c/workspace/Portfolio/frontend
npm install
npm run dev
```

브라우저에서 터미널에 표시된 주소(기본 http://localhost:3000)를 엽니다. PowerShell 실행 정책 오류가 나면 `npm.cmd`를 사용하세요.

## 내용 수정

| 파일 | 내용 |
| --- | --- |
| `frontend/src/data.js` | 자기소개, 수상·자격증, 역량, 해외 경험, 문제 해결 사례 |
| `frontend/src/projects.js` | 모든 프로젝트(실무·외주·학원 팀·ERP)의 제목·설명·담당 기능·기술·링크 |
| `frontend/src/careers.js` | 경력 |
| `frontend/src/skills.js` | 기술 목록 |
| `frontend/src/main.jsx` | 페이지 구성, 메뉴, 이메일 복사 |
| `frontend/src/styles.css` | 디자인과 반응형 레이아웃 |

내용은 JavaScript 파일에서 수정합니다. 프로젝트의 `category`는 `COMPANY`, `FREELANCE`, `TEAM`이며 배열 순서대로 표시됩니다. `period`는 기간, `role`은 역할, `description`은 설명입니다. 기존 DB의 프로젝트 3개·경력 3개·기술 20개를 옮겼으며 ERP 카드와 이력서 내용도 유지했습니다.

Java·Spring·MySQL 등의 기술명은 실제 경력과 프로젝트 경험을 설명하는 콘텐츠입니다.

## 검증과 배포

`frontend` 폴더에서 실행합니다.

```bash
npm run check
npm run build
npm run preview
```

`check`는 데이터·설정 JavaScript 문법을 검사하고, `build`는 React JSX를 포함한 앱 전체를 빌드합니다. `preview`로 빌드 결과를 확인합니다.

배포 대상은 `frontend/dist/`입니다. GitHub Pages 등 정적 호스팅을 사용할 수 있고, 상대 경로 설정으로 저장소 하위 경로도 지원합니다. 콘텐츠 수정 후 다시 빌드·배포하세요. API 주소, DB 계정, 관리자 키 설정은 필요하지 않습니다.

원본 이력서와 설치 파일은 배포 파일에 포함되지 않습니다. 실제 원격 배포는 별도로 진행합니다.

### GitHub Pages 배포

1. GitHub 저장소의 `Settings → Pages → Build and deployment → Source`에서 `GitHub Actions`를 선택합니다.
2. `.github/workflows/pages.yml`을 포함한 코드를 `master` 또는 `main` 브랜치에 푸시합니다.
3. 저장소의 `Actions`에서 `Deploy portfolio to GitHub Pages` 실행 결과를 확인합니다. 성공하면 배포 작업에 사이트 주소가 표시됩니다.

이후 해당 브랜치에 푸시할 때마다 의존성 설치와 빌드를 수행하고 `frontend/dist`를 배포합니다. `dist`를 직접 커밋할 필요는 없습니다.
