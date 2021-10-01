package com.company.gum.entity;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc

/**
 * The Enum Duration.
 */
public enum Duration {

    /**
     * The week.
     */
    WEEK(new BigDecimal(35), 7),
    /**
     * The month.
     */
    MONTH(new BigDecimal(140), 30),
    /**
     * The half year.
     */
    HALF_YEAR(new BigDecimal(820), 182),
    /**
     * The year.
     */
    YEAR(new BigDecimal(1530), 365);

    /**
     * The price.
     */
    private BigDecimal price;

    /**
     * The day.
     */
    private int day;

    /**
     * Instantiates a new duration.
     *
     * @param price the price
     * @param day   the day
     */
    Duration(BigDecimal price, int day) {
        this.price = price;
        this.day = day;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Day.
     *
     * @return the int
     */
    public int day() {
        return day;
    }

}
