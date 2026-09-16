UPDATE projects
SET title = REPLACE(title, '한라대학교', 'H 대학교'),
    description = REPLACE(description, '한라대학교', 'H 대학교')
WHERE title LIKE '%한라대학교%' OR description LIKE '%한라대학교%';

UPDATE careers
SET description = REPLACE(description, '한라대학교', 'H 대학교')
WHERE description LIKE '%한라대학교%';
