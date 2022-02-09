<%--
  Created by IntelliJ IDEA.
  User: ishan
  Date: 1/30/2022
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${titleTranslate}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<form action="${pageContext.request.contextPath}/controller?user=update" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="emailInput">${emailTranslate}</label>
        <input type="text" readonly class="form-control-plaintext" id="emailInput" value="${sessionScope.user.email}" name="email">
    </div>
    <div class="form-group">
        <label for="usernameInput">${usernameTranslate}</label>
        <c:if test="${empty sessionScope.user.name}">
            <input type="text" name="username" class="form-control" id="usernameInput" placeholder="${usernameTranslate}">
        </c:if>
        <c:if test="${not empty sessionScope.user.name}">
            <input type="text" name="username" class="form-control" id="usernameInput" placeholder="${sessionScope.user.name}">
        </c:if>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">${passwordTranslate}</label>
        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="${passwordTranslate}">
    </div>
    <div class="form-group">
        <div class="custom-file">
            <input type="file" name="userPhoto" accept="image/*" class="custom-file-input" id="customFile">
            <label class="custom-file-label" for="customFile">${fileTranslate}</label>
        </div>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">${submitTranslate}</button>
    </div>
</form>

<jsp:include page="footer.jsp" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
