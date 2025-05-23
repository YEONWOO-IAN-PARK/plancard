CREATE TABLE `cards` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '카드(지역) 고유번호',
  `title` varchar(255) NOT NULL COMMENT '카드(지역)명',
  `description` varchar(1500) COMMENT '설명',
  `city_id` varchar(50) NOT NULL COMMENT '도시 고유번호. 카드와 도시는 N:1 관계이다.',
  `category_id` bigint COMMENT '카테고리 고유번호 (관광지, 유적지, ...)',
  `rating` tinyint COMMENT '별점(1점 단위)',
  `main_image_id` bigint COMMENT '대표 카드 이미지 고유번호',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역) 정보 테이블. 국가:도시:카드(지역) 1:N:M 관계를 나타낸다.';

CREATE TABLE `cities` (
  `id` varchar(50) PRIMARY KEY COMMENT '도시 고유번호',
  `name` varchar(255) NOT NULL COMMENT '도시명',
  `name_ascii` varchar(255) COMMENT '도시명 ASCII 버전',
  `description` varchar(1500) COMMENT '설명',
  `latitude` double COMMENT '위도(-90 ~ +90)',
  `longitude` double COMMENT '경도(-180 ~ +180)',
  `country_id` varchar(20) COMMENT '국가 고유번호. 도시와 국가는 N:1 관계이다.',
  `main_image_id` bigint COMMENT '대표 도시 이미지 고유번호',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '도시 정보 테이블. 도시 테이블은 국가 테이블과 N:1 관계이다.';

CREATE TABLE `countries` (
  `id` varchar(50) PRIMARY KEY COMMENT '국가 고유번호',
  `name` varchar(255) NOT NULL COMMENT '국가명',
  `description` varchar(1500) COMMENT '설명',
  `main_image_id` bigint COMMENT '대표 국가 이미지 고유번호',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = 'UNComtrade 에서 제공하는 국가 정보 테이블.';

CREATE TABLE `themes` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '테마 고유번호',
  `name` varchar(255) NOT NULL COMMENT '테마명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '테마는 시스템에서 지정한 테마정보다. (랜드마크, 음식점, 가게, 공공시설, ...). 필터 검색 시 사용된다.';

CREATE TABLE `card_theme` (
  `theme_id` bigint COMMENT '테마 고유번호',
  `card_id` bigint COMMENT '카드 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일',
  PRIMARY KEY (`theme_id`, `card_id`)
) COMMENT = '카드와 테마의 중간 테이블. 여러 장의 카드는 여러 가지 테마를 가질 수 있다. 카드와 테마는 N:M 관계이다.';

CREATE TABLE `country_theme` (
  `theme_id` bigint COMMENT '테마 고유번호',
  `country_id` varchar(50) COMMENT '국가 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일',
  PRIMARY KEY (`theme_id`, `country_id`)
) COMMENT = '국가와 테마의 중간 테이블. 여러 국가는 여러 가지 테마를 가질 수 있다. 국가와 테마는 N:M 관계이다.';

CREATE TABLE `city_theme` (
  `theme_id` bigint COMMENT '테마 고유번호',
  `city_id`  varchar(50) COMMENT '도시 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일',
  PRIMARY KEY (`theme_id`, `city_id`)
) COMMENT = '도시와 테마의 중간 테이블. 여러 도시는 여러 가지 테마를 가질 수 있다. 도시와 테마는 N:M 관계이다.';

CREATE TABLE `categories` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 고유번호',
  `name` varchar(255) NOT NULL COMMENT '카테고리명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '하나의 카드(지역)에 하나만 할당되는 카테고리 정보';

CREATE TABLE `card_images` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '카드(지역) 이미지 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드(지역) 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `original_file_name` varchar(255) NOT NULL COMMENT '원본 파일명',
  `stored_file_name` varchar(255) NOT NULL COMMENT '저장 파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `alt` varchar(255) COMMENT 'ALT 텍스트',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역)에 할당되는 이미지. 카드 테이블과 카드 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `city_images` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '도시 이미지 고유번호',
  `city_id` varchar(50) NOT NULL COMMENT '도시 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `original_file_name` varchar(255) NOT NULL COMMENT '원본 파일명',
  `stored_file_name` varchar(255) NOT NULL COMMENT '저장 파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `alt` varchar(255) COMMENT 'ALT 텍스트',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '도시에 할당되는 이미지. 도시 테이블과 도시 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `country_images` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '국가 이미지 고유번호',
  `country_id` varchar(50) NOT NULL COMMENT '국가 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `original_file_name` varchar(255) NOT NULL COMMENT '원본 파일명',
  `stored_file_name` varchar(255) NOT NULL COMMENT '저장 파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `alt` varchar(255) COMMENT 'ALT 텍스트',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '국가에 할당되는 이미지. 국가 테이블과 국가 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `card_map_links` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '카드 맵 링크 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드 고유번호(FK)',
  `platform` varchar(255) NOT NULL COMMENT '지도 플랫폼 구분 (예: google_maps, naver_maps 등)',
  `map_link` varchar(255) NOT NULL COMMENT '지도 링크(URL)',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역) 위치정보 링크 정보 테이블';

CREATE TABLE `my_cards` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '내 카드 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드 고유번호(FK)',
  `user_id` bigint NOT NULL COMMENT '유저 고유번호(FK)',
  `memo` varchar(1500) COMMENT '메모',
  `main_image_type` char(1) COMMENT '대표 카드 이미지 타입 ( C: 카드, M : 내 카드 ) ',
  `main_image_id` bigint COMMENT '대표 카드 이미지 고유번호',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '내 카드 얻은날짜(getdate)',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드 정보. 특정 지역에서 여행 인증을 하면 카드(지역)의 커스텀 카드를 획득할 수 있다.';

CREATE TABLE `my_card_tags` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '내 카드 태그 고유번호',
  `my_card_id` bigint NOT NULL COMMENT '내 카드 고유번호(FK)',
  `name` varchar(255) NOT NULL COMMENT '태그명',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드의 태그정보 테이블. 5개까지 추가할 수 있는 사용자 정의 태그 정보이다.';

CREATE TABLE `my_card_images` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '내 카드 이미지 고유번호',
  `my_card_id` bigint NOT NULL COMMENT '내 카드 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `original_file_name` varchar(255) NOT NULL COMMENT '원본 파일명',
  `stored_file_name` varchar(255) NOT NULL COMMENT '저장 파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `alt` varchar(255) COMMENT 'ALT 텍스트',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드 이미지정보 테이블. 내 카드에 여러장의 사진을 추가할 수 있도록 고려';

CREATE TABLE `users` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유번호',
  `email` varchar(255) NOT NULL COMMENT '이메일',
  `password` varchar(255) COMMENT '암호화된 비밀번호',
  `provider` varchar(255) COMMENT '소셜로그인 제공자',
  `provider_id` varchar(255) COMMENT '소셜로그인 제공자 고유번호',
  `nickname` varchar(255) COMMENT '별명',
  `name` varchar(255) COMMENT '이름',
  `profile_image` varchar(255) COMMENT '프로필 사진',
  `role` varchar(255) NOT NULL DEFAULT 'USER' COMMENT '역할',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '사용자 정보테이블';

CREATE TABLE `card_scraps` (
  `card_id` bigint COMMENT '카드 고유번호',
  `user_id` bigint COMMENT '사용자 고유번호',
  PRIMARY KEY (`card_id`, `user_id`)
) COMMENT = '사용자가 스크랩한 카드(지역) 정보 테이블';

CREATE TABLE `plans` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '여행계획 고유번호',
  `title` varchar(255) COMMENT '여행계획명',
  `memo` varchar(1500) COMMENT '메모',
  `start_date` date COMMENT '시작일',
  `end_date` date COMMENT '종료일',
  `visibility` varchar(10) NOT NULL DEFAULT 'private' COMMENT '플랜탐험 공개설정 "private" , "public"',
  `like_count` int COMMENT '좋아요 수',
  `user_id` bigint COMMENT '플랜 사용자 고유번호',
  `author_id` bigint COMMENT '플랜 원작자 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '사용자 정의 여행 계획정보 테이블. 여행일자, 여행일자별 카드, 여행일자별 브릿지로 구성';

CREATE TABLE `plan_days` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '여행계획 - 일자 고유번호',
  `plan_id` bigint NOT NULL COMMENT '여행계획 고유번호(FK)',
  `title` varchar(255) COMMENT '여행계획명',
  `travel_date` date NOT NULL COMMENT '실제 여행일자',
  `visibility` varchar(10) NOT NULL DEFAULT 'private' COMMENT '플랜탐험 공개설정 "private" , "public"',
  `like_count` int COMMENT '좋아요 수',
  `author_id` bigint COMMENT '플랜 - 일자 원작자 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '사용자 정의 여행 계획정보 - 일별 정보 테이블. 카드와 브릿지로 구성';

CREATE TABLE `plan_day_cards` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '여행계획 - 일자 - 카드(지역) 고유번호',
  `plan_day_id` bigint COMMENT '여행계획 - 일자 고유번호(FK)',
  `seq` int COMMENT '해당 SEQ는 카드순서 변경에 의해 언제든지 변동가능',
  `card_id` bigint COMMENT '카드(지역) 고유번호(FK)'
) COMMENT = '사용자 정의 여행 계획 - 일별 카드 정보 테이블. 브릿지로 연결됨';

CREATE TABLE `plan_day_bridges` (
  `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '여행계획 - 일자 - 브릿지 고유번호',
  `plan_day_id` bigint COMMENT '여행계획 - 일자 고유번호(FK)',
  `seq` int COMMENT '여행계획의 day에 존재하는 브릿지의 위치. (1부터 시작)',
  `memo` varchar(1500) COMMENT '메모'
) COMMENT = '사용자 정의 여행 계획 - 일자별 카드별 브릿지 정보 테이블.';

CREATE TABLE `plan_scraps` (
  `plan_id` bigint COMMENT '스크랩한 플랜 고유번호(FK)',
  `user_id` bigint COMMENT '스크랩한 사용자 고유번호(FK)',
  PRIMARY KEY (plan_id, user_id)
);

CREATE TABLE `plan_day_scraps` (
   `plan_day_id` bigint COMMENT '스크랩한 여행계획 - 일자 고유번호(FK)',
   `user_id` bigint COMMENT '스크랩한 사용자 고유번호(FK)',
   PRIMARY KEY (plan_day_id, user_id)
);

CREATE TABLE `plan_likes` (
    `plan_id` bigint COMMENT '여행계획 고유번호(FK)',
    `user_id` bigint COMMENT '좋아요한 사용자 고유번호(FK)',
    PRIMARY KEY (plan_id, user_id)
);

CREATE TABLE `plan_day_likes` (
    `plan_day_id` bigint COMMENT '여행계획 - 일자 고유번호(FK)',
    `user_id` bigint COMMENT '좋아요한 사용자 고유번호(FK)',
    PRIMARY KEY (plan_day_id, user_id)
);

CREATE TABLE `temp_storages` (
   `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '임시 저장소 고유번호',
   `user_id` bigint COMMENT '사용자의 현재 장바구니',
   `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
   `updated_date` datetime COMMENT '최종 수정일'
);

CREATE TABLE `temp_storage_cards` (
   `id` bigint AUTO_INCREMENT PRIMARY KEY COMMENT '임시 카드저장소 고유번호',
   `temp_storage_id` bigint COMMENT '임시 저장소에 고유번호(FK)',
   `card_id` bigint COMMENT '임시 저장소에 담은 카드(지역) 고유번호',
   `my_card_id` bigint COMMENT '임시 카드저장소에 담은 내 카드(커스텀) 고유번호',
   `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
   `updated_date` datetime COMMENT '최종 수정일'
);

ALTER TABLE `cards` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);
ALTER TABLE `cards` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `cities` ADD FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

ALTER TABLE `card_theme` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);
ALTER TABLE `card_theme` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `country_theme` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);
ALTER TABLE `country_theme` ADD FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

ALTER TABLE `city_theme` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);
ALTER TABLE `city_theme` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

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

ALTER TABLE `plan_day_bridges` ADD FOREIGN KEY (`plan_day_id`) REFERENCES `plan_days` (`id`);

ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`);
ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `plan_day_scraps` ADD FOREIGN KEY (`plan_day_id`) REFERENCES `plan_days` (`id`);
ALTER TABLE `plan_day_scraps` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `temp_storages` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `temp_storage_cards` ADD FOREIGN KEY (`temp_storage_id`) REFERENCES `temp_storages` (`id`);
ALTER TABLE `temp_storage_cards` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);
ALTER TABLE `temp_storage_cards` ADD FOREIGN KEY (`my_card_id`) REFERENCES `my_cards` (`id`);

ALTER TABLE `users` ADD UNIQUE KEY users_uk (`provider`, `provider_id`);