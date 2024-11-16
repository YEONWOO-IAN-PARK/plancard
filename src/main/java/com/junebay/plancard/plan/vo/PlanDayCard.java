package com.junebay.plancard.plan.vo;

import lombok.Builder;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description :
 */
@Builder
public class PlanDayCard {

    private long id;
    private long planDayId;
    private int seq;
    private long cardId;
}
