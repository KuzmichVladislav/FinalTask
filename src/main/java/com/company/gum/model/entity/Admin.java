package com.company.gum.model.entity;

import java.util.Arrays;

/**
 * The Class Admin.
 *
 * @author Vladislav Kuzmich
 */
public class Admin extends User {

	/**
	 * Instantiates a new admin.
	 */
	public Admin() {
		setRole(UserRole.ADMIN);
	}

	/**
	 * Instantiates a new admin.
	 *
	 * @param builder the builder
	 */
	private Admin(Builder builder) {
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
	}

	/**
	 * {@code Admin} builder static inner class.
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
		 * Instantiates a new builder.
		 */
		public Builder() {
			// Instantiates a new builder.
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
			if (!(o instanceof Builder)) {
				return false;
			}

			Builder builder = (Builder) o;

			if (verification != builder.verification) {
				return false;
			}
			if (isActive != builder.isActive) {
				return false;
			}
			if (id != null ? !id.equals(builder.id) : builder.id != null) {
				return false;
			}
			if (role != builder.role) {
				return false;
			}
			if (login != null ? !login.equals(builder.login) : builder.login != null) {
				return false;
			}
			if (password != null ? !password.equals(builder.password) : builder.password != null) {
				return false;
			}
			if (name != null ? !name.equals(builder.name) : builder.name != null) {
				return false;
			}
			if (surname != null ? !surname.equals(builder.surname) : builder.surname != null) {
				return false;
			}
			if (mail != null ? !mail.equals(builder.mail) : builder.mail != null) {
				return false;
			}
			if (!Arrays.equals(photo, builder.photo)) {
				return false;
			}
			return base64Image != null ? base64Image.equals(builder.base64Image) : builder.base64Image == null;
		}

		/**
		 * Hash code.
		 *
		 * @return the int
		 */
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
			result = 31 * result + (isActive ? 1 : 0);
			result = 31 * result + Arrays.hashCode(photo);
			return result;
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
		 * Returns a {@code Admin} built from the parameters previously set.
		 *
		 * @return a {@code Admin} built with parameters of this
		 * {@code Admin.Builder}
		 */
		public Admin build() {
			return new Admin(this);
		}
	}
}
