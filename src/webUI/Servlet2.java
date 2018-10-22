package webUI;

/**
 * Servlet 2
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import client.Client;
import util.StructOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Client client = new Client();
        String selectedOptions[] = null;
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements())
            selectedOptions = request.getParameterValues(params.nextElement());

        System.out.println(Arrays.toString(selectedOptions));
        String pizzaname = request.getParameter("pizzeria");
        ArrayList<String> optionList = client.showOptionSets(pizzaname);


        double totalPrice = client.getPizzaPrice(pizzaname, selectedOptions);
        request.setAttribute("pizzaname", pizzaname);
        request.setAttribute("price", totalPrice);
        request.setAttribute("order", selectedOptions);
        request.setAttribute("list", optionList);


        request.getRequestDispatcher("TicketOrder.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        String pizzaName = (String) request.getParameter("pizza");

        Client client = new Client();
        ArrayList<StructOption> listOption = null;

        // Set response content type
        response.setContentType("text/html");
        out = response.getWriter();
        String title = "Welcome to INDAMUTSA pizzeria";
        String docType =
                "<!doctype html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#FFE4B5\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n" +

                "<form action = \"/Servlet2\" method = \"POST\">");
        out.println("<input type='hidden' name='pizzeria' value='" + pizzaName + "' />");
        ArrayList<String> optionList = client.showOptionSets(pizzaName);

        if(optionList == null)
            out.println("<h3>The database is empty,<br/> Please add items</h3>");

        if (client.pizzeriaList() != null && !(client.pizzeriaList() instanceof String))
        {
            for (String optset : optionList)
            {
                out.println("<h4>" + optset + "</h4>");

                out.println("<select  name=\"pizza\">");
                listOption = client.pizzaOptions(pizzaName, optset);

                for (StructOption struct : listOption)
                {
                    out.println("<option value=\"" + struct.getName() + "\">" + struct.getName() + "</option>");
                }
                out.println("</select>");
            }
        }

        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body> </html>");
        out.println("\n");
        out.println();
        out.close();
    }
}

//  System.out.println(params.nextElement());
//       String option= params.nextElement();
//               request.getParameterValues(option);