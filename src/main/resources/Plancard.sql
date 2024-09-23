CREATE TABLE `cards` (
  `id` bigint PRIMARY KEY COMMENT '카드(지역) 고유번호',
  `title` varchar(255) NOT NULL COMMENT '카드(지역)명',
  `description` varchar(255) COMMENT '설명',
  `city_id` varchar(255) NOT NULL COMMENT '도시 고유번호. 카드와 도시는 N : 1 관계이다.',
  `category_id` bigint COMMENT '카테고리 고유번호 (관광지, 유적지, ...)',
  `rating` tinyint COMMENT '별점(1점 단위)',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '생성일',
  `updated_date` date COMMENT '최종 수정일'
) COMMENT = '';

CREATE TABLE `cities` (
  `id` varchar(255) PRIMARY KEY COMMENT '도시 고유번호',
  `name` varchar(255) NOT NULL COMMENT '도시명',
  `name_ascii` varchar(255) COMMENT '도시명 ASCII 버전',
  `description` varchar(255) COMMENT '설명',
  `latitude` decimal[9,6] COMMENT '위도(-90 ~ +90)',
  `longitude` decimal[9,6] COMMENT '경도(-180 ~ +180)',
  `country_id` varchar(255) COMMENT '국가 고유번호. 도시와 국가는 N : 1 관계이다.',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '생성일',
  `updated_date` date COMMENT '최종 수정일'
) COMMENT = '';

CREATE TABLE `countries` (
  `id` varchar(255) PRIMARY KEY COMMENT '국가 고유번호',
  `name` varchar(255) NOT NULL COMMENT '국가명',
  `description` varchar(255) COMMENT '설명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '생성일',
  `updated_date` date COMMENT '최종 수정일'
);

CREATE TABLE `themes` (
  `id` bigint PRIMARY KEY COMMENT '테마 고유번호',
  `name` varchar(255) NOT NULL COMMENT '테마명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '생성일',
  `updated_date` date COMMENT '최종 수정일'
) COMMENT = '테마는 시스템에서 지정한 테마정보다. (랜드마크, 음식점, 가게, 공공시설, ...). 필터 검색 시 사용된다.';

CREATE TABLE `card_theme` (
  `theme_id` bigint COMMENT '',
  `card_id` bigint COMMENT '',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date,
  PRIMARY KEY (`theme_id`, `card_id`)
) COMMENT = '여러 장의 카드는 여러 가지 테마를 가질 수 있다. 카드와 테마는 N : M 관계이다.';

CREATE TABLE `categories` (
  `id` bigint PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `card_images` (
  `id` bigint PRIMARY KEY,
  `card_id` bigint NOT NULL,
  `save_path` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `size` int,
  `is_main` bool NOT NULL,
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `city_images` (
  `id` bigint PRIMARY KEY,
  `city_id` varchar(255) NOT NULL,
  `save_path` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `size` int,
  `is_main` bool NOT NULL,
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `country_images` (
  `id` bigint PRIMARY KEY,
  `country_id` varchar(255) NOT NULL,
  `save_path` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `size` int,
  `is_main` bool NOT NULL,
  `is_active` bool NOT NULL DEFAULT true
);

CREATE TABLE `card_map_links` (
  `id` bigint PRIMARY KEY,
  `card_id` bigint NOT NULL,
  `platform` varchar(255) NOT NULL COMMENT '지도 플랫폼 구분 (예: google_maps, naver_maps 등)',
  `map_link` varchar(255) NOT NULL COMMENT '지도 링크(URL)',
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `my_cards` (
  `id` bigint PRIMARY KEY,
  `card_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `memo` varchar(255),
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '내 카드 얻은날짜(getdate)',
  `updated_date` date
);

CREATE TABLE `my_card_tags` (
  `id` bigint NOT NULL,
  `my_card_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '태그명',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `my_card_images` (
  `id` bigint PRIMARY KEY,
  `my_card_id` bigint NOT NULL,
  `save_path` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `extension` varchar(255) NOT NULL,
  `size` int,
  `is_main` bool NOT NULL,
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `users` (
  `id` bigint PRIMARY KEY,
  `email` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255),
  `provider` varchar(255),
  `provider_id` varchar(255),
  `username` varchar(255),
  `profile_image` varchar(255),
  `role` varchar(255) NOT NULL DEFAULT 'USER',
  `is_active` bool NOT NULL DEFAULT true,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `card_scraps` (
  `card_id` bigint,
  `user_id` bigint,
  PRIMARY KEY (`card_id`, `user_id`)
);

CREATE TABLE `plans` (
  `id` bigint PRIMARY KEY,
  `title` varchar(255),
  `memo` varchar(255),
  `start_date` date,
  `end_date` date,
  `is_explore` bool NOT NULL DEFAULT true,
  `like_count` int,
  `user_id` bigint,
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `plan_days` (
  `id` bigint PRIMARY KEY,
  `plan_id` bigint NOT NULL,
  `title` varchar(255),
  `travel_date` date NOT NULL COMMENT '실제 여행일자',
  `created_date` date NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `updated_date` date
);

CREATE TABLE `plan_day_cards` (
  `id` bigint PRIMARY KEY,
  `plan_day_id` bigint,
  `seq` int COMMENT '해당 SEQ는 카드순서 변경에 의해 언제든지 변동가능',
  `card_id` bigint,
  `is_own` bool
);

CREATE TABLE `plan_day_bridges` (
  `plan_day_card_id` bigint,
  `plan_day_card_seq` int,
  `memo` varchar(255)
);

CREATE TABLE `plan_scraps` (
  `id` bigint PRIMARY KEY,
  `plan_id` bigint COMMENT '스크랩할 플랜 아이디',
  `user_id` bigint COMMENT '내 아이디'
);

ALTER TABLE `cards` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

ALTER TABLE `cities` ADD FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

ALTER TABLE `card_theme` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);

ALTER TABLE `card_theme` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `categories` ADD FOREIGN KEY (`id`) REFERENCES `cards` (`category_id`);

ALTER TABLE `card_images` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `city_images` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

ALTER TABLE `country_images` ADD FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

ALTER TABLE `card_map_links` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `my_cards` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `my_cards` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `my_card_tags` ADD FOREIGN KEY (`my_card_id`) REFERENCES `my_cards` (`id`);

ALTER TABLE `my_card_images` ADD FOREIGN KEY (`my_card_id`) REFERENCES `my_cards` (`id`);

ALTER TABLE `card_scraps` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `card_scraps` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `plans` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `plan_days` ADD FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`);

ALTER TABLE `plan_day_cards` ADD FOREIGN KEY (`plan_day_id`) REFERENCES `plan_days` (`id`);

ALTER TABLE `plan_day_bridges` ADD FOREIGN KEY (`plan_day_card_id`) REFERENCES `plan_day_cards` (`id`);

ALTER TABLE `plan_day_bridges` ADD FOREIGN KEY (`plan_day_card_seq`) REFERENCES `plan_day_cards` (`seq`);

ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`);

ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
