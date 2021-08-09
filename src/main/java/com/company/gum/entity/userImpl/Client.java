package com.company.gum.entity.userImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Client extends User {
    private LocalDateTime registerDate;
    private Integer discount;
    private Integer discountLevel;
    private String phone;
    private String mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (getRegisterDate() != null ? !getRegisterDate().equals(client.getRegisterDate()) : client.getRegisterDate() != null)
            return false;
        if (getDiscount() != null ? !getDiscount().equals(client.getDiscount()) : client.getDiscount() != null)
            return false;
        if (getDiscountLevel() != null ? !getDiscountLevel().equals(client.getDiscountLevel()) : client.getDiscountLevel() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(client.getPhone()) : client.getPhone() != null) return false;
        if (getMail() != null ? !getMail().equals(client.getMail()) : client.getMail() != null) return false;
        return getMoney() != null ? getMoney().equals(client.getMoney()) : client.getMoney() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
        result = 31 * result + (getDiscountLevel() != null ? getDiscountLevel().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getMoney() != null ? getMoney().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("registerDate=").append(registerDate);
        sb.append(", discount=").append(discount);
        sb.append(", discountLevel=").append(discountLevel);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", money=").append(money);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
