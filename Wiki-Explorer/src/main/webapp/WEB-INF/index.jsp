<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>

            .chart div {
                font: 10px sans-serif;
                background-color: steelblue;
                text-align: right;
                padding: 3px;
                margin: 1px;
                color: white;
            }

        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ionut! ${aBinding}</h1>
        <div class="chart">
            <c:forEach items="${wordCount}" var="theWordCount">
                <div style="width: 40px;">${theWordCount.theWord} Nr: ${theWordCount.occurences}</div>
             </c:forEach>
            <div style="width: 40px;">4</div>
            <div style="width: 80px;">8</div>
            <div style="width: 150px;">15</div>
            <div style="width: 160px;">16</div>
            <div style="width: 230px;">23</div>
            <div style="width: 420px;">42</div>
        </div>
    </body>
</html>
