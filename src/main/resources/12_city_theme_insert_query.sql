INSERT IGNORE INTO city_theme (city_id, theme_id)
SELECT
    city_id,
    FLOOR(1 + RAND() * 22) AS theme_id
FROM (
    SELECT c.id AS city_id
    FROM cities c
    ORDER BY RAND()
    LIMIT 20000
) AS random_cities
JOIN (
    SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3
) AS repeat3;