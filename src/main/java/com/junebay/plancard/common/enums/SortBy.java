package com.junebay.plancard.common.enums;

/**
 * @author : IAN
 * @date : 2025-04-18
 * @description : 조회 시 정렬할 컬럼을 명시한 ENUM
 */
public enum SortBy {

    CARD_TITLE("card_title"),
    RATING("rating"),
    CREATED_DATE("created_date"),
    TITLE("title");     /* 국가(countries) 조회 시에 정렬기준이 countryTitle이 아니라 title 이므로 해당 필드를 추가함 */

    private final String column;

    SortBy(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    /**
     * 클라이언트에서 정렬기준을 "cardTitle" 이라고 전달받았을때 해당 메서드를 통해 스네이크 케이스로 변경한다.
     * 변경된 스네이크 케이스 문자열을 이넘의 column값과 대소문자 구분없이 비교하여 같으면 Enum을 반환하고, 다르면 예외를 반환한다.
     *  ex) "cardTitle" -> "CARD_TITLE" / "rating" -> "RATING"
     * @param sortByStr
     * @return
     */
    public static SortBy from(String sortByStr) {

        String convertedToSnakeCase = convertToSnakeCase(sortByStr);
        for (SortBy sb : SortBy.values()) {
            if (sb.name().equalsIgnoreCase(convertedToSnakeCase)) {
                return sb;
            }
        }
        throw new IllegalArgumentException("Unknown sortBy : " + sortByStr);
    }

    /**
     * 카멜케이스를 대문자 스네이크 케이스로 변경한다.
     * @param sortByStr
     * @return
     */
    private static String convertToSnakeCase(String sortByStr) {
        return sortByStr.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase();
    }
}
