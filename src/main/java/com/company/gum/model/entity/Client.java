package com.company.gum.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The Class Client.
 *
 * @author Vladislav Kuzmich
 */
public class Client extends User {

	/**
	 * The register date.
	 */
	private LocalDateTime registerDate;
	/**
	 * The discount.
	 */
	private Integer discount;
	/**
	 * The phone.
	 */
	private String phone;
	/**
	 * The money.
	 */
	private BigDecimal money;

	/**
	 * Instantiates a new client.
	 */
	public Client() {
		setRole(UserRole.CLIENT);
	}

	/**
	 * Instantiates a new client.
	 *
	 * @param builder the builder
	 */
	private Client(Builder builder) {
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
		setDiscount(builder.discount);
		setPhone(builder.phone);
		setMoney(builder.money);
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
		if (!(o instanceof Client)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Client client = (Client) o;

		if (getRegisterDate() != null ? !getRegisterDate().equals(client.getRegisterDate()) : client.getRegisterDate() != null) {
			return false;
		}
		if (getDiscount() != null ? !getDiscount().equals(client.getDiscount()) : client.getDiscount() != null) {
			return false;
		}
		if (getPhone() != null ? !getPhone().equals(client.getPhone()) : client.getPhone() != null) {
			return false;
		}
		return getMoney() != null ? getMoney().equals(client.getMoney()) : client.getMoney() == null;
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
		result = 31 * result + (getDiscount() != null ? getDiscount().hashCode() : 0);
		result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
		result = 31 * result + (getMoney() != null ? getMoney().hashCode() : 0);
		return result;
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
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
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
	 * Gets the money.
	 *
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * Sets the money.
	 *
	 * @param money the new money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * The Class Builder.
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
		 * The register date.
		 */
		private LocalDateTime registerDate;

		/**
		 * The discount.
		 */
		private Integer discount;

		/**
		 * The phone.
		 */
		private String phone;

		/**
		 * The money.
		 */
		private BigDecimal money;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			// Instantiates a new builder.
		}

		/**
		 * Id.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder id(Integer val) {
			id = val;
			return this;
		}

		/**
		 * Role.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder role(UserRole val) {
			role = val;
			return this;
		}

		/**
		 * Login.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder login(String val) {
			login = val;
			return this;
		}

		/**
		 * Password.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder password(String val) {
			password = val;
			return this;
		}

		/**
		 * Name.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder name(String val) {
			name = val;
			return this;
		}

		/**
		 * Surname.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder surname(String val) {
			surname = val;
			return this;
		}

		/**
		 * Mail.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder mail(String val) {
			mail = val;
			return this;
		}

		/**
		 * Verification.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder verification(boolean val) {
			verification = val;
			return this;
		}

		/**
		 * Checks if is active.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder isActive(boolean val) {
			isActive = val;
			return this;
		}

		/**
		 * Photo.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder photo(byte[] val) {
			photo = val;
			return this;
		}

		/**
		 * Base 64 image.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder base64Image(String val) {
			base64Image = val;
			return this;
		}

		/**
		 * Register date.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder registerDate(LocalDateTime val) {
			registerDate = val;
			return this;
		}

		/**
		 * Discount.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder discount(Integer val) {
			discount = val;
			return this;
		}

		/**
		 * Phone.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder phone(String val) {
			phone = val;
			return this;
		}

		/**
		 * Money.
		 *
		 * @param val the val
		 * @return the builder
		 */
		public Builder money(BigDecimal val) {
			money = val;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the client
		 */
		public Client build() {
			return new Client(this);
		}
	}
}
