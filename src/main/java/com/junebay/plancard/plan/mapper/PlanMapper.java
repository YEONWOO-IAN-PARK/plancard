package com.junebay.plancard.plan.mapper;

import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.plan.dto.BasicPlanDTO;
import com.junebay.plancard.plan.dto.PlanDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Plan 관련 작업을 하는 MyBatis Mapper 인터페이스
 */
@Mapper
public interface PlanMapper {

    /**
     * 내 플랜을 등록하는 기능
     */
    long insertMyPlan(@Param("planDTO") BasicPlanDTO planDTO, @Param("userId") long userId);

    /**
     * 내 플랜 DTO 정보를 조회하는 기능
     */
    BasicPlanDTO selectPlanOne(@Param("planId") long planId);

    /**
     * 탐험 플랜 DTO 목록을 조회하는 기능
     */
    List<PlanDTO> selectExplorePlanList(SearchDTO searchDTO, long userId);

    /**
     * 내 플랜 DTO 목록을 조회하는 기능
     */
    List<PlanDTO> selectMyPlanList(SearchDTO searchDTO, long userId);
}
