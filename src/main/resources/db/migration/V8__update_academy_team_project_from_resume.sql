-- Source: 이력서_20260909.pdf (education, awards, and project experience).
-- The resume gives the education period, not individual project dates.
UPDATE projects
SET role = '한국ICT인재개발원 · Java 풀스택 교육 과정 팀 프로젝트',
    period = '교육 기간: 2025.01 ~ 2025.08',
    description = '한국ICT인재개발원의 「무중단 서비스를 위한 클라우드기반 AI 활용 자바 풀스택 개발자 과정」에서 진행한 팀 프로젝트입니다. 프로젝트 수행 과정에서 JSP 화면 구축과 서버 연동을 경험하고, jQuery를 활용한 화면 효과, JavaScript 이벤트 처리, Ajax를 통한 비동기 처리를 구현했습니다. 교육 기간 중 진행한 팀 프로젝트에서 우수팀으로 선정되어 2025년 프로젝트 우수상을 수상했습니다.',
    pending = FALSE
WHERE title = '학원 팀 프로젝트' AND category = 'TEAM';
