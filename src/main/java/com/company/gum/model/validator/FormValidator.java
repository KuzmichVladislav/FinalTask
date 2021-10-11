package com.company.gum.model.validator;

/**
 * The Class FormValidator.
 *
 * @author Vladislav Kuzmich
 */
public class FormValidator {

	/**
	 * The Constant LOGIN_REGEX.
	 */
	private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";

	/**
	 * The Constant PASSWORD_REGEX.
	 */
	private static final String PASSWORD_REGEX = "^[\\w_]{6,18}$";

	/**
	 * The Constant NAME_SURNAME_REGEX.
	 */
	private static final String NAME_SURNAME_REGEX = "^[\\p{IsAlphabetic}\\-]{2,20}$";

	/**
	 * The Constant PHONE_REGEX.
	 */
	private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";

	/**
	 * The Constant MAIL_REGEX.
	 */
	private static final String MAIL_REGEX = "^([\\w_\\.-]+)@([\\w_\\.-]+)\\.([a-z\\.]{2,6})$";

	/**
	 * The Constant MONEY_REGEX.
	 */
	private static final String MONEY_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";

	/**
	 * The Constant DISCOUNT_REGEX.
	 */
	private static final String DISCOUNT_REGEX = "^[0-9]{1,2}(\\.[0-9]{1,2})?$";

	/**
	 * The instance.
	 */
	private static FormValidator instance;

	/**
	 * Instantiates a new form validator.
	 */
	private FormValidator() {
	}

	/**
	 * Gets the single instance of FormValidator.
	 *
	 * @return single instance of FormValidator
	 */
	public static FormValidator getInstance() {
		if (instance == null) {
			instance = new FormValidator();
		}
		return instance;
	}

	/**
	 * Check login.
	 *
	 * @param login the login
	 * @return true, if successful
	 */
	public boolean checkLogin(String login) {
		return login.matches(LOGIN_REGEX);
	}

	/**
	 * Check password.
	 *
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean checkPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}

	/**
	 * Check name surname.
	 *
	 * @param nameSurname the name surname
	 * @return true, if successful
	 */
	public boolean checkNameSurname(String nameSurname) {
		return nameSurname.matches(NAME_SURNAME_REGEX);
	}

	/**
	 * Check phone.
	 *
	 * @param phone the phone
	 * @return true, if successful
	 */
	public boolean checkPhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}

	/**
	 * Check mail.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean checkMail(String mail) {
		return mail.matches(MAIL_REGEX);
	}

	/**
	 * Check money.
	 *
	 * @param money the money
	 * @return true, if successful
	 */
	public boolean checkMoney(String money) {
		return money.matches(MONEY_REGEX);
	}

	/**
	 * Check discount.
	 *
	 * @param discount the discount
	 * @return true, if successful
	 */
	public boolean checkDiscount(String discount) {
		return discount.matches(DISCOUNT_REGEX);
	}

}
