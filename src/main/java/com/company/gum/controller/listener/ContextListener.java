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

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();

    private static final long DELAY = 86400000;
    public static final String DELETE_TIME = "01:30:00 AM";

    private final DateFormat format = DateFormat.getTimeInstance();

    private final Timer timer = new Timer();

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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeAllConnections();
        timer.cancel();
    }
}
