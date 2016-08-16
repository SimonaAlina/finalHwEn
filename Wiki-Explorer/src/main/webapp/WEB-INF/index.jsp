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

        .button-green {
            font-family: "Segoe UI", "Segoe", "Verdana";
            background: #00b33c linear-gradient(rgb(15, 158, 10), rgb(24, 105, 21)) no-repeat center top;
            overflow: hidden;
            color: white;
            border-radius: 5px;
            width: 82px;
            position: relative;
            padding: 8px 10px 8px 10px;
        }

        .button-green:hover {
            background: #00b33c linear-gradient(rgb(12, 141, 8), rgb(19, 88, 17)) no-repeat center top;
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }

        .file-upload {
            opacity: 0;
            width: 102px;
            height: 35px;
            position: absolute;
            top: 0px;
            left: 0px;
        }

        .button-submit {
            font-family: "Segoe UI", "Segoe", "Verdana";
            background: #00b33c linear-gradient(rgb(15, 158, 10), rgb(24, 105, 21)) no-repeat center top;
            overflow: hidden;
            color: white;
            border-radius: 5px;
            width: 82px;
            position: relative;
            padding: 8px 10px 8px 10px;
        }

        .button-submit:hover {
            background: #00b33c linear-gradient(rgb(12, 141, 8), rgb(19, 88, 17)) no-repeat center top;
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }


    </style>
    <title>JSP Page</title>
    <link rel="stylesheet" type="text/css" href="background.css"/>
</head>

    <h1>Hello ionut! ${aBinding}</h1>



<body>
<form name="Form1" action="#" method="post">
    Title:<br>
    <!--<input type="text" name="Title"><br>-->
    <textarea placeholder="Write your title here" cols="30" rows="1"></textarea>

    <input class="submit button-submit" type="submit"/>

</form>


<form name="Form2" action="/wikiapp/upload" enctype="multipart/form-data" method="post">

    <div class="button-green"><input class="file-upload" name="theFile" type="file">Choose File</div>
    <textarea placeholder="The titles file name..." cols="30" rows="1"></textarea>
    <input class="submit button-submit" type="submit" name="Submit File"/>

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
