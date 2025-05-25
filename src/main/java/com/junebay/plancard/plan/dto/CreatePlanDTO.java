package com.junebay.plancard.plan.dto;

import com.junebay.plancard.common.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * 내 플랜 등록을 위한 DTO
 */
@Getter
@Setter
public class CreatePlanDTO {

    private long planId;
    private String title;
    private String startDate;
    private String endDate;
    private int duration;

    public CreatePlanDTO(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate start = LocalDate.parse(this.startDate, formatter);
            LocalDate end = LocalDate.parse(this.endDate, formatter);

            int days = Period.between(start, end).getDays();
            this.duration = days + 1;

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
        }
    }


}
