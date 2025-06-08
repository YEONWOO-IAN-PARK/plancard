package com.junebay.plancard.plan.dto;

import com.junebay.plancard.plan.vo.PlanDayBridge;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class PlanDayDTO {

    private long planDayId;
    private String title;
    private LocalDate travelDate;
    private List<Integer> planDayCardList;
    private List<PlanDayBridge> planDayBridgeList;
}
