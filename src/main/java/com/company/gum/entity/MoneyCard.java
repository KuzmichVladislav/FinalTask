package com.company.gum.entity;

import java.math.BigDecimal;

public class MoneyCard {
    private String cardNumber;
    private BigDecimal cardAmount;

    public MoneyCard() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyCard)) return false;

        MoneyCard moneyCard = (MoneyCard) o;

        if (getCardNumber() != null ? !getCardNumber().equals(moneyCard.getCardNumber()) : moneyCard.getCardNumber() != null)
            return false;
        return getCardAmount() != null ? getCardAmount().equals(moneyCard.getCardAmount()) : moneyCard.getCardAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getCardNumber() != null ? getCardNumber().hashCode() : 0;
        result = 31 * result + (getCardAmount() != null ? getCardAmount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoneyCard{");
        sb.append("cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardAmount=").append(cardAmount);
        sb.append('}');
        return sb.toString();
    }
}
