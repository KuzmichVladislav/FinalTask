package com.company.gum.tag;

import com.company.gum.entity.Order;
import com.company.gum.util.PropertyLoader;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
    public int doStartTag() {

        HttpSession session = pageContext.getSession();
        String language = String.valueOf(session.getAttribute("locale"));
        Properties properties = PropertyLoader.loadProperty("bundle/message_" + language + ".properties");

        String registerDate = properties.getProperty("profile.register.date");
        String trainerFio = properties.getProperty("order.trainer.name");
        String startDate = properties.getProperty("order.start.date");
        String endDate = properties.getProperty("order.end.date");
        String status = properties.getProperty("order.status");
        String detail = properties.getProperty("order.details");
        String delete = properties.getProperty("comment.delete");

        JspWriter out = pageContext.getOut();
        try {
            out.write("<table style=\"width: auto;\">");
            out.write("<th>");
            for (Order order : (List<Order>) pageContext.getRequest().getAttribute("orders")) {
                out.write(
                        "<table class=\"table table-striped\" border=\"1\" style=\"width:max-content; max-width:max-content;\">");
                out.write("<tr>");
                out.write("<th>" + registerDate + "</th>");
                out.write("<th>" + trainerFio + "</th>");
                out.write("<th>" + startDate + "</th>");
                out.write("<th>" + endDate + "</th>");
                out.write("<th>" + status + "</th>");
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
                        + "\"><button class=\"btn btn-sm btn-outline-primary w-100\">" + detail + "</button></a>");
                if (order.getOrderStatus() == Order.OrderStatus.NEW
                        || order.getOrderStatus() == Order.OrderStatus.REVIEWED
                        || order.getOrderStatus() == Order.OrderStatus.REJECTED) {
                    out.write("<form name=\"delete_form\" action=\"controller\" method=\"POST\">");
                    out.write("<input type=\"hidden\" name=\"command\" value=\"DELETE_ORDER_BY_CLIENT\">");
                    out.write("<input type=\"hidden\" name=\"orderId\" value=" + order.getId() + ">");
                    out.write("<button type=\"submit\" class=\"btn btn-sm btn-outline-primary w-100\">" + delete + "</button>");
                    out.write("</form>");
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
