package com.company.gum.controller.listener;

import com.company.gum.model.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving context events. The class that is
 * interested in processing a context event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addContextListener<code> method. When
 * the context event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ContextEvent
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Context initialized.
     *
     * @param sce the sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.initPool();
    }

    /**
     * Context destroyed.
     *
     * @param sce the sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closeAllConnections();
    }
}
