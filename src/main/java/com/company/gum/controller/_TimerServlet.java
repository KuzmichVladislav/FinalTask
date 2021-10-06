/*
package com.company.gum.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(value = "/TimerServlet", initParams = {
    @WebInitParam(name = "initialTime", value = "01:30:00 AM"),
    @WebInitParam(name = "delay", value = "86400000")}, loadOnStartup = 1)
public class TimerServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final long HOURS_24 = 86400000;

    private final DateFormat format = DateFormat.getTimeInstance();

    private Timer timer;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        Date initialTime;
        try {
            initialTime = format.parse(servletConfig.getInitParameter("initialTime"));
        } catch (ParseException pe) {
            logger.info("startTime could not be parsed from @WebInitParam");
            initialTime = new Date();
        }

        long delay;
        try {
            delay = Long.parseLong(servletConfig.getInitParameter("delay"));
        } catch (Exception e) {
            logger.info("delay Parameter could not be parsed from @WebInitParam");
            delay = HOURS_24;
        }

        timer = new Timer();
        Calendar time = Calendar.getInstance();
        Calendar timeOfDay = Calendar.getInstance();

        try {
            timeOfDay.setTime(initialTime);

            time.set(Calendar.HOUR_OF_DAY, timeOfDay.get(Calendar.HOUR_OF_DAY));
            time.set(Calendar.MINUTE, timeOfDay.get(Calendar.MINUTE));
            time.set(Calendar.SECOND, timeOfDay.get(Calendar.SECOND));

            Calendar startTime = Calendar.getInstance();
            startTime.add(Calendar.MINUTE, 1);

            if (time.before(startTime)) {
                time = startTime;
            }
            logger.info("Timer has been set for {} ({})", time.getTime(), delay);

            TimerTask deleteObsoleteOrders = new DeleteObsoleteOrders();
            timer.schedule(deleteObsoleteOrders, time.getTime(), delay);
        } catch (Exception e) {
            logger.info("TimerServlet", e);
        }
    }

    @Override
    public void destroy() {
        timer.cancel();
        super.destroy();
    }

}
*/