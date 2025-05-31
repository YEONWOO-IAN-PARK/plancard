package com.junebay.plancard.plan.service.impl;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.validator.CustomValidator;
import com.junebay.plancard.plan.dto.BasicPlanDTO;
import com.junebay.plancard.plan.dto.CreatePlanDTO;
import com.junebay.plancard.plan.dto.PlanDTO;
import com.junebay.plancard.plan.mapper.PlanMapper;
import com.junebay.plancard.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 카드 관련 비즈니스 로직을 구현하는 서비스 구현체 클래스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    @Value("${response.created.plan.detail}")
    private String insertedPlan;
    private final PlanMapper planMapper;
    private final CustomValidator customValidator;

    @Override
    public ResponseDTO createMyPlan(CreatePlanDTO planDTO) {
        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        customValidator.validateStringDateRange(planDTO.getStartDate(), planDTO.getEndDate());

        // 1. Plan Insert
        planMapper.insertMyPlan(planDTO, userId);

        // TODO: 2. PlanDay Insert

        // TODO: 3. planDayCard Insert

        // TODO: 4. PlanDayBridge Insert


        BasicPlanDTO justCreatedPlanDTO = planMapper.selectPlanOne(planDTO.getPlanId());

        return setResponseDTO(justCreatedPlanDTO, insertedPlan);
    }

    @Override
    public ResponseDTO selectPlanList(String planType, SearchDTO searchDTO) {
        int totalItemCount;
        List<PlanDTO> planDTOList;

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        customValidator.validateRequest(searchDTO);

//        if ("explore".equals(planType)) {
//            totalItemCount = planMapper.selectAllExplorePlanCount(searchDTO, userId);
//            planDTOList = planMapper.selectExplorePlanList(searchDTO, userId);
//        } else {
//            totalItemCount = planMapper.selectAllMyPlanCount(searchDTO, userId);
//            planDTOList = planMapper.selectMyPlanList(searchDTO, userId);
//        }

//        searchDTO.getPagination().setTotalItems(totalItemCount);

//        return setResponseDTO();

        return null;
    }

    /**
     * 단일 카드 반환을 위한 ResponseDTO 세팅
     * 플랜 상세 조회 || 플랜 추가 시 사용된다.
     */
    private ResponseDTO setResponseDTO(BasicPlanDTO planDTO, String detail) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (planDTO != null) {
            responseDTO.setDetails(detail);
        }
        responseDTO.setResult(planDTO);

        return responseDTO;
    }
}
