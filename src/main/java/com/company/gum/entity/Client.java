package com.company.gum.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

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
        setMail(builder.mail);
        setVerification(builder.verification);
        setPhoto(builder.photo);
        setRegisterDate(builder.registerDate);
        setDiscount(builder.discount);
        setDiscountLevel(builder.discountLevel);
        setPhone(builder.phone);
        setMoney(builder.money);
        setActive(builder.isActive);
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
        private String mail;
        private boolean verification;
        private byte[] photo;
        private LocalDateTime registerDate;
        private Integer discount;
        private Integer discountLevel;
        private String phone;
        private BigDecimal money;
        private boolean isActive;

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
         * Sets the {@code photo} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code photo} to set
         * @return a reference to this Builder
         */
        public Builder photo(byte[] val) {
            photo = val;
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
         * Sets the {@code isActive} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code money} to set
         * @return a reference to this Builder
         */
        public Builder isActive(boolean val) {
            isActive = val;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Builder{");
            sb.append("id=").append(id);
            sb.append(", role=").append(role);
            sb.append(", login='").append(login).append('\'');
            sb.append(", password='").append(password).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", surname='").append(surname).append('\'');
            sb.append(", mail='").append(mail).append('\'');
            sb.append(", verification=").append(verification);
            sb.append(", registerDate=").append(registerDate);
            sb.append(", discount=").append(discount);
            sb.append(", discountLevel=").append(discountLevel);
            sb.append(", phone='").append(phone).append('\'');
            sb.append(", money=").append(money);
            sb.append(", isActive=").append(isActive);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder)) return false;

            Builder builder = (Builder) o;

            if (verification != builder.verification) return false;
            if (isActive != builder.isActive) return false;
            if (id != null ? !id.equals(builder.id) : builder.id != null) return false;
            if (role != builder.role) return false;
            if (login != null ? !login.equals(builder.login) : builder.login != null) return false;
            if (password != null ? !password.equals(builder.password) : builder.password != null) return false;
            if (name != null ? !name.equals(builder.name) : builder.name != null) return false;
            if (surname != null ? !surname.equals(builder.surname) : builder.surname != null) return false;
            if (mail != null ? !mail.equals(builder.mail) : builder.mail != null) return false;
            if (!Arrays.equals(photo, builder.photo)) return false;
            if (registerDate != null ? !registerDate.equals(builder.registerDate) : builder.registerDate != null)
                return false;
            if (discount != null ? !discount.equals(builder.discount) : builder.discount != null) return false;
            if (discountLevel != null ? !discountLevel.equals(builder.discountLevel) : builder.discountLevel != null)
                return false;
            if (phone != null ? !phone.equals(builder.phone) : builder.phone != null) return false;
            return money != null ? money.equals(builder.money) : builder.money == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (role != null ? role.hashCode() : 0);
            result = 31 * result + (login != null ? login.hashCode() : 0);
            result = 31 * result + (password != null ? password.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            result = 31 * result + (mail != null ? mail.hashCode() : 0);
            result = 31 * result + (verification ? 1 : 0);
            result = 31 * result + Arrays.hashCode(photo);
            result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
            result = 31 * result + (discount != null ? discount.hashCode() : 0);
            result = 31 * result + (discountLevel != null ? discountLevel.hashCode() : 0);
            result = 31 * result + (phone != null ? phone.hashCode() : 0);
            result = 31 * result + (money != null ? money.hashCode() : 0);
            result = 31 * result + (isActive ? 1 : 0);
            return result;
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
