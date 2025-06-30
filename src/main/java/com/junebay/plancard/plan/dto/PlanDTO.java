package com.junebay.plancard.plan.dto;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.vo.Category;
import com.junebay.plancard.common.vo.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 내 플랜 또는 탐험 플랜 DTO
 */
@Getter
@Setter
public class PlanDTO extends BasicPlanDTO {

    private String visibility;
    private int likeCount;
    private boolean like;
    private boolean scrap;
    private String author;
    private Date createdDate;
    private Date lastUpdateDate;
    private List<Category> categoryList;
    private List<Category> themeList;
    private List<ExplorePlanTagDTO> tagList;  // categoryList + themeList 중 특정기준으로 추출된 n개 태그정보를 해당 목록에 담는다. 세팅 시점은 mybatis mapper 호출 이후 setter메서드를 별도로 실행해야함
    private List<CardDTO> thumbnailCardList;
}
