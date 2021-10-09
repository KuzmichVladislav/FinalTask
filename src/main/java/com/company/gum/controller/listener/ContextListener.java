package com.company.gum.controller.listener;

import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The listener interface for receiving context events.
 * The class that is interested in processing a context
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addContextListener<code> method. When
 * the context event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Vladislav Kuzmich
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /** The Constant logger. */
    private static final Logger logger = LogManager.getLogger();

    /** The Constant DELAY. */
    private static final long DELAY = 86400000;
    
    /** The Constant DELETE_TIME. */
    public static final String DELETE_TIME = "01:30:00 AM";

    /** The format. */
    private final DateFormat format = DateFormat.getTimeInstance();

    /** The timer. */
    private final Timer timer = new Timer();

    /**
     * Context initialized.
     *
     * @param sce the sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.initPool();

        Date initialTime;
        try {
            initialTime = format.parse(DELETE_TIME);
        } catch (ParseException e) {
            logger.warn("initialTime could not be parsed");
            initialTime = new Date();
        }

        Calendar time = Calendar.getInstance();
        Calendar timeOfDay = Calendar.getInstance();

        timeOfDay.setTime(initialTime);

        time.set(Calendar.HOUR_OF_DAY, timeOfDay.get(Calendar.HOUR_OF_DAY));
        time.set(Calendar.MINUTE, timeOfDay.get(Calendar.MINUTE));
        time.set(Calendar.SECOND, timeOfDay.get(Calendar.SECOND));

        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.MINUTE, 1);

        if (time.before(startTime)) {
            time = startTime;
        }
        logger.debug("Timer has been set for {} ({})", time.getTime(), DELAY);

        TimerTask deleteObsoleteOrders = new DeleteOrdersTimerTask();
        timer.schedule(deleteObsoleteOrders, time.getTime(), DELAY);
    }

    /**
     * Context destroyed.
     *
     * @param sce the sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeAllConnections();
        timer.cancel();
    }
}
