package com.company.gum.command;

public enum CommandType {

    SIGN_UP(new SignUpCommand()),

    VERIFICATION(new VerificationCommand());

    CommandType(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
