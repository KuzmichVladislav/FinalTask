package com.company.gum.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Client extends User {

    private LocalDateTime registerDate;
    private Integer discount;
    private Integer discountLevel;
    private String phone;
    private BigDecimal money;

    public Client() {
        role = UserRole.CLIENT;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(Integer discountLevel) {
        this.discountLevel = discountLevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Client client = (Client) o;

        if (!getRegisterDate().equals(client.getRegisterDate())) {
            return false;
        }
        if (!getDiscount().equals(client.getDiscount())) {
            return false;
        }
        if (!getDiscountLevel().equals(client.getDiscountLevel())) {
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(client.getPhone()) : client.getPhone() != null) {
            return false;
        }
        return getMoney().equals(client.getMoney());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getRegisterDate().hashCode();
        result = 31 * result + getDiscount().hashCode();
        result = 31 * result + getDiscountLevel().hashCode();
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + getMoney().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("registerDate=").append(registerDate);
        sb.append(", discount=").append(discount);
        sb.append(", discountLevel=").append(discountLevel);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", money=").append(money);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
