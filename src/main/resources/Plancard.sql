CREATE TABLE `cards` (
  `id` bigint PRIMARY KEY COMMENT '카드(지역) 고유번호',
  `title` varchar(255) NOT NULL COMMENT '카드(지역)명',
  `description` varchar(255) COMMENT '설명',
  `city_id` varchar(255) NOT NULL COMMENT '도시 고유번호. 카드와 도시는 N:1 관계이다.',
  `category_id` bigint COMMENT '카테고리 고유번호 (관광지, 유적지, ...)',
  `rating` tinyint COMMENT '별점(1점 단위)',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역) 정보 테이블. 국가:도시:카드(지역) 1:N:M 관계를 나타낸다.';

CREATE TABLE `cities` (
  `id` varchar(255) PRIMARY KEY COMMENT '도시 고유번호',
  `name` varchar(255) NOT NULL COMMENT '도시명',
  `name_ascii` varchar(255) COMMENT '도시명 ASCII 버전',
  `description` varchar(255) COMMENT '설명',
  `latitude` decimal(9,6) COMMENT '위도(-90 ~ +90)',
  `longitude` decimal(9,6) COMMENT '경도(-180 ~ +180)',
  `country_id` varchar(255) COMMENT '국가 고유번호. 도시와 국가는 N:1 관계이다.',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '도시 정보 테이블. 도시 테이블은 국가 테이블과 N:1 관계이다.';

CREATE TABLE `countries` (
  `id` varchar(255) PRIMARY KEY COMMENT '국가 고유번호',
  `name` varchar(255) NOT NULL COMMENT '국가명',
  `description` varchar(255) COMMENT '설명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = ' UNComtrade 에서 제공하는 국가 정보 테이블.';

CREATE TABLE `themes` (
  `id` bigint PRIMARY KEY COMMENT '테마 고유번호',
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

CREATE TABLE `categories` (
  `id` bigint PRIMARY KEY COMMENT '카테고리 고유번호',
  `name` varchar(255) NOT NULL COMMENT '카테고리명',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '하나의 카드(지역)에 하나만 할당되는 카테고리 정보';

CREATE TABLE `card_images` (
  `id` bigint PRIMARY KEY COMMENT '카드(지역) 이미지 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드(지역) 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `file_name` varchar(255) NOT NULL COMMENT '파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `is_main` bool NOT NULL COMMENT '메인사진 유무',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역)에 할당되는 이미지. 카드 테이블과 카드 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `city_images` (
  `id` bigint PRIMARY KEY COMMENT '도시 이미지 고유번호',
  `city_id` varchar(255) NOT NULL COMMENT '도시 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `file_name` varchar(255) NOT NULL COMMENT '파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `is_main` bool NOT NULL COMMENT '메인사진 유무',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '도시에 할당되는 이미지. 도시 테이블과 도시 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `country_images` (
  `id` bigint PRIMARY KEY COMMENT '국가 이미지 고유번호',
  `country_id` varchar(255) NOT NULL COMMENT '국가 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `file_name` varchar(255) NOT NULL COMMENT '파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `is_main` bool NOT NULL COMMENT '메인사진 유무',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '국가에 할당되는 이미지. 국가 테이블과 국가 이미지 테이블은 1:N 관계이다.';

CREATE TABLE `card_map_links` (
  `id` bigint PRIMARY KEY COMMENT '카드 맵 링크 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드 고유번호(FK)',
  `platform` varchar(255) NOT NULL COMMENT '지도 플랫폼 구분 (예: google_maps, naver_maps 등)',
  `map_link` varchar(255) NOT NULL COMMENT '지도 링크(URL)',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '카드(지역) 위치정보 링크 정보 테이블';

CREATE TABLE `my_cards` (
  `id` bigint PRIMARY KEY COMMENT '내 카드 고유번호',
  `card_id` bigint NOT NULL COMMENT '카드 고유번호(FK)',
  `user_id` bigint NOT NULL COMMENT '유저 고유번호(FK)',
  `memo` varchar(255) COMMENT '메모',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '내 카드 얻은날짜(getdate)',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드 정보. 특정 지역에서 여행 인증을 하면 카드(지역)의 커스텀 카드를 획득할 수 있다.';

CREATE TABLE `my_card_tags` (
  `id` bigint NOT NULL COMMENT '내 카드 태그 고유번호',
  `my_card_id` bigint NOT NULL COMMENT '내 카드 고유번호(FK)',
  `name` varchar(255) NOT NULL COMMENT '태그명',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드의 태그정보 테이블. 5개까지 추가할 수 있는 사용자 정의 태그 정보이다.';

CREATE TABLE `my_card_images` (
  `id` bigint PRIMARY KEY COMMENT '내 카드 이미지 고유번호',
  `my_card_id` bigint NOT NULL COMMENT '내 카드 고유번호(FK)',
  `save_path` varchar(255) NOT NULL COMMENT '저장 경로',
  `file_name` varchar(255) NOT NULL COMMENT '파일명',
  `extension` varchar(255) NOT NULL COMMENT '확장자',
  `size` int COMMENT '파일 크기(byte)',
  `is_main` bool NOT NULL COMMENT '메인사진 유무',
  `is_active` bool NOT NULL DEFAULT true COMMENT '사용 유무',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '내 카드 이미지정보 테이블.';

CREATE TABLE `users` (
  `id` bigint PRIMARY KEY COMMENT '사용자 고유번호',
  `email` varchar(255) UNIQUE NOT NULL COMMENT '이메일',
  `password` varchar(255) COMMENT '암호화된 비밀번호',
  `provider` varchar(255) COMMENT '소셜로그인 제공자',
  `provider_id` varchar(255) COMMENT '소셜로그인 제공자 고유번호',
  `username` varchar(255) COMMENT '사용자명',
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
  `id` bigint PRIMARY KEY COMMENT '여행계획 고유번호',
  `title` varchar(255) COMMENT '여행계획명',
  `memo` varchar(255) COMMENT '메모',
  `start_date` date COMMENT '시작일',
  `end_date` date COMMENT '종료일',
  `is_explore` bool NOT NULL DEFAULT true COMMENT '플랜 탐험 등록유무',
  `like_count` int COMMENT '좋아요 수',
  `user_id` bigint COMMENT '플랜 작성 사용자 고유번호',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '사용자 정의 여행 계획정보 테이블. 여행일자, 여행일자별 카드, 여행일자별 브릿지로 구성';

CREATE TABLE `plan_days` (
  `id` bigint PRIMARY KEY COMMENT '여행계획 - 일자 고유번호',
  `plan_id` bigint NOT NULL COMMENT '여행계획 고유번호(FK)',
  `title` varchar(255) COMMENT '여행계획명',
  `travel_date` date NOT NULL COMMENT '실제 여행일자',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_date` datetime COMMENT '최종 수정일'
) COMMENT = '사용자 정의 여행 계획정보 - 일별 정보 테이블. 카드와 브릿지로 구성';

CREATE TABLE `plan_day_cards` (
  `id` bigint PRIMARY KEY COMMENT '여행계획 - 카드(지역) 고유번호',
  `plan_day_id` bigint COMMENT '여행계획 - 일자 고유번호(FK)',
  `seq` int COMMENT '해당 SEQ는 카드순서 변경에 의해 언제든지 변동가능',
  `card_id` bigint COMMENT '카드(지역) 고유번호(FK)',
  `is_own` bool COMMENT '내 카드 등록 유무'
) COMMENT = '사용자 정의 여행 계획 - 일별 카드 정보 테이블. 브릿지로 연결됨';

CREATE TABLE `plan_day_bridges` (
  `plan_day_card_id` bigint COMMENT '여행계획 - 일자별 카드 고유번호',
  `plan_day_card_seq` int COMMENT '여행계획 - 일자별 카드 순서',
  `memo` varchar(255) COMMENT '메모'
) COMMENT = '사용자 정의 여행 계획 - 일자별 카드별 브릿지 정보 테이블.';

CREATE TABLE `plan_scraps` (
  `id` bigint PRIMARY KEY COMMENT '여행계획 스크랩 고유번호',
  `plan_id` bigint COMMENT '스크랩한 플랜 고유번호(FK)',
  `user_id` bigint COMMENT '스크랩한 사용자 고유번호(FK)'
);

ALTER TABLE `cards` ADD FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`);

ALTER TABLE `cities` ADD FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

ALTER TABLE `card_theme` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);

ALTER TABLE `card_theme` ADD FOREIGN KEY (`card_id`) REFERENCES `cards` (`id`);

ALTER TABLE `cards` ADD FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

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

ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`);

ALTER TABLE `plan_scraps` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
