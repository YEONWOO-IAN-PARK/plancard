package com.junebay.plancard.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청/응답 DTO - 페이지네이션 정보
 */
@Getter
@Setter
public class Pagination {

    private int page;           /* 불러올 페이지 번호 */
    private int size;           /* 한 페이지에 가져올 row 개수 */
    private int totalPages;     /* 총 페이지 수 */
    private int totalItems;     /* 총 row 수 */
    private int offset;         /* 오프셋 */

    public Pagination(int page, int size) {
        if (size <= 0) throw new IllegalArgumentException("size must be greater than 0");

        this.page = page;
        this.size = size;
        this.offset = (page - 1) * size;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;

        if (this.totalItems > 0) {
            this.totalPages = (int) Math.ceil((double) this.totalItems / this.size);
        } else {
            this.totalPages = 0;
        }
    }

    /**
         "pagination": {
             "page": 1,
             "size": 10,
             "totalPages": 5,
             "totalItems": 50
         }
     */
}
