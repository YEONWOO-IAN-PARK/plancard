package com.junebay.plancard.image.enums;

/**
 * @author : IAN
 * @date : 2025-05-12
 * @description : 카드 이미지 종류를 구분하기 위한 Enum
 */
public enum ImageType {

    CARD("card"),
    MY_CARD("my_card"),
    COUNTRY("country"),
    CITY("city");

    private final String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageType() {
        return imageType;
    }

    public static ImageType from(String value) {
        for (ImageType type : ImageType.values()) {
            if (type.getImageType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown image type: " + value);
    }
}
