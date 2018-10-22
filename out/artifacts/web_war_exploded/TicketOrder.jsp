<%--/**
 * JSP pizza displayer
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */
--%>

<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: arsene
  Date: 10/21/18
  Time: 4:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>INDAMUTSA Pizzeria</title>
</head>
<body>
<h1>Welcome</h1>
<p><b>Ordered Pizza:</b> ${pizzaname}</p>
<p><b>Total price:</b> ${price}</p>
<p>Your order is: </p>

<%
    String[] str = (String[]) request.getAttribute("order");
    ArrayList<String> list = (ArrayList) request.getAttribute("list");

    for (int i = 0; i < str.length; i++)
    {
        out.print(list.get(i)+ ": => "+str[i]  +"</br>");
    }

%>

</body>
</html>

<%--<%
    ArrayList ar=new ArrayList();
    ar.add("bob");
    ar.add("riche");
    ar.add("jacky");
    ar.add("rosy");
%>

<html>
<body>
<%
    int i=0;
    for(i=0;i<ar.size();i++)
    {
        out.print("ArrayList Elements       :"+ar.get(i)+"<br/>");
    }
%>
</body>
</html>--%>
