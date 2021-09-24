package com.company.gum.entity;

public class Admin extends User {

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
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append("role=").append(role);
        sb.append('}');
        return sb.toString();
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
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * Sets the {@code role} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code role} to set
         * @return a reference to this Builder
         */
        public Builder role(UserRole val) {
            role = val;
            return this;
        }

        /**
         * Sets the {@code login} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code login} to set
         * @return a reference to this Builder
         */
        public Builder login(String val) {
            login = val;
            return this;
        }

        /**
         * Sets the {@code password} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code password} to set
         * @return a reference to this Builder
         */
        public Builder password(String val) {
            password = val;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder name(String val) {
            name = val;
            return this;
        }

        /**
         * Sets the {@code surname} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code surname} to set
         * @return a reference to this Builder
         */
        public Builder surname(String val) {
            surname = val;
            return this;
        }

        /**
         * Sets the {@code mail} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code mail} to set
         * @return a reference to this Builder
         */
        public Builder mail(String val) {
            mail = val;
            return this;
        }

        /**
         * Sets the {@code verification} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code verification} to set
         * @return a reference to this Builder
         */
        public Builder verification(boolean val) {
            verification = val;
            return this;
        }

        /**
         * Sets the {@code photo} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code photo} to set
         * @return a reference to this Builder
         */
        public Builder photo(byte[] val) {
            photo = val;
            return this;
        }

        /**
         * Returns a {@code Admin} built from the parameters previously set.
         *
         * @return a {@code Admin} built with parameters of this {@code Admin.Builder}
         */
        public Admin build() {
            return new Admin(this);
        }
    }
}
