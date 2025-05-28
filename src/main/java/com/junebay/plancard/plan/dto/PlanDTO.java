package com.junebay.plancard.plan.dto;

import com.junebay.plancard.common.vo.Tag;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 내 플랜 또는 탐험 플랜 DTO
 */
@Getter
@Setter
public class PlanDTO extends CreatePlanDTO {

    private String visibility;
    private int likeCount;
    private boolean scrap;
    private boolean like;
    private String author;
    private Date createdDate;
    private Date lastUpdateDate;
    private List<Tag> tagList;
}
