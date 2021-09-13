package com.company.gum.entity;

import java.math.BigDecimal;

public enum Duration {

    WEEK(new BigDecimal(35), 7),
    MONTH(new BigDecimal(140), 30),
    HALF_YEAR(new BigDecimal(820), 182),
    YEAR(new BigDecimal(1530), 365);

    private BigDecimal price;
    private int day;

    Duration(BigDecimal price, int day) {
        this.price = price;
        this.day = day;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int day() {
        return day;
    }

}
