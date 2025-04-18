package com.junebay.plancard.card.enums;

/**
 * @author : IAN
 * @date : 2025-04-18
 * @description :
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

    public static SortBy from(String sortBy) {
        for (SortBy sb : SortBy.values()) {
            if (sb.name().equalsIgnoreCase(sortBy)) {
                return sb;
            }
        }
        throw new IllegalArgumentException("Unknown sortBy : " + sortBy);
    }
}
