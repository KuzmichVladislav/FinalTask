package com.company.gum.controller;

import com.company.gum.command.Command;
import com.company.gum.command.CommandType;
import com.company.gum.command.PagePath;
import com.company.gum.command.ParameterName;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/controller")
public class MainController extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public void init() throws ServletException {
		super.init();
		ConnectionPool.initPool();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionRequestContent content = new SessionRequestContent(req);
		String commandName = req.getParameter(ParameterName.COMMAND).toUpperCase();
		String page;
		try {
			Command command = CommandType.valueOf(commandName).getCommand();
			page = command.execute(content);
			content.insertAttributes(req);
			req.getRequestDispatcher(page).forward(req, resp);
		} catch (CommandException | ServiceException | IllegalArgumentException e) {
			logger.error(e);
			req.setAttribute(ParameterName.ERROR, e);
			req.getRequestDispatcher(PagePath.ERROR).forward(req, resp);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool.getInstance().closeAllConnections();
	}
}
