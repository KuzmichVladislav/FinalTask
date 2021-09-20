package com.company.gum.command;

import com.company.gum.command.impl.*;
import com.company.gum.command.impl.client.ChangePasswordCommand;
import com.company.gum.command.impl.client.EditClientProfileCommand;
import com.company.gum.command.impl.client.NewOrderCommand;
import com.company.gum.command.impl.client.RefillMoneyCommand;
import com.company.gum.command.impl.comment.CreateNewCommentCommand;
import com.company.gum.command.impl.comment.DeleteCommentCommand;
import com.company.gum.command.impl.comment.EditCommentCommand;
import com.company.gum.command.impl.comment.FindAllActiveCommentsCommand;
import com.company.gum.command.impl.order.ClientOrderDetailCommand;
import com.company.gum.command.impl.order.CreateOrderCommand;
import com.company.gum.command.impl.order.DeleteOrderByClientCommand;
import com.company.gum.command.impl.order.FindAllOrdersByClientCommand;
import com.company.gum.command.impl.trainer.EditDescriptionCommand;
import com.company.gum.command.impl.trainer.EditTrainerProfileCommand;

public enum CommandType {

    SIGN_UP(new SignUpCommand()),
    VERIFICATION(new VerificationCommand()),
    CHANGE_LOCALE(new LocaleCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPLOAD_IMAGE(new UploadImageCommand()),
    EDIT_CLIENT_PROFILE(new EditClientProfileCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    NEW_ORDER(new NewOrderCommand()),
    CREATE_NEW_ORDER(new CreateOrderCommand()),
    DELETE_ORDER_BY_CLIENT(new DeleteOrderByClientCommand()),
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClientCommand()),
    CLIENT_ORDER_DETAIL(new ClientOrderDetailCommand()),
    CREATE_NEW_COMMENT(new CreateNewCommentCommand()),
    FIND_ALL_ACTIVE_COMMENTS(new FindAllActiveCommentsCommand()),
    DELETE_COMMENT(new DeleteCommentCommand()),
    EDIT_COMMENT(new EditCommentCommand()),
    REFILL_MONEY(new RefillMoneyCommand()),
    EDIT_TRAINER_PROFILE(new EditTrainerProfileCommand()),
    EDIT_DESCRIPTION(new EditDescriptionCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
