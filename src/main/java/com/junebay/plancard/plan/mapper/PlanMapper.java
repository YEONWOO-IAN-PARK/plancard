package com.junebay.plancard.plan.mapper;

import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.plan.dto.BasicPlanDTO;
import com.junebay.plancard.plan.dto.PlanDTO;
import com.junebay.plancard.plan.dto.PlanDayDTO;
import com.junebay.plancard.plan.vo.PlanDayBridge;
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
     * 탐험 플랜 DTO 목록의 총 건수를 조회하는 기능
     */
    int selectAllExplorePlanCount(@Param("searchDTO") SearchDTO searchDTO, @Param("userId") long userId);

    /**
     * 탐험 플랜 DTO 목록을 조회하는 기능
     */
    List<PlanDTO> selectExplorePlanList(@Param("searchDTO") SearchDTO searchDTO, @Param("userId")long userId);

    /**
     * 내 플랜 DTO 목록의 총 건수를 조회하는 기능
     */
    int selectAllMyPlanCount(@Param("searchDTO") SearchDTO searchDTO, @Param("userId")long userId);

    /**
     * 내 플랜 DTO 목록을 조회하는 기능
     */
    List<PlanDTO> selectMyPlanList(@Param("searchDTO") SearchDTO searchDTO, @Param("userId") long userId);

    /**
     * 내 플랜을 저장하는 기능
     */
    void insertPlanDay(@Param("planId") long planId, @Param("planDayDTO") PlanDayDTO planDayDTO, @Param("userId") long userId);

    /**
     * 내 플랜 > 플랜데이 카드 목록을 추가하는 기능
     */
    void insertPlanDayCards(@Param("planDayDTO") PlanDayDTO planDayDTO, @Param("userId") long userId);

    /**
     * 내 플랜 > 플렌데이 카드를 잇는 브릿지를 추가하는 기능
     * 카드가 2개일떄 카드를 잇는 브릿지는 1개이다. 브릿지 개수 = (카드 개수 - 1)
     */
    void insertPlanDayBridges(@Param("planDayDTO") PlanDayDTO planDayDTO, @Param("userId") long userId);


}
