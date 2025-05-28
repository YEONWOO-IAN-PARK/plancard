package com.junebay.plancard.plan.service;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.plan.dto.CreatePlanDTO;

/**
 * 플랜 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface PlanService {

    /**
     * 내 플랜을 등록하고 등록된 정보를 반환하는 기능
     * @param planDTO
     */
    ResponseDTO createMyPlan(CreatePlanDTO planDTO);

    /**
     * @param planType 조회플랜 유형("explore" || "my")
     * @param searchDTO 요청 DTO
     */
    ResponseDTO selectPlanList(String planType, SearchDTO searchDTO);
}
