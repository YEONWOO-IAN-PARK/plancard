INSERT INTO cards (title, description, city_id, category_id, rating, is_active)

SELECT 	CONCAT('[CARD] ', name) as title,
        CONCAT('This is description for ', name) as description,
        id as city_id,
        FLOOR(1 + (RAND() * 10)) as category_id,  -- 1~10 랜덤
        FLOOR(1 + (RAND() * 5)) as rating,         -- 1~5 랜덤
        TRUE as is_active
FROM 	cities
WHERE name LIKE '%Paris%'
   OR name LIKE '%Marseille%'
   OR name LIKE '%Lyon%'
   OR name LIKE '%Toulouse%'
   OR name LIKE '%Nice%'
   OR name LIKE '%Nantes%'
   OR name LIKE '%Strasbourg%'
   OR name LIKE '%Montpellier%'
   OR name LIKE '%Bordeaux%'
   OR name LIKE '%Lille%'

   OR name LIKE '%Seoul%'
   OR name LIKE '%Busan%'
   OR name LIKE '%Incheon%'
   OR name LIKE '%Daegu%'
   OR name LIKE '%Daejeon%'
   OR name LIKE '%Gwangju%'
   OR name LIKE '%Ulsan%'
   OR name LIKE '%Suwon%'
   OR name LIKE '%Changwon%'
   OR name LIKE '%Cheongju%'

   OR name LIKE '%New York%'
   OR name LIKE '%Los Angeles%'
   OR name LIKE '%Chicago%'
   OR name LIKE '%Houston%'
   OR name LIKE '%Phoenix%'
   OR name LIKE '%Philadelphia%'
   OR name LIKE '%San Antonio%'
   OR name LIKE '%San Diego%'
   OR name LIKE '%Dallas%'
   OR name LIKE '%San Jose%'

   OR name LIKE '%Beijing%'
   OR name LIKE '%Shanghai%'
   OR name LIKE '%Guangzhou%'
   OR name LIKE '%Shenzhen%'
   OR name LIKE '%Chengdu%'
   OR name LIKE '%Hangzhou%'
   OR name LIKE '%Chongqing%'
   OR name LIKE '%Wuhan%'
   OR name LIKE '%Xian%'
   OR name LIKE '%Tianjin%';
