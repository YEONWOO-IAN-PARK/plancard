package com.junebay.plancard.plan.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2024-11-07
 * @description : Plan Day 하위의 카드와 카드사이를 잇는 Bridge
 */
@Getter
@Setter
public class PlanDayBridge {

    private long id;
    private long planDayId;
    private int seq;
    private String memo;
}
