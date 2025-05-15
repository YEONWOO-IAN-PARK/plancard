package com.junebay.plancard.card.controller;

import com.junebay.plancard.card.service.CountryService;
import com.junebay.plancard.common.dto.RequestDTO;
import com.junebay.plancard.common.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : IAN
 * @date : 2025-05-14
 * @description : 국가 또는 도시 관련 RestController 클래ㅡㅅ
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/countries")
public class CountryRestController {

    private final CountryService countryService;

    /**
     * 국가 조회 요청핸들러메서드
     */
    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> countries(@RequestBody RequestDTO requestDTO) {
        // TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
        ResponseDTO responseDTO = countryService.selectCountries(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
