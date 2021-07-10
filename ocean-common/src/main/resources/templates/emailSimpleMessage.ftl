<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title th:text="${content.subject}"></title>
</head>
<body>
<div>
    <h1><p th:text="${content.title}" /></h1>
    <br/><br/>
    <br/>
    <p th:text="${content.message}" /><br/>
</div>
</body>
</html>