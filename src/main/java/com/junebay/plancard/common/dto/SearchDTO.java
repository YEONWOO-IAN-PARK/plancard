package com.junebay.plancard.common.dto;

import com.junebay.plancard.common.vo.Filter;
import com.junebay.plancard.common.vo.Pagination;
import com.junebay.plancard.common.vo.Sort;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO
 */
@Getter
@Setter
public class SearchDTO {

    private String search;             /* 검색어 */
    private Filter filter;              /* 필터링 */
    private Sort sort;                 /* 정렬 */
    private Pagination pagination;     /* 페이지네이션 */


    /**
     {
         "search": "",
         "filter": {
             "scrap": false,
             "country": "FR",
             "city": "1301",
             "theme": ["7","132"],
             "category": ["1123", "978"]
         },
         "sort": {
             "sortBy": "cardTitle",
             "sortOrder": "asc"
         },
         "pagination": {
             "page": 1,
             "size": 10
         }
     }
     */
}
