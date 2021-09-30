package com.company.gum.command;

import com.company.gum.command.impl.*;
import com.company.gum.command.impl.admin.*;
import com.company.gum.command.impl.client.ChangePasswordCommand;
import com.company.gum.command.impl.client.EditClientProfileCommand;
import com.company.gum.command.impl.client.NewOrderCommand;
import com.company.gum.command.impl.client.RefillMoneyCommand;
import com.company.gum.command.impl.comment.CreateNewCommentCommand;
import com.company.gum.command.impl.comment.DeleteCommentCommand;
import com.company.gum.command.impl.comment.EditCommentCommand;
import com.company.gum.command.impl.comment.ShowAllActiveCommentsCommand;
import com.company.gum.command.impl.order.*;
import com.company.gum.command.impl.trainer.EditDescriptionCommand;
import com.company.gum.command.impl.trainer.EditExperienceCommand;
import com.company.gum.command.impl.trainer.EditTrainerProfileCommand;

public enum CommandType {

    REGISTER(new RegisterCommand()), REGISTER_TRAINER(new RegisterTrainerCommand()),
    VERIFICATION(new VerificationCommand()), CHANGE_LOCALE(new LocaleCommand()), LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()), UPLOAD_IMAGE(new UploadImageCommand()),
    EDIT_CLIENT_PROFILE(new EditClientProfileCommand()), CHANGE_PASSWORD(new ChangePasswordCommand()),
    NEW_ORDER(new NewOrderCommand()), CREATE_NEW_ORDER(new CreateOrderCommand()),
    DELETE_ORDER_BY_CLIENT(new DeleteOrderByClientCommand()),
    SHOW_ALL_ORDERS_BY_CLIENT(new ShowAllOrdersByClientCommand()),
    SHOW_ALL_ORDERS_BY_TRAINER(new ShowAllOrdersByTrainerCommand()),
    SHOW_ALL_ORDERS_BY_ADMIN(new ShowAllOrdersByAdminCommand()), SHOW_ORDER_BY_CLIENT(new ShowOrderByClientCommand()),
    SHOW_ORDER_BY_TRAINER(new ShowOrderByTrainerCommand()), SHOW_ORDER_BY_ADMIN(new ShowOrderByAdminCommand()),
    CREATE_NEW_COMMENT(new CreateNewCommentCommand()), SHOW_ALL_ACTIVE_COMMENTS(new ShowAllActiveCommentsCommand()),
    DELETE_COMMENT(new DeleteCommentCommand()), EDIT_COMMENT(new EditCommentCommand()),
    REFILL_MONEY(new RefillMoneyCommand()), EDIT_TRAINER_PROFILE(new EditTrainerProfileCommand()),
    EDIT_ADMIN_PROFILE(new EditAdminProfileCommand()), EDIT_DESCRIPTION(new EditDescriptionCommand()),
    EDIT_EXPERIENCE(new EditExperienceCommand()), EDIT_NUTRITION(new EditNutritionCommand()),
    EDIT_EXERCISES(new EditExercisesCommand()), SHOW_USERS(new ShowUsersCommand()),
    UPDATE_ORDER_STATUS(new UpdateOrderStatusCommand()), DELETE_USER(new DeleteUserCommand()),
    RESTORE_USER(new RestoreUserCommand()), SHOW_USER_PROFILE(new ShowUserProfileCommand()),
    ASSIGN_DISCOUNT(new AssignDiscountCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
