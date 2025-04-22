package com.junebay.plancard.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 요청 DTO - 필터 정보
 */
@Getter
@Setter
public class Filter {

    private boolean scrap;              /* 스크랩 여부 */
    private boolean like;               /* 좋아요 여부 */
    private boolean day;                /*  */
    private String country;             /* 국가 ID */
    private String city;                /* 도시 ID */
    private List<String> theme;         /* 테마 ID */
    private List<String> category;      /* 카테고리 ID */

    /**
        "filter": {
            "scrap": false,
            "like": false,
            "day": false,
            "country": "FR",
            "city": "1301",
            "theme": ["7","132"],
            "category": ["1123", "978"]
        },
     */
}
