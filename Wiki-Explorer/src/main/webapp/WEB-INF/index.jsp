<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>

    <script src="/assets/js/wikiJS.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="<c:url value="/assets/css/background.css" />"/>

    <title>JSP Page</title>

</head>


<body>

<form name="simpleSearchForm" action="${pageContext.request.contextPath}/wikiapp/getTitle/${singleWord}" method="post">
    <div align="center">
        Title: <br>
        <p><input type="text" placeholder="Write your title here" id="singleWord" name="singleWord" accept="txt/*"/></p>
        <input class="btn button-wrapper btn-submit" type="submit" name="submitSimpleSearch"/>
    </div>
</form>


<form name="multipleSearchForm" action="/wikiapp/upload" enctype="multipart/form-data" method="post">
    <div align="center">

        <input class="btn-choose  btn-chooseFile" id="btn-choose" name="btnChoose" type="file"/>

        <p><input type="text" placeholder="Here is your titles file!" id="FileWord" name="FileWord" accept="txt/*"/></p>

        <input class="btn button-wrapper btn-submit" type="submit" name="Submit File"/>

    </div>
</form>

    <div class="wrapper" >

        <div id="chart"></div>
    </div>

</body>
</html>
