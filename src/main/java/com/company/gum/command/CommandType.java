package com.company.gum.command;

import com.company.gum.command.comment.CreateNewComment;
import com.company.gum.command.comment.DeleteComment;
import com.company.gum.command.comment.EditComment;
import com.company.gum.command.comment.FindAllActiveComments;
import com.company.gum.command.impl.*;
import com.company.gum.command.impl.client.ChangePassword;
import com.company.gum.command.impl.client.EditClientProfile;
import com.company.gum.command.impl.client.NewOrder;
import com.company.gum.command.order.ClientOrderDetail;
import com.company.gum.command.order.CreateOrder;
import com.company.gum.command.order.DeleteOrderByClient;
import com.company.gum.command.order.FindAllOrdersByClient;


public enum CommandType {

    SIGN_UP(new SignUpCommand()),
    VERIFICATION(new VerificationCommand()),
    CHANGE_LOCALE(new LocaleCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPLOAD_IMAGE(new UploadImageCommand()),
    EDIT_CLIENT_PROFILE(new EditClientProfile()),
    CHANGE_PASSWORD(new ChangePassword()),
    NEW_ORDER(new NewOrder()),
    CREATE_NEW_ORDER(new CreateOrder()),
    DELETE_ORDER_BY_CLIENT(new DeleteOrderByClient()),
    FIND_ALL_ORDERS_BY_CLIENT(new FindAllOrdersByClient()),
    CLIENT_ORDER_DETAIL(new ClientOrderDetail()),
    CREATE_NEW_COMMENT(new CreateNewComment()),
    FIND_ALL_ACTIVE_COMMENTS(new FindAllActiveComments()),
    DELETE_COMMENT(new DeleteComment()),
    EDIT_COMMENT(new EditComment());


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
