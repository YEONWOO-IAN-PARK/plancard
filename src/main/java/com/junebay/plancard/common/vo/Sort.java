package com.junebay.plancard.common.vo;

import com.junebay.plancard.card.enums.SortBy;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO - 정렬 정보
 */
public class Sort {

    private SortBy sortBy;      /* 정렬 기준 */
    private String sortOrder;   /* 정렬 방법 (오름차순, 내림차순) */

    public void setSortBy(String sb) {
        this.sortBy = SortBy.from(sb);
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
         "sort": {
             "sortBy": "cardTitle",
             "sortOrder": "asc"
         },
     */
}
