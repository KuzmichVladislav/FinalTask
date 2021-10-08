package com.company.gum.controller.listener;

import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;


/**
 * The Class DeleteOrdersTimerTask.
 *
 * @author Vladislav Kuzmich
 */
public class DeleteOrdersTimerTask extends TimerTask {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The Constant SQL_UPDATE_QUERY.
     */
    private static final String SQL_UPDATE_QUERY = "UPDATE orders\n"
            + "SET is_active = ?\n"
            + "WHERE end_order_date < convert(?, datetime)";

    /**
     * The date now.
     */
    private final Date dateNow = new Date();

    /**
     * The sql date.
     */
    private final java.sql.Date sqlDate = new java.sql.Date(dateNow.getTime());


    /**
     * Run.
     */
    public void run() {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY)) {
            statement.setBoolean(1, false);
            statement.setDate(2, sqlDate);
            boolean isDeleted = statement.executeUpdate() == 1;
            logger.debug(isDeleted ? "Obsolete orders has been deleted" : "No obsolete orders found");
        } catch (SQLException throwables) {
            logger.warn("Obsolete orders has not been deleted");
            throwables.printStackTrace();
        }
    }
}
