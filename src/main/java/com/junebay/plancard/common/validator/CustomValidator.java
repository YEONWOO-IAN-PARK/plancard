package com.junebay.plancard.common.validator;

import com.junebay.plancard.card.dto.CardDTO;
import com.junebay.plancard.card.dto.MyCardDTO;
import com.junebay.plancard.card.dto.MyCardTagDTO;
import com.junebay.plancard.common.dto.SearchDTO;
import com.junebay.plancard.common.enums.StatusCode;
import com.junebay.plancard.common.exception.BadRequestException;
import com.junebay.plancard.common.exception.NoContentException;
import com.junebay.plancard.common.exception.NotFoundException;
import com.junebay.plancard.common.util.CommonUtil;
import com.junebay.plancard.common.vo.Filter;
import com.junebay.plancard.image.dto.MyCardImageDTO;
import com.junebay.plancard.plan.dto.PlanDayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author : IAN
 * @date : 2025-04-22
 * @description : 유효성 검사 클래스. (실질적인 예외처리는 GlobalExceptionHandler 가 처리)
 */
@Component
@RequiredArgsConstructor
public class CustomValidator {

    /**
     * SearchDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateRequest(SearchDTO searchDTO) {
        if (searchDTO.getPagination() == null || searchDTO.getSort() == null) throw new BadRequestException(StatusCode.BAD_REQUEST);
    }

    /**
     * SearchDTO를 받아 사용자 요청 중 검색 Filter의 누락 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateRequestForFilter(SearchDTO searchDTO) {
        Filter filter = searchDTO.getFilter();
        if (filter == null
            || filter.getScrap() == null
            || filter.getDay() == null
            || filter.getLike() == null) {
            throw new BadRequestException(StatusCode.BAD_REQUEST);
        }
    }

    /**
     * SearchDTO를 받아 사용자 요청의 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     */
    public void validateCardOne(CardDTO cardDTO) {
        if (cardDTO == null || cardDTO.getCardId() == 0) throw new NotFoundException(StatusCode.NOT_FOUND);
    }

    /**
     * CardDTO를 받아 CardDTO의 스크랩유무를 위한 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     * @param scrapCriteria 스크랩 요청 기준   true : 스크랩 || false : 스크랩 해제
     */
    public void validateCardScrap(CardDTO cardDTO, boolean scrapCriteria) {

        if (scrapCriteria) {
            // 카드 스크랩 요청 시, 프로세스 흐름상 요청 할 수 없는 구조라 예외로 지정한다.
            if (cardDTO.isScrap()) throw new NoContentException(StatusCode.NO_CONTENT);
        }
    }

    /**
     * 전달받은 tagId로 CardDTO에 해당 태그가 존재하는지 유효성 검사 시행
     * 유효성 검사를 통과하지 못할 시, 각 상황에 맞는 예외를 반환한다.
     * @param cardDTO 내 카드 ID로 찾은 CardDTO(MyCardDTO)
     * @param tagId 태그 ID
     */
    public void validateCardTag(CardDTO cardDTO, long tagId) {

        if (tagId > 0 && cardDTO instanceof MyCardDTO myCardDTO) {
            boolean isExist = containsTagId(myCardDTO.getMyTagList(), tagId);
            if (!isExist) throw new NotFoundException(StatusCode.NOT_FOUND);
        }
    }

    /**
     * 내 카드의 태그 목록의 ID와 클라이언트에게 전달받은 tagId를 비교하여 태그 존재 유무를 파악
     */
    public boolean containsTagId(List<MyCardTagDTO> tagList, long tagId) {
        return tagList.stream().anyMatch(tagDTO -> tagDTO.getTagId() == tagId);
    }

    /**
     * 전달받은 이미지 파일의 유효성 검사
     */
    public void validateCardImage(MultipartFile imageFile) {
        final long MAX_FILE_SIZE = 5 * 1024 * 1024;
        final int MAX_FILENAME_LENGTH = 255;

        // 빈 파일 검사
        if (imageFile.isEmpty()) throw new IllegalArgumentException("파일이 비어있습니다.");

        // 파일의 용량 검사 (0이상 ~ 5mb 이하)
        if (imageFile.getSize() <= 0 || imageFile.getSize() > MAX_FILE_SIZE) throw new IllegalArgumentException("파일 용량은 0보다 크고 5MB 이하여야 합니다.");

        // 파일명 길이 검사 (255 자 이하)
        String fileName = imageFile.getOriginalFilename();
        int byteLength = 0;
        if (fileName != null && !fileName.isEmpty()) {
            byteLength = fileName.getBytes(StandardCharsets.UTF_8).length;
        }
        if (fileName == null || byteLength == 0 || byteLength > MAX_FILENAME_LENGTH) throw new IllegalArgumentException("파일명이 너무 깁니다.");

        // 파일 확장자 검사 (jpg/jpeg/png/webp)
        if (!fileName.matches("(?i)^.*\\.(jpg|jpeg|png|webp)$")) throw new IllegalArgumentException("허용된 확장자(jpg, jpeg, png, webp)만 가능합니다.");

        // 파일 MIME 타입 검사 (image/jpeg, image/png 등)
        String contentType = imageFile.getContentType();
        if (contentType == null || !(contentType.equalsIgnoreCase("image/jpeg")
                || contentType.equalsIgnoreCase("image/jpg")
                || contentType.equalsIgnoreCase("image/png")
                || contentType.equalsIgnoreCase("image/webp")
        )) {
            throw new IllegalArgumentException("허용된 이미지 타입만 가능합니다.");
        }

        // 실제 이미지 여부 확인
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            if (image == null) {
                throw new IllegalArgumentException("이미지 파일이 아닙니다.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 파일을 읽는 데 실패했습니다.");
        }
    }

    /**
     * 전달받은 내 카드 이미지 ID가 존재하는지 검사
     */
    public void validateMyCardImage(List<MyCardImageDTO> myImageList, long myCardImageId) {
        boolean isExist = myImageList.stream().anyMatch(myCardImageDTO -> myCardImageDTO.getMyImageId() == myCardImageId);
        if (myCardImageId == 0 || !isExist) {
            throw new NotFoundException(StatusCode.NOT_FOUND);
        }
    }

    /**
     * 전달받은 메인 이미지 등록 요청 타입 ( "C" : 탐험카드 || "M" : 내카드)이 올바른 값인지 검사
     */
    public void validateMainImageType(String mainImageType) {
        if (CommonUtil.isNullOrBlank(mainImageType)|| (!"C".equals(mainImageType) && !"M".equals(mainImageType))) {
            throw new BadRequestException(StatusCode.BAD_REQUEST);
        }
    }

    /**
     * 전달받은 문자열 시작일과 종료일의 유효성 검사.
     * 허용형식은 (yyyy-MM-dd)
     */
    public void validateDateRange(LocalDate startDate, LocalDate endDate) {

        try {
            // 종료일이 시작일보다 빠른지 검증
            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("종료일은 시작일보다 이전일 수 없습니다.");
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다.");
        }

    }

    /**
     * 전달받은 PlanDay 목록의 PlanDay의 유효성 검사
     */
    public void validatePlanDays(List<PlanDayDTO> planDayList) {
        if (planDayList == null) throw new BadRequestException(StatusCode.BAD_REQUEST);
    }

}
