package com.junebay.plancard.card.enums;

/**
 * @author : IAN
 * @date : 2025-04-18
 * @description : 조회 시 정렬할 컬럼을 명시한 ENUM
 */
public enum SortBy {

    CARD_TITLE("card_title"),
    RATING("rating"),
    CREATED_DATE("created_date");

    private final String column;

    SortBy(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    /**
     * 
     * @param sortByStr
     * @return
     */
    public static SortBy from(String sortByStr) {

        String convertedToCamelCase = convertToSnakeCase(sortByStr);
        for (SortBy sb : SortBy.values()) {
            if (sb.name().equalsIgnoreCase(convertedToCamelCase)) {
                return sb;
            }
        }
        throw new IllegalArgumentException("Unknown sortBy : " + sortByStr);
    }

    private static String convertToSnakeCase(String sortByStr) {
        // 예: "cardTitle" -> "CARD_TITLE" / "rating" -> "RATING"
        return sortByStr.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase();
    }
}
