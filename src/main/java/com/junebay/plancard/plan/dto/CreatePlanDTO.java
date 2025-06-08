package com.junebay.plancard.plan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 플랜 등록 시 클라이언트에게 전달받은 플랜 템플릿 + 플랜 데이 목록 DTO 클래스
 */
@Getter
@Setter
public class CreatePlanDTO extends BasicPlanDTO {

    private List<PlanDayDTO> planDayList;
}
