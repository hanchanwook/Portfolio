CREATE TABLE projects (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    category VARCHAR(20) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    role VARCHAR(200) NOT NULL,
    period VARCHAR(80),
    pending BOOLEAN NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT chk_project_category CHECK (category IN ('COMPANY', 'FREELANCE', 'TEAM')),
    CONSTRAINT chk_project_order CHECK (display_order >= 0)
);
CREATE TABLE careers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company VARCHAR(120) NOT NULL,
    role VARCHAR(200) NOT NULL,
    period VARCHAR(80) NOT NULL,
    description VARCHAR(5000) NOT NULL,
    is_current BOOLEAN NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT chk_career_order CHECK (display_order >= 0)
);
CREATE TABLE skills (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    category VARCHAR(20) NOT NULL,
    display_order INT NOT NULL,
    CONSTRAINT chk_skill_category CHECK (category IN ('BACKEND', 'FRONTEND', 'DATABASE', 'TOOLS')),
    CONSTRAINT chk_skill_order CHECK (display_order >= 0)
);
