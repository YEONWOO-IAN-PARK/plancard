## 2024.09.06 Fri
- 프로젝트 생성

## 2024.09.08 Sun
- 사용할 국가, 도시 구축정보 검색

## 2024.09.09 Mon
- OAuth2 소셜 로그인 대상 선정 (NAVER, GOOGLE, KAKAO)

## 2024.09.18 Wed
- ERD 작성 중
- TODO : 카드의 스크랩, 별점(평점) 테이블 처리 어떻게 할지 의논해야 함

## 2024.09.20 Fri
- ERD 작성 중
- TODO : 국가, 도시 테이블의 한글화 버전이 필요함. (컬럼으로 추가하는게 나을듯)
- 카드의 스크랩은 테이블화 시켜서 관리하는게 맞을듯

## 2024.09.21 Sat
- ERD 작성 중
- 스크랩에 대한 테이블 처리 완료 (유저 : 카드 테이블을 다대다로 설정)
- Q. 플랜 등록 & 수정 페이지에서 day 스케쥴에 등록된 카드 중 특정 카드를 삭제했을 경우 브릿지의 개수도 줄어들어야함. 브릿지 삭제 기준 정립 필요
  - 브릿지에 배치된 카드의 순서에 맞게 순서를 부여해야 할듯

## 2024.09.22 Sun
- Q. 플랜 > Mine 페이지에서 특정 일자 내에 여러 플랜 덱이 존재하는데 어떤 의미인지? 현재 특정 일자 내 플랜 구성은 '카드-브릿지-카드' 인데 덱끼리 뭉쳐있는 구성 기준이 궁금함
- 내 플랜을 플랜탐험에 등록할 경우 생성되는 내 플랜 공유 링크는 어떤식으로 처리할지 생각해보아야함.
  - 향후 구현할 플랜 탐험 페이지에서 내 게시물의 링크를 컬럼으로 등록해 두는것이 아니라 {플랜탐험페이지URL} + 인코딩된 고유플랜 문자열을 PathVariable이나 쿼리파라미터 형태로 링크를 생성하고 서버에 요청했을때 컨트롤러에서 처리하는 방식으로 구현하면 어떨까?
- 09.21에 질의되었던 브릿지 삭제 기준에 대해..
  - 플랜 내의 day에 할당된 카드와 브릿지는 순서를 가져야 한다. 모두 SEQ로 관리되며 카드를 삭제할 경우 경고문으로 알리고 왼쪽 브릿지까지 삭제된다.(첫번째 카드일 경우 제외)
  - 이로 인한 사이드 이펙트로 '플랜 수정 > 특정 day에 나열된 카드 순서를 변경 시 DB상 SEQ만 서로 변경'을 고려해야 한다.
- 