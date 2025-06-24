package com.junebay.plancard.plan.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


/**
 * 내 플랜 등록을 위한 Plan DTO
 */
@Getter
@Setter
public class BasicPlanDTO {

    private long planId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int duration;

    public void setDuration(int duration) {
        try {
            if (this.startDate != null && this.endDate != null) {
                double days = ChronoUnit.DAYS.between(this.startDate, this.endDate);
                this.duration = (int) days + 1;
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
        }
    }
}
