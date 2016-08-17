<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

<span class='body'>
    <div>
    <link type="text/css" rel="stylesheet" href="<c:url value="/assets/css/background.css" />"/>
        </div>
</span>


<h1>---WikiSearch---${aBinding}</h1>


<body>
<form name="Form1" action="#" method="post">
    <div align="center">
        Title:<br>
        <textarea placeholder="Write your title here" cols="30" rows="1"></textarea>
        <input class="btn button-wrapper btn-submit" type="submit" name="Submit File"/>
    </div>
</form>


<form name="Form2" action="/wikiapp/upload" enctype="multipart/form-data" method="post">
    <div align="center">

    <input class="btn-chose button-wrapper btn-choseFile file-upload  name=theFile type=file" />
    <textarea placeholder="The titles file name..." cols="30" rows="1"></textarea>
    <input class="submit btn_submit" type="submit" name="Submit File"/>
</div>
</form>


<form name="BarChart">
    <div class="chart">
        <c:forEach items="${wordCount}" var="theWordCount">
            <div style="width: 10px;">${theWordCount.theWord} Nr: ${theWordCount.occurences}</div>
        </c:forEach>
        <div style="width: 10px;">1</div>
        <div style="width: 20px;">2</div>
        <div style="width: 40px;">4</div>
        <div style="width: 80px;">8</div>
        <div style="width: 150px;">15</div>
        <div style="width: 160px;">16</div>
        <div style="width: 230px;">23</div>
        <div style="width: 420px;">42</div>
    </div>

</form>

</body>
</html>
