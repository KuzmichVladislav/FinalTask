package com.company.gum.controller.command;

import com.company.gum.controller.command.impl.*;
import com.company.gum.controller.command.impl.admin.*;
import com.company.gum.controller.command.impl.client.ChangePasswordCommand;
import com.company.gum.controller.command.impl.client.EditClientProfileCommand;
import com.company.gum.controller.command.impl.client.NewOrderCommand;
import com.company.gum.controller.command.impl.client.RefillMoneyCommand;
import com.company.gum.controller.command.impl.comment.CreateNewCommentCommand;
import com.company.gum.controller.command.impl.comment.DeleteCommentCommand;
import com.company.gum.controller.command.impl.comment.EditCommentCommand;
import com.company.gum.controller.command.impl.comment.ShowAllActiveCommentsCommand;
import com.company.gum.controller.command.impl.order.*;
import com.company.gum.controller.command.impl.trainer.EditDescriptionCommand;
import com.company.gum.controller.command.impl.trainer.EditExperienceCommand;
import com.company.gum.controller.command.impl.trainer.EditTrainerProfileCommand;
import com.company.gum.controller.command.impl.trainer.ShowAllTrainerCommand;

/**
 * The Enum CommandType.
 *
 * @author Vladislav Kuzmich
 */
public enum CommandType {

	/**
	 * The register.
	 */
	REGISTER(new RegisterCommand()),
	/**
	 * The register trainer.
	 */
	REGISTER_TRAINER(new RegisterTrainerCommand()),
	/**
	 * The verification.
	 */
	VERIFICATION(new VerificationCommand()),
	/**
	 * The change locale.
	 */
	CHANGE_LOCALE(new LocaleCommand()),
	/**
	 * The login.
	 */
	LOGIN(new LoginCommand()),
	/**
	 * The logout.
	 */
	LOGOUT(new LogoutCommand()),
	/**
	 * The upload image.
	 */
	UPLOAD_IMAGE(new UploadImageCommand()),
	/**
	 * The edit client profile.
	 */
	EDIT_CLIENT_PROFILE(new EditClientProfileCommand()),
	/**
	 * The change password.
	 */
	CHANGE_PASSWORD(new ChangePasswordCommand()),
	/**
	 * The new order.
	 */
	NEW_ORDER(new NewOrderCommand()),
	/**
	 * The create new order.
	 */
	CREATE_NEW_ORDER(new CreateOrderCommand()),
	/**
	 * The delete order by client.
	 */
	DELETE_ORDER_BY_CLIENT(new DeleteOrderByClientCommand()),
	/**
	 * The show all orders by client.
	 */
	SHOW_ALL_ORDERS_BY_CLIENT(new ShowAllOrdersByClientCommand()),
	/**
	 * The show all orders by trainer.
	 */
	SHOW_ALL_ORDERS_BY_TRAINER(new ShowAllOrdersByTrainerCommand()),
	/**
	 * The show all orders by admin.
	 */
	SHOW_ALL_ORDERS_BY_ADMIN(new ShowAllOrdersByAdminCommand()),
	/**
	 * The show order by client.
	 */
	SHOW_ORDER_BY_CLIENT(new ShowOrderByClientCommand()),
	/**
	 * The show order by trainer.
	 */
	SHOW_ORDER_BY_TRAINER(new ShowOrderByTrainerCommand()),
	/**
	 * The show order by admin.
	 */
	SHOW_ORDER_BY_ADMIN(new ShowOrderByAdminCommand()),
	/**
	 * The create new comment.
	 */
	CREATE_NEW_COMMENT(new CreateNewCommentCommand()),
	/**
	 * The show all active comments.
	 */
	SHOW_ALL_ACTIVE_COMMENTS(new ShowAllActiveCommentsCommand()),
	/**
	 * The delete comment.
	 */
	DELETE_COMMENT(new DeleteCommentCommand()),
	/**
	 * The edit comment.
	 */
	EDIT_COMMENT(new EditCommentCommand()),
	/**
	 * The refill money.
	 */
	REFILL_MONEY(new RefillMoneyCommand()),
	/**
	 * The edit trainer profile.
	 */
	EDIT_TRAINER_PROFILE(new EditTrainerProfileCommand()),
	/**
	 * The edit admin profile.
	 */
	EDIT_ADMIN_PROFILE(new EditAdminProfileCommand()),
	/**
	 * The edit description.
	 */
	EDIT_DESCRIPTION(new EditDescriptionCommand()),
	/**
	 * The edit experience.
	 */
	EDIT_EXPERIENCE(new EditExperienceCommand()),
	/**
	 * The edit nutrition.
	 */
	EDIT_NUTRITION(new EditNutritionCommand()),
	/**
	 * The edit exercises.
	 */
	EDIT_EXERCISES(new EditExercisesCommand()),
	/**
	 * The show users.
	 */
	SHOW_USERS(new ShowUsersCommand()),
	/**
	 * The update order status.
	 */
	UPDATE_ORDER_STATUS(new UpdateOrderStatusCommand()),
	/**
	 * The delete user.
	 */
	DELETE_USER(new DeleteUserCommand()),
	/**
	 * The restore user.
	 */
	RESTORE_USER(new RestoreUserCommand()),
	/**
	 * The show user profile.
	 */
	SHOW_USER_PROFILE(new ShowUserProfileCommand()),
	/**
	 * The assign discount.
	 */
	ASSIGN_DISCOUNT(new AssignDiscountCommand()),
	/**
	 * The show all trainers.
	 */
	SHOW_ALL_TRAINERS(new ShowAllTrainerCommand());

	/**
	 * The command.
	 */
	private final Command command;

	/**
	 * Instantiates a new command type.
	 *
	 * @param command the command
	 */
	CommandType(Command command) {
		this.command = command;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}
}
