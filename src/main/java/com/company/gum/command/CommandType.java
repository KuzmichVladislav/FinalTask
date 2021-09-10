package com.company.gum.command;

import com.company.gum.command.impl.*;
import com.company.gum.command.impl.client.ChangePassword;
import com.company.gum.command.impl.client.EditClientProfile;
import com.company.gum.command.impl.client.NewOrder;

public enum CommandType {

    SIGN_UP(new SignUpCommand()),
    VERIFICATION(new VerificationCommand()),
    CHANGE_LOCALE(new LocaleCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPLOAD_IMAGE(new _UploadImageCommand2()),
    EDIT_CLIENT_PROFILE(new EditClientProfile()),
    CHANGE_PASSWORD(new ChangePassword()),
    NEW_ORDER(new NewOrder());


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
