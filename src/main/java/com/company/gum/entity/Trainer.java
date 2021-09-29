package com.company.gum.entity;

import java.time.LocalDateTime;

public class Trainer extends User {

    private LocalDateTime registerDate;
    private String phone;
    private String mail;
    private String description;
    private String experience;

    public Trainer() {
        role = UserRole.TRAINER;
    }

    private Trainer(Builder builder) {
        setId(builder.id);
        setRole(builder.role);
        setLogin(builder.login);
        setPassword(builder.password);
        setName(builder.name);
        setSurname(builder.surname);
        setMail(builder.mail);
        setVerification(builder.verification);
        setActive(builder.isActive);
        setPhoto(builder.photo);
        setBase64Image(builder.base64Image);
        setRegisterDate(builder.registerDate);
        setPhone(builder.phone);
        setMail(builder.mail);
        setDescription(builder.description);
        setExperience(builder.experience);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("registerDate=").append(registerDate);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", experience='").append(experience).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (getRegisterDate() != null ? !getRegisterDate().equals(trainer.getRegisterDate()) : trainer.getRegisterDate() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(trainer.getPhone()) : trainer.getPhone() != null) return false;
        if (getMail() != null ? !getMail().equals(trainer.getMail()) : trainer.getMail() != null) return false;
        if (getDescription() != null ? !getDescription().equals(trainer.getDescription()) : trainer.getDescription() != null)
            return false;
        return getExperience() != null ? getExperience().equals(trainer.getExperience()) : trainer.getExperience() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getExperience() != null ? getExperience().hashCode() : 0);
        return result;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * {@code Trainer} builder static inner class.
     */
    public static final class Builder {
        private Integer id;
        private UserRole role;
        private String login;
        private String password;
        private String name;
        private String surname;
        private String mail;
        private String description;
        private String experience;
        private boolean verification;
        private boolean isActive;
        private byte[] photo;
        private String base64Image;
        private LocalDateTime registerDate;
        private String phone;

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
         * Sets the {@code description} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code description} to set
         * @return a reference to this Builder
         */
        public Builder description(String val) {
            description = val;
            return this;
        }

        /**
         * Sets the {@code experience} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code experience} to set
         * @return a reference to this Builder
         */
        public Builder experience(String val) {
            experience = val;
            return this;
        }

        /**
         * Returns a {@code Trainer} built from the parameters previously set.
         *
         * @return a {@code Trainer} built with parameters of this {@code Trainer.Builder}
         */
        public Trainer build() {
            return new Trainer(this);
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
         * Sets the {@code isActive} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code isActive} to set
         * @return a reference to this Builder
         */
        public Builder isActive(boolean val) {
            isActive = val;
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
         * Sets the {@code base64Image} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code base64Image} to set
         * @return a reference to this Builder
         */
        public Builder base64Image(String val) {
            base64Image = val;
            return this;
        }

        /**
         * Sets the {@code registerDate} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code registerDate} to set
         * @return a reference to this Builder
         */
        public Builder registerDate(LocalDateTime val) {
            registerDate = val;
            return this;
        }

        /**
         * Sets the {@code phone} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param val the {@code phone} to set
         * @return a reference to this Builder
         */
        public Builder phone(String val) {
            phone = val;
            return this;
        }
    }
}
