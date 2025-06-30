INSERT INTO card_images (
    id, card_id, save_path, original_file_name, stored_file_name,
    extension, size, alt, is_main, is_active
)

SELECT	main_image_id AS id
		, id AS card_id
        , CONCAT('D:\\plancard\\upload\\image\\card\\', main_image_id) AS save_path
        , CONCAT('sample_', main_image_id) AS original_file_name
        , CONCAT('uuid_', main_image_id) AS stored_file_nmae
        , 'webp' AS extension
        , 1024 AS size
        , CONCAT('sample_alt_', main_image_id) AS alt
        , true AS is_main
        , true AS is_active
FROM 	cards
;