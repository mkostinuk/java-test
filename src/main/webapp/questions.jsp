<%@ page import="org.example.model.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%Question question = (Question) session.getAttribute("question");%>
<h1><%=question.getQuestion()%>
</h1>
<form action="/questions">
    <%
        for (int i = 0; i < 4; i++) {
            Question.Answer answer = question.getAnswers().get(i);
    %>
    <label>
        <input type="radio" name="answer" value="<%=answer.isCorrect()%>">
        <%=answer.getText()%>
    </label>
    <br>
    <% }%>
    <label>
        <input type="radio" name="next" value="false">
         <b>GIVE UP</b>
    </label>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
