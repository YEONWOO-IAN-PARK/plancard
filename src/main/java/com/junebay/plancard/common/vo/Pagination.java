package com.junebay.plancard.common.vo;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청/응답 DTO - 페이지네이션 정보
 */
public class Pagination {

    private int page;           /* 불러올 페이지 번호 */
    private int size;           /* 한 페이지에 가져올 row 개수 */
    private int totalPages;     /* 총 페이지 수 */
    private int totalItems;     /* 총 row 수 */

    /**
         "pagination": {
             "page": 1,
             "size": 10,
             "totalPages": 5,
             "totalItems": 50
         }
     */
}
