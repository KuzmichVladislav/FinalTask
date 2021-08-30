package com.company.gum.command;

import com.company.gum.command.impl.LocaleCommand;
import com.company.gum.command.impl.SignUpCommand;
import com.company.gum.command.impl.VerificationCommand;

public enum CommandType {

    SIGN_UP(new SignUpCommand()),
    VERIFICATION(new VerificationCommand()),
    CHANGE_LOCALE(new LocaleCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
