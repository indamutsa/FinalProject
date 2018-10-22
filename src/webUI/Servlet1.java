package webUI;

/**
 * Servlet 1
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import client.Client;
import util.Courrier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Servlet1")
public class Servlet1 extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        Client client = new Client();
        ArrayList<String> listPizza = null;

        if (client.pizzeriaList() != null && !(client.pizzeriaList() instanceof String))
        {
            listPizza = (ArrayList<String>) client.pizzeriaList();
        }
        else
            out.println("<h2>Empty database, please add items and try again</h2>");

        // Set response content type
        response.setContentType("text/html");
        String firstName = "First name";
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

                "<form action = \"/Servlet2\" method = \"GET\">");
        out.println("<select  name=\"pizza\">");
        for (String str : listPizza)
            out.println("<option value=\""+ str + "\">" + str + "</option>");
        out.println("</select>");
        out.println(" <input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body> </html>");
        out.println("\n");
        out.println();
        out.close();
    }
}
