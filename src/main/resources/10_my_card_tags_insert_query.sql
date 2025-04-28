INSERT INTO my_card_tags (my_card_id, name)
(
SELECT	id
		, '[TAG] Sample Tag - 1'
FROM	my_cards
WHERE	is_active = true
) ;

COMMIT;

INSERT INTO my_card_tags (my_card_id, name)
(
SELECT	id
		, '[TAG] Sample Tag - 2'
FROM	my_cards
WHERE	is_active = true
) ;

COMMIT;