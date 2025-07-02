package com.junebay.plancard.plan.service.impl;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.validator.CustomValidator;
import com.junebay.plancard.plan.dto.BasicPlanDTO;
import com.junebay.plancard.plan.dto.CreatePlanDTO;
import com.junebay.plancard.plan.dto.PlanDTO;
import com.junebay.plancard.plan.dto.PlanDayDTO;
import com.junebay.plancard.plan.mapper.PlanMapper;
import com.junebay.plancard.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Value("${response.ok.exist.plan.list.detail}")
    private String existPlans;

    @Value("${response.ok.notExist.list.detail}")
    private String notExist;

    private final PlanMapper planMapper;
    private final CustomValidator customValidator;

    @Override
    public ResponseDTO createMyPlan(CreatePlanDTO planDTO) {
        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        // 1. Plan Insert
        customValidator.validateDateRange(planDTO.getStartDate(), planDTO.getEndDate());
        planMapper.insertMyPlan(planDTO, userId);
        BasicPlanDTO justCreatedPlanDTO = planMapper.selectPlanOne(planDTO.getPlanId());

        List<PlanDayDTO> planDayList = planDTO.getPlanDayList();
        customValidator.validatePlanDays(planDayList);
        // PlanDayDTO 객체는 Plan Day 수 만큼 존재하며 travelDate를 필수값으로 보유함
        planDayList.forEach(pd -> {
            planMapper.insertPlanDay(justCreatedPlanDTO.getPlanId(), pd, userId);
            List<Integer> planDayCardList = pd.getPlanDayCardList();
            if (planDayCardList != null) {
                if (!planDayCardList.isEmpty()) planMapper.insertPlanDayCards(pd, userId);
                if (planDayCardList.size() > 1) planMapper.insertPlanDayBridges(pd, userId);
            }
        });

        return setResponseDTO(justCreatedPlanDTO);
    }

    @Override
    public ResponseDTO selectPlanList(SearchDTO searchDTO, String planType) {
        int totalItemCount = -1;
        List<PlanDTO> planDTOList = new ArrayList<>();

        long userId = 2;    // TODO : 임시 유저 아이디. 스프링 시큐리티 적용 시 대체한다.

        customValidator.validateRequest(searchDTO);

        if ("explore".equals(planType)) {
            customValidator.validateRequestForFilter(searchDTO);

            totalItemCount = planMapper.selectAllExplorePlanCount(searchDTO, userId);
            planDTOList = planMapper.selectExplorePlanList(searchDTO, userId);
        } else {
            totalItemCount = planMapper.selectAllMyPlanCount(searchDTO, userId);
            if (totalItemCount > 0) {
                planDTOList = planMapper.selectMyPlanList(searchDTO, userId);
            }
        }

        searchDTO.getPagination().setTotalItems(totalItemCount);

        return setResponseDTO(searchDTO, planDTOList);
    }

    private ResponseDTO setResponseDTO(SearchDTO searchDTO, List<PlanDTO> planDTOList) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (!planDTOList.isEmpty()) {
            responseDTO.setPagination(searchDTO.getPagination());
            responseDTO.setDetails(existPlans);
        } else {
            responseDTO.setDetails(notExist);
        }

        responseDTO.setResult(planDTOList);

        return responseDTO;
    }

    /**
     * 단일 카드 반환을 위한 ResponseDTO 세팅
     * 플랜 상세 조회 || 플랜 추가 시 사용된다.
     */
    private ResponseDTO setResponseDTO(BasicPlanDTO planDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (planDTO != null) {
            responseDTO.setDetails(insertedPlan);
        }
        responseDTO.setResult(planDTO);

        return responseDTO;
    }
}
