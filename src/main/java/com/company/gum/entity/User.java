package com.company.gum.entity;

import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User extends AbstractEntity {

    /**
     * The role.
     */
    UserRole role;

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
     * The is active.
     */
    private boolean isActive;

    /**
     * The mail.
     */
    private String mail;

    /**
     * The verification.
     */
    private boolean verification;

    /**
     * The photo.
     */
    private byte[] photo;

    /**
     * The base 64 image.
     */
    private String base64Image;

    /**
     * Instantiates a new user.
     *
     * @param builder the builder
     */
    private User(Builder builder) {
        setId(builder.id);
        setRole(builder.role);
        setLogin(builder.login);
        setPassword(builder.password);
        setName(builder.name);
        setSurname(builder.surname);
        setActive(builder.isActive);
        setMail(builder.mail);
        setVerification(builder.verification);
        setPhoto(builder.photo);
        setBase64Image(builder.base64Image);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("role=").append(role);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", verification=").append(verification);
        sb.append(", photo=").append(Arrays.toString(photo));
        sb.append(", base64Image='").append(base64Image).append('\'');
        sb.append('}');
        return sb.toString();
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
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (isActive() != user.isActive()) {
            return false;
        }
        if (isVerification() != user.isVerification()) {
            return false;
        }
        if (getRole() != user.getRole()) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) {
            return false;
        }
        if (getMail() != null ? !getMail().equals(user.getMail()) : user.getMail() != null) {
            return false;
        }
        if (!Arrays.equals(getPhoto(), user.getPhoto())) {
            return false;
        }
        return getBase64Image() != null ? getBase64Image().equals(user.getBase64Image())
                : user.getBase64Image() == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (isVerification() ? 1 : 0);
        result = 31 * result + Arrays.hashCode(getPhoto());
        result = 31 * result + (getBase64Image() != null ? getBase64Image().hashCode() : 0);
        return result;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Gets the mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the mail.
     *
     * @param mail the new mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Checks if is verification.
     *
     * @return true, if is verification
     */
    public boolean isVerification() {
        return verification;
    }

    /**
     * Sets the verification.
     *
     * @param verification the new verification
     */
    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    /**
     * Gets the photo.
     *
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets the photo.
     *
     * @param photo the new photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * Gets the base 64 image.
     *
     * @return the base 64 image
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * Sets the base 64 image.
     *
     * @param base64Image the new base 64 image
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * The Enum UserRole.
     */
    public enum UserRole {

        /**
         * The admin.
         */
        ADMIN,
        /**
         * The trainer.
         */
        TRAINER,
        /**
         * The client.
         */
        CLIENT
    }

    /**
     * {@code User} builder static inner class.
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
         * The is active.
         */
        private boolean isActive;

        /**
         * The mail.
         */
        private String mail;

        /**
         * The verification.
         */
        private boolean verification;

        /**
         * The photo.
         */
        private byte[] photo;

        /**
         * The base 64 image.
         */
        private String base64Image;

        /**
         * Instantiates a new builder.
         */
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
         * Returns a {@code User} built from the parameters previously set.
         *
         * @return a {@code User} built with parameters of this
         * {@code User.Builder}
         */
        public User build() {
            return new User(this);
        }
    }
}
