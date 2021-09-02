package com.company.gum.entity;

public class User extends AbstractEntity {

	UserRole role;
	private String login;
	private String password;
	private String name;
	private String surname;
	private boolean isActive;
	private String profileImage;
	private String mail;
	private boolean verification;

	private User(Builder builder) {
		setId(builder.id);
		setRole(builder.role);
		setLogin(builder.login);
		setPassword(builder.password);
		setName(builder.name);
		setSurname(builder.surname);
		setActive(builder.isActive);
		setProfileImage(builder.profileImage);
		setMail(builder.mail);
		setVerification(builder.verification);
	}

	public User() {
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public boolean isVerification() {
		return verification;
	}

	public void setVerification(boolean verification) {
		this.verification = verification;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User user = (User) o;

		if (isActive() != user.isActive()) {
			return false;
		}
		if (!getLogin().equals(user.getLogin())) {
			return false;
		}
		if (!getPassword().equals(user.getPassword())) {
			return false;
		}
		if (getRole() != user.getRole()) {
			return false;
		}
		if (!getName().equals(user.getName())) {
			return false;
		}
		if (!getSurname().equals(user.getSurname())) {
			return false;
		}
		return getMail().equals(user.getMail());
	}

	@Override
	public int hashCode() {
		int result = getLogin().hashCode();
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getRole().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getSurname().hashCode();
		result = 31 * result + (isActive() ? 1 : 0);
		result = 31 * result + getMail().hashCode();
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("login='").append(login).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", role=").append(role);
		sb.append(", name='").append(name).append('\'');
		sb.append(", surname='").append(surname).append('\'');
		sb.append(", isActive=").append(isActive);
		sb.append(", profileImage='").append(profileImage).append('\'');
		sb.append(", mail='").append(mail).append('\'');
		sb.append(", verification=").append(verification);
		sb.append('}');
		return sb.toString();
	}

	public enum UserRole {
		ADMIN,
		TRAINER,
		CLIENT
	}

	/**
	 * {@code User} builder static inner class.
	 */
	public static final class Builder {

		private Integer id;
		private UserRole role;
		private String login;
		private String password;
		private String name;
		private String surname;
		private boolean isActive;
		private String profileImage;
		private String mail;
		private boolean verification;

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
