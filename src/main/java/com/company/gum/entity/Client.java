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

    private Client(Builder builder) {
        setId(builder.id);
        setRole(builder.role);
        setLogin(builder.login);
        setPassword(builder.password);
        setName(builder.name);
        setSurname(builder.surname);
        setProfileImage(builder.profileImage);
        setMail(builder.mail);
        setVerification(builder.verification);
        setRegisterDate(builder.registerDate);
        setDiscount(builder.discount);
        setDiscountLevel(builder.discountLevel);
        setPhone(builder.phone);
        setMoney(builder.money);
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

    /**
     * {@code Client} builder static inner class.
     */
    public static final class Builder {

        private Integer id;
        private UserRole role;
        private String login;
        private String password;
        private String name;
        private String surname;
        private String profileImage;
        private String mail;
        private boolean verification;
        private LocalDateTime registerDate;
        private Integer discount;
        private Integer discountLevel;
        private String phone;
        private BigDecimal money;

        public Builder() {
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code role} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code role} to set
         * @return a reference to this Builder
         */
        public Builder role(UserRole val) {
            role = val;
            return this;
        }

        /**
         * Sets the {@code login} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code login} to set
         * @return a reference to this Builder
         */
        public Builder login(String val) {
            login = val;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder password(String val) {
            password = val;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String val) {
            name = val;
            return this;
        }

        /**
         * Sets the {@code surname} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code surname} to set
         * @return a reference to this Builder
         */
        public Builder surname(String val) {
            surname = val;
            return this;
        }

        /**
         * Sets the {@code profileImage} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code profileImage} to set
         * @return a reference to this Builder
         */
        public Builder profileImage(String val) {
            profileImage = val;
            return this;
        }

        /**
         * Sets the {@code mail} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code mail} to set
         * @return a reference to this Builder
         */
        public Builder mail(String val) {
            mail = val;
            return this;
        }

        /**
         * Sets the {@code verification} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code verification} to set
         * @return a reference to this Builder
         */
        public Builder verification(boolean val) {
            verification = val;
            return this;
        }

        /**
         * Sets the {@code registerDate} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code registerDate} to set
         * @return a reference to this Builder
         */
        public Builder registerDate(LocalDateTime val) {
            registerDate = val;
            return this;
        }

        /**
         * Sets the {@code discount} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code discount} to set
         * @return a reference to this Builder
         */
        public Builder discount(Integer val) {
            discount = val;
            return this;
        }

        /**
         * Sets the {@code discountLevel} and returns a reference to this
         * Builder so that the methods can be chained together.
         *
         * @param val the {@code discountLevel} to set
         * @return a reference to this Builder
         */
        public Builder discountLevel(Integer val) {
            discountLevel = val;
            return this;
        }

        /**
         * Sets the {@code phone} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code phone} to set
         * @return a reference to this Builder
         */
        public Builder phone(String val) {
            phone = val;
            return this;
        }

        /**
         * Sets the {@code money} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code money} to set
         * @return a reference to this Builder
         */
        public Builder money(BigDecimal val) {
            money = val;
            return this;
        }

        /**
         * Returns a {@code Client} built from the parameters previously set.
         *
         * @return a {@code Client} built with parameters of this
         * {@code Client.Builder}
         */
        public Client build() {
            return new Client(this);
        }
    }
}
