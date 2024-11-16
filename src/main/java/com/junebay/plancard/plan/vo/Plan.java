package com.junebay.plancard.plan.vo;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Builder
public class Plan {

    private long id;
    private String title;
    private String memo;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String visibility;
    private int likeCount;
    private long userId;    // It may differ from the author id when user scraps plan
    private long authorId;  // author's user id
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
