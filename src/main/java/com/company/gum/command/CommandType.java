package com.company.gum.command;

public enum CommandType {

    SIGN_UP(new SignUpCommand()),
    VERIFICATION(new VerificationCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
