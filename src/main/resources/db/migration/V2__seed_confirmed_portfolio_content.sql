INSERT INTO projects (title, category, description, role, period, pending, display_order) VALUES
('핸드소프트', 'COMPANY', '현재 재직 중인 회사에서의 개발 경험입니다. 상세 내용은 추후 추가할 예정입니다.', '연구원 · 소프트웨어 개발', '2025.10 — 현재', TRUE, 0),
('S-IN', 'FREELANCE', '외주로 참여한 S-IN 프로젝트입니다. 상세 내용은 추후 추가할 예정입니다.', '외주 프로젝트', NULL, TRUE, 1),
('학원 팀 프로젝트', 'TEAM', '한국ICT인재개발원 Java 풀스택 교육 과정에서 진행한 팀 프로젝트입니다.', 'Java 풀스택 교육 과정', NULL, TRUE, 2);
INSERT INTO careers (company, role, period, description, is_current, display_order) VALUES
('핸드소프트', '연구원 · 소프트웨어 개발', '2025.10 — 현재', '소프트웨어 개발 업무를 담당하고 있습니다.', TRUE, 0),
('우성돈까스', '지점장 · 매장 운영', '2021.10 — 2024.12', '매장 운영 전반 및 고객 응대', FALSE, 1),
('제이스텍', '제조기술부 · 자동화 장비', '2012.11 — 2021.06', '자동화 장비 제작, 현장 운용 교육, 고객 대응 및 협력사 관리', FALSE, 2);
INSERT INTO skills (name, category, display_order) VALUES
('Java', 'BACKEND', 0), ('Spring Boot', 'BACKEND', 1), ('Spring Framework', 'BACKEND', 2), ('MyBatis', 'BACKEND', 3), ('JSP / Servlet', 'BACKEND', 4),
('JavaScript', 'FRONTEND', 5), ('React', 'FRONTEND', 6), ('Next.js', 'FRONTEND', 7), ('HTML / CSS', 'FRONTEND', 8), ('jQuery / Ajax', 'FRONTEND', 9),
('MySQL', 'DATABASE', 10), ('Oracle', 'DATABASE', 11), ('SQL', 'DATABASE', 12), ('DB 모델링', 'DATABASE', 13),
('AWS EC2', 'TOOLS', 14), ('Docker', 'TOOLS', 15), ('Kubernetes', 'TOOLS', 16), ('Git / GitHub', 'TOOLS', 17), ('Figma', 'TOOLS', 18), ('ERD Cloud', 'TOOLS', 19);
