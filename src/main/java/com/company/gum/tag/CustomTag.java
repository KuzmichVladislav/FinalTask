package com.company.gum.tag;

import com.company.gum.entity.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomTag.
 */
public class CustomTag extends TagSupport {

	/**
	 * Do start tag.
	 *
	 * @return the int
	 * @throws JspException the jsp exception
	 */
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write("<table style=\"width: auto;\">");
			out.write("<th>");
			for (Order order : (List<Order>) pageContext.getRequest().getAttribute("orders")) {
				out.write(
						"<table class=\"table table-striped\" border=\"1\" style=\"width:max-content; max-width:max-content;\">");
				out.write("<tr>");
				out.write("<th>${registerDate}</th>");
				out.write("<th>${trainerFio}</th>");
				out.write("<th>${startDate}</th>");
				out.write("<th>${endDate}</th>");
				out.write("<th>${status}</th>");
				out.write("</tr>");
				out.write("<tr>");
				out.write("<td>" + order.getRegisterDate() + "</td>");
				out.write("<td>" + order.getTrainerName() + " " + order.getTrainerSurname() + "</td>");
				out.write("<td>" + order.getStartDate() + "</td>");
				out.write("<td>" + order.getEndDate() + "</td>");
				out.write("<td>" + order.getOrderStatus() + "</td>");
				out.write("</tr>");
				out.write("<tr>");
				out.write("<td colspan=\"5\">");
				out.write("<a href=\"controller?command=SHOW_ORDER_BY_CLIENT&orderId=" + order.getId()
						+ "\"><button class=\"btn btn-sm btn-outline-primary w-100\">${Detail}</button></a>");
				if (order.getOrderStatus() == Order.OrderStatus.NEW
						|| order.getOrderStatus() == Order.OrderStatus.REVIEWED
						|| order.getOrderStatus() == Order.OrderStatus.REJECTED) {
					out.write("<a href=\"controller?command=DELETE_ORDER_BY_CLIENT&orderId=" + order.getId()
							+ "\"><button class=\"btn btn-sm btn-outline-primary w-100\">${Delete}</button></a>");
				}
				out.write("</td>");
				out.write("</tr>");
			}
			out.write("</table>");
			out.write("</th>");
			out.write("</table>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
