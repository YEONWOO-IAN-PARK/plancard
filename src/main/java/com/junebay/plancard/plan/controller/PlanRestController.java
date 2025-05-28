package com.junebay.plancard.plan.controller;

import com.junebay.plancard.common.dto.ResponseDTO;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.plan.dto.CreatePlanDTO;
import com.junebay.plancard.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * 플랜 관련된 작업을 처리하는 RestController
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/plans")
@RestController
public class PlanRestController {

    private final PlanService planService;

    /**
     * 내 플랜 등록 요청
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> createMyPlan(@RequestBody CreatePlanDTO planDTO) {
        ResponseDTO responseDTO = planService.createMyPlan(planDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    /**
     * 내 플랜 목록 조회 요청
     */
    @PostMapping("/search/{planType}")
    public ResponseEntity<ResponseDTO> plans(@PathVariable String planType, @RequestBody SearchDTO searchDTO) {
        ResponseDTO responseDTO = planService.selectPlanList(planType, searchDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
