package com.junebay.plancard.plan.dto;

import com.junebay.plancard.common.vo.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : IAN
 * @date : 2025-06-30
 * @description : 탐험 플랜 태그 목록 클래스. 탐험 플랜에서 조회되는 태그(Tag)는 my_card_tags와 다르다.
 * 플랜에 해당하는 categories 와 theme 목록 중 특정 기준으로 추출해 Tag 클래스의 형태로 만들어진 정보이다.
 * isTheme이 true면 theme 정보. false면 category 정보.
 */
@Getter
@Setter
public class ExplorePlanTagDTO extends Tag {

    private boolean isTheme;
}
