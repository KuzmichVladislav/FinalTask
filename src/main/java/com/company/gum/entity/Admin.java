package com.company.gum.entity;

import java.util.Arrays;

public class Admin extends User {

    private String mail;

    public Admin() {
        role = UserRole.ADMIN;
    }

    private Admin(Builder builder) {
        setId(builder.id);
        setRole(builder.role);
        setLogin(builder.login);
        setPassword(builder.password);
        setName(builder.name);
        setSurname(builder.surname);
        setMail(builder.mail);
        setVerification(builder.verification);
        setPhoto(builder.photo);
        setMail(builder.mail);
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * {@code Admin} builder static inner class.
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
         * Returns a {@code Admin} built from the parameters previously set.
         *
         * @return a {@code Admin} built with parameters of this
         * {@code Admin.Builder}
         */
        public Admin build() {
            return new Admin(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Builder)) return false;

            Builder builder = (Builder) o;

            if (verification != builder.verification) return false;
            if (id != null ? !id.equals(builder.id) : builder.id != null) return false;
            if (role != builder.role) return false;
            if (login != null ? !login.equals(builder.login) : builder.login != null) return false;
            if (password != null ? !password.equals(builder.password) : builder.password != null) return false;
            if (name != null ? !name.equals(builder.name) : builder.name != null) return false;
            if (surname != null ? !surname.equals(builder.surname) : builder.surname != null) return false;
            if (mail != null ? !mail.equals(builder.mail) : builder.mail != null) return false;
            return Arrays.equals(photo, builder.photo);
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
            sb.append('}');
            return sb.toString();
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
            return result;
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
    }
}
