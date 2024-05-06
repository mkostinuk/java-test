<%@ page import="java.util.List" %>
<%@ page import="org.example.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Finish</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%List<User> userList = (List<User>) session.getAttribute("list_users");%>
<%User user = (User) session.getAttribute("user");%>
<table>
    <caption><b>Results</b></caption>
    <tr>
        <th>PLACE</th>
        <th>ID</th>
        <th>Name</th>
        <th>Attempts</th>
        <th>Points</th>
        <th>Best Result</th>
        <th>IP</th>
    </tr>
    <%for (int i = 0; i < userList.size(); i++) {%>
    <tr>
        <td><b><%=i + 1%>
        </b></td>
        <td><%=userList.get(i).getId()%>
        </td>
        <td><%=userList.get(i).getName()%>
        </td>
        <td><%=userList.get(i).getAttempts()%>
        </td>
        <td><%=userList.get(i).getPoints() + "/5"%>
        </td>
        <td><%=userList.get(i).bestResult() + "/5"%>
        </td>
        <td><%=userList.get(i).getIp()%>
        </td>
    </tr>

    <%}%>

</table>

<form method="post" action=<%="/registration?username=" + user.getName()%>>
    <button type="submit"> go to second attempt</button>
</form>
<form action="index.html">
    <button type="submit"> go to main page</button>
</form>
</body>
</html>
