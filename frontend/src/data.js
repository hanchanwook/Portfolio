export const profile = {
  name: '한찬욱', email: 'cksdnr106@naver.com',
  introduction: '자동화 장비 제작과 해외 현장에서 고객 대응, 협력사 관리, PL·PM 역할을 경험했습니다. 매장 운영을 통해서는 사용자의 요구와 서비스의 가치를 가까이에서 배웠습니다. 이제는 그 경험을 바탕으로 화면부터 서버까지 구현하는 풀스택 개발자로 성장하고 있습니다.',
};
// Section labels; technology names are maintained in skills.js.
export const skillGroups = [
  { category: 'BACKEND', icon: '⌘', title: 'Backend', description: '서비스의 흐름을 설계하고 구현합니다.' },
  { category: 'FRONTEND', icon: '</>', title: 'Frontend', description: '사용자와 만나는 화면을 만듭니다.' },
  { category: 'DATABASE', icon: '▤', title: 'Database', description: '데이터의 구조와 관계를 이해합니다.' },
  { category: 'TOOLS', icon: '↗', title: 'Infra & Tools', description: '개발부터 배포까지 경험을 넓힙니다.' },
];
// 실제 사례만 추가합니다. 배열이 비어 있으면 준비 중 안내를 표시합니다.
export const troubleshooting = [];
export const awards = ['프로젝트 우수상', 'SW인재상', 'SW혁신상', '특모범상'];
export const resumeStrengths = [
  { title: '현장에서 배운 협업', label: 'PL · PM EXPERIENCE', description: '자동화 장비 제작 회사의 해외 현장에서 PL·PM 역할을 맡아 자사 인원과 협력사를 관리했습니다. 프로그램 제어팀과 소통하며 장비 유지보수와 개조 작업을 함께 수행했습니다.' },
  { title: '고객과 운영을 이해하는 시선', label: 'CUSTOMER & OPERATIONS', description: '매장을 운영하며 다양한 고객을 응대하고, 재료와 소모품 등 자원을 관리했습니다. 고객의 요구를 듣는 태도와 작은 자원도 책임 있게 다루는 운영 관점을 배웠습니다.' },
  { title: '개발자로 이어진 도전', label: 'WHY DEVELOPMENT', description: '장비 회사에서 함께 일하던 프로그램 개발팀을 보며 개발에 관심을 갖게 됐습니다. 이후 Java 풀스택 교육 과정에 참여하고, 수업 이후와 주말에도 학습을 이어가며 개발자로 진로를 전환했습니다.' },
];
export const capabilities = [
  { title: '웹 서비스와 서버 연동', items: ['Spring Boot와 MVC 패턴을 활용한 웹 애플리케이션 구현', 'Java · JSP · Servlet 기반 클래스 설계와 Session을 이용한 로그인 처리', 'MyBatis를 활용한 DB 연동 및 데이터 조회·등록·수정·삭제'], tags: ['Java', 'Spring Boot', 'MyBatis', 'Session'] },
  { title: '데이터 모델링과 SQL', items: ['테이블 구조 설계와 정규화, PK·FK 제약조건을 고려한 데이터 모델링', 'JOIN과 서브쿼리를 활용한 데이터 조회 및 CRUD 쿼리 작성', '뷰와 인덱스 활용, ERD Cloud를 이용한 DB 설계'], tags: ['MySQL', 'SQL', 'ERD Cloud'] },
  { title: '화면 구현과 데이터 활용', items: ['JavaScript 이벤트 처리와 Ajax를 활용한 비동기 데이터 연동', 'CSS와 Bootstrap을 활용한 반응형 화면, jQuery 효과 구현', 'Jsoup 웹 크롤링과 Python Pandas·NumPy를 활용한 기초 데이터 분석·전처리'], tags: ['JavaScript', 'Ajax', 'Bootstrap', 'Jsoup', 'Python'] },
  { title: '협업 도구와 배포 환경', items: ['Git · GitHub · SourceTree를 활용한 브랜치와 버전 관리', 'Figma · ERD Cloud · draw.io를 활용한 UI와 기능 구조 설계', 'Docker 컨테이너화와 Kubernetes 배포·운영 자동화, 스케일링·로드밸런싱 학습'], tags: ['Git', 'SourceTree', 'Figma', 'Docker', 'Kubernetes'] },
];
export const overseasExperience = {
  period: '2020.07 — 2021.03 · 9개월', location: '중국 천진 · 우한',
  title: '장비 설치부터 양산 대응까지',
  description: '해외 파견 현장에서 자동화 장비 설치와 시운전을 진행하고, 양산 대응 및 A/S 작업을 수행했습니다. 현장 운용 교육과 고객 대응 경험을 쌓았습니다.',
};
export const certificates = [
  { title: '전자기능사', organization: '한국산업인력공단', date: '2012.07' },
  { title: '전자계산기기능사', organization: '한국산업인력공단', date: '2011.07' },
  { title: 'ITQ 아래한글 B등급', organization: '한국생산성본부', date: '2012.05' },
  { title: 'ITQ 한글파워포인트 A등급', organization: '한국생산성본부', date: '2011.10' },
];
