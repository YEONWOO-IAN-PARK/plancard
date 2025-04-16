package com.junebay.plancard.common.dto;

import com.junebay.plancard.common.vo.Pagination;

import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-16
 * @description : 사용자 응답 DTO
 */
public class ResponseDTO {

    private int status;             /* 상태코드 */
    private String message;         /* 상태메세지 (ex. OK, ...) */
    private List<?> data;           /* 응답 데이터 */
    private Pagination pagination;  /* 페이지네이션 */
    private String detail;          /* 상세 응답 메시지 (ex.검색결과에 따른 목록을 가져왔습니다.) */
}
