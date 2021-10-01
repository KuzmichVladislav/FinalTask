package com.company.gum.controller;

import com.company.gum.controller.command.*;
import com.company.gum.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
@WebServlet(value = "/controller")
public class MainController extends HttpServlet {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Do get.
     *
     * @param req the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    /**
     * Do post.
     *
     * @param req the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    /**
     * Do process.
     *
     * @param req the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent(req);

        try {
            Command command = CommandType.valueOf(req.getParameter(AttributeName.COMMAND).toUpperCase()).getCommand();
            Router router = command.execute(content);
            switch (router.getRouterType()) {
                case REDIRECT:
                    content.insertAttributes(req);
                    resp.sendRedirect(router.getPagePath());
                    break;
                case FORWARD:
                    content.insertAttributes(req);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(router.getPagePath());
                    dispatcher.forward(req, resp);
                    break;
                default:
                    logger.error("incorrect route type {}", router.getRouterType());
            }
        } catch (CommandException e) {
            logger.error(e);
            req.setAttribute(AttributeName.ERROR, e);
            req.getRequestDispatcher(PagePath.ERROR).forward(req, resp);
        }
    }
}
