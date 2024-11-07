package com.junebay.plancard.plan.vo;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
public class PlanDay {

    private long id;
    private long planId;
    private String title;
    private String visibility;
    private int likeCount;
    private boolean isLike;
    private boolean isScrap;
    private long authorId;  // author's userId
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
