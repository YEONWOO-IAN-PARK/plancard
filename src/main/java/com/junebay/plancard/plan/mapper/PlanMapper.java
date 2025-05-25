package com.junebay.plancard.plan.mapper;

import com.junebay.plancard.plan.dto.CreatePlanDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Plan 관련 작업을 하는 MyBatis Mapper 인터페이스
 */
@Mapper
public interface PlanMapper {

    /**
     * 내 플랜을 등록하는 기능
     */
    long insertMyPlan(@Param("planDTO") CreatePlanDTO planDTO, @Param("userId") long userId);

    /**
     * 내 플랜 DTO 정보를 조회하는 기능
     */
    CreatePlanDTO selectPlanOne(@Param("planId") long planId);
}
