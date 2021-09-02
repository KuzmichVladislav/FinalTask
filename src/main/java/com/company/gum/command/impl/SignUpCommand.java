package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.command.ParameterName;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.util.Validator;

import static com.company.gum.command.ParameterName.*;

public class SignUpCommand implements Command {

	private ClientService clientService = ClientServiceImpl.getInstance();

	@Override
	public String execute(SessionRequestContent requestContent) throws CommandException {
		String page;

		String login = requestContent.getParameterByName(USER_LOGIN).strip();
		String password = requestContent.getParameterByName(USER_PASSWORD).strip();
		String repeatedPassword = requestContent.getParameterByName(REPEAT_PASSWORD).strip();
		String name = requestContent.getParameterByName(USER_NAME).strip();
		String surname = requestContent.getParameterByName(USER_LAST_NAME).strip();
		String phone = requestContent.getParameterByName(USER_PHONE).strip();
		String mail = requestContent.getParameterByName(USER_MAIL).strip();

		boolean isValid = true;

		if (!Validator.checkLogin(login)) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_LOGIN);
			isValid = false;
		}
		if (!Validator.checkNameSurname(name) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_NAME);
			isValid = false;
		}
		if (!Validator.checkNameSurname(surname) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_LAST_NAME);
			isValid = false;
		}
		if (!Validator.checkPhone(phone) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_PHONE);
			isValid = false;
		}
		if (!Validator.checkMail(mail) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);// TODO: 9/1/2021
			isValid = false;
		}
		if (!Validator.checkPassword(password) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_PASSWORD);
			isValid = false;
		}
		if (!repeatedPassword.equals(password) && isValid) {
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.PASSWORDS_NOT_EQUAL);
			isValid = false;
		}

		try {
			if (isValid) {

				Client client = new Client.Builder().login(login)
						.password(password)
						.mail(mail)
						.name(name)
						.surname(surname)
						.phone(phone)
						.build();

				clientService.createClient(client);

				page = PagePath.CLIENT_CREATED;
			} else {
				page = PagePath.SIGN_UP;
			}
		} catch (ServiceException e) {
			page = PagePath.SIGN_UP;
			requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.LOGIN_ALREADY_EXIST);
		}
		return page;
	}
}
