package com.company.gum.model.entity;

import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class Trainer.
 */
public class Trainer extends User {

    /**
     * The register date.
     */
    private LocalDateTime registerDate;

    /**
     * The phone.
     */
    private String phone;

    /**
     * The mail.
     */
    private String mail;

    /**
     * The description.
     */
    private String description;

    /**
     * The experience.
     */
    private String experience;

    /**
     * Instantiates a new trainer.
     */
    public Trainer() {
        role = UserRole.TRAINER;
    }

    /**
     * Instantiates a new trainer.
     *
     * @param builder the builder
     */
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

    /**
     * Gets the register date.
     *
     * @return the register date
     */
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets the register date.
     *
     * @param registerDate the new register date
     */
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * Gets the phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone the new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the mail.
     *
     * @return the mail
     */
    @Override
    public String getMail() {
        return mail;
    }

    /**
     * Sets the mail.
     *
     * @param mail the new mail
     */
    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the experience.
     *
     * @return the experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Sets the experience.
     *
     * @param experience the new experience
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Trainer{"
                + "registerDate=" + registerDate
                + ", phone='" + phone + '\''
                + ", mail='" + mail + '\''
                + ", description='" + description + '\''
                + ", experience='" + experience + '\''
                + ", role=" + role
                + '}';
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trainer)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Trainer trainer = (Trainer) o;

        if (getRegisterDate() != null ? !getRegisterDate().equals(trainer.getRegisterDate()) : trainer.getRegisterDate() != null) {
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(trainer.getPhone()) : trainer.getPhone() != null) {
            return false;
        }
        if (getMail() != null ? !getMail().equals(trainer.getMail()) : trainer.getMail() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(trainer.getDescription()) : trainer.getDescription() != null) {
            return false;
        }
        return getExperience() != null ? getExperience().equals(trainer.getExperience()) : trainer.getExperience() == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
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

    /**
     * {@code Trainer} builder static inner class.
     */
    public static final class Builder {

        /**
         * The id.
         */
        private Integer id;

        /**
         * The role.
         */
        private UserRole role;

        /**
         * The login.
         */
        private String login;

        /**
         * The password.
         */
        private String password;

        /**
         * The name.
         */
        private String name;

        /**
         * The surname.
         */
        private String surname;

        /**
         * The mail.
         */
        private String mail;

        /**
         * The description.
         */
        private String description;

        /**
         * The experience.
         */
        private String experience;

        /**
         * The verification.
         */
        private boolean verification;

        /**
         * The is active.
         */
        private boolean isActive;

        /**
         * The photo.
         */
        private byte[] photo;

        /**
         * The base 64 image.
         */
        private String base64Image;

        /**
         * The register date.
         */
        private LocalDateTime registerDate;

        /**
         * The phone.
         */
        private String phone;

        /**
         * Instantiates a new builder.
         */
        public Builder() {
            // Instantiates a new builder.
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
         * Sets the {@code description} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code description} to set
         * @return a reference to this Builder
         */
        public Builder description(String val) {
            description = val;
            return this;
        }

        /**
         * Sets the {@code experience} and returns a reference to this Builder
         * so that the methods can be chained together.
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
         * @return a {@code Trainer} built with parameters of this
         * {@code Trainer.Builder}
         */
        public Trainer build() {
            return new Trainer(this);
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
         * Sets the {@code isActive} and returns a reference to this Builder so
         * that the methods can be chained together.
         *
         * @param val the {@code isActive} to set
         * @return a reference to this Builder
         */
        public Builder isActive(boolean val) {
            isActive = val;
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
         * Sets the {@code base64Image} and returns a reference to this Builder
         * so that the methods can be chained together.
         *
         * @param val the {@code base64Image} to set
         * @return a reference to this Builder
         */
        public Builder base64Image(String val) {
            base64Image = val;
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
    }
}
