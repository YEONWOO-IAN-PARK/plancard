package com.junebay.plancard.common.dto;

import com.junebay.plancard.common.vo.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 응답 DTO
 */
@Getter
@Setter
public class ResponseDTO {

    private List<?> result;           /* 응답 데이터 */
    private Pagination pagination;  /* 페이지네이션 */
    private String details;          /* 상세 응답 메시지 (ex.검색결과에 따른 목록을 가져왔습니다.) */

    /**
     {
         "status": 200,
         "message": "OK",
         "data": [F
             {
                 "cardId": "1",
                 "title -> cardTitle": "Eiffel Tower",
                 "description": "A wrought iron lattice tower on the Champ de Mars in Paris, France.",
                 "country": "France",
                 "city": "Paris",
                 "theme": ["night","elastic"],
                 "category": "attraction",
                 "rating": 4,
                 "scrap": true,
                 "mapLink": "https://googlemap.com/france/paris/972234",
                 "imageList": [
                     {
                         "imageId": 1,
                         "imageUrl": "https://example.com/eiffel.jpg",
                         "alt": "루브르 이미지",
                         "isMain": false
                     }
                 ]
             },
             {
                 "cardId": "2",
                 "title": "Louvre Museum",
                 "description": "The world's largest art museum and a historic monument in Paris, France.",
                 "country": "France",
                 "city": "Paris",
                 "theme": ["art"],
                 "category": "attraction",
                 "rating": 3,
                 "scrap": false,
                 "mapLink": "https://googlemap.com/france/paris/972234",
                 "imageList": [
                     {
                         "imageId": 1,
                         "imageUrl": "https://example.com/louvre.jpg",
                         "alt": "루브르 이미지",
                         "isMain": false
                     }
                 ]
             }
         ],
         "pagination": {
             "page": 1,
             "size": 10,
             "totalPages": 5,
             "totalItems": 50
         },
         "details": "검색결과에 따른 카드 목록을 가져왔습니다."
     }
     */
}
