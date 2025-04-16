package com.junebay.plancard.common.vo;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO - 정렬 정보
 */
public class Sort {

    private String sortBy;      /* 정렬 기준 */
    private String sortOrder;   /* 정렬 방법 (오름차순, 내림차순) */

    /**
         "sort": {
             "sortBy": "cardTitle",
             "sortOrder": "asc"
         },
     */
}
