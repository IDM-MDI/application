<%--
  Created by IntelliJ IDEA.
  User: ishan
  Date: 1/24/2022
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${titleTranslate}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>${idTranslate}</th>
        <th>${photoTranslate}</th>
        <th>${nameTranslate}</th>
        <th>${bdTranslate}</th>
        <th>${sdTranslate}</th>
        <th>${priceTranslate}</th>
        <th>${cpuTranslate}</th>
        <th>${memTranslate}</th>
        <th>${batTranslate}</th>
        <th>${vidTranslate}</th>
        <th>${audTranslate}</th>
        <th>${catTranslate}</th>
        <th>${deleteTranslate}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="gadget" items="${requestScope.gadgetList}">
        <tr>
            <th scope="row">${gadget.id}</th>
            <c:if test="${gadget.mainPhoto[0] eq null}">
                <td>
                    <img src="${pageContext.request.contextPath}/img/question.png" alt="mdo" width="32" height="32" class="rounded-circle">
                </td>
            </c:if>
            <c:if test="${gadget.mainPhoto[0] ne null}">
                <td>
                    <img src="data:image/jpg;base64,${gadget.photoToString}" alt="mdo" width="32" height="32" class="rounded-circle">
                </td>
            </c:if>
            <td>${gadget.name}</td>
            <td>${gadget.bigDescription}</td>
            <td>${gadget.smallDescription}</td>
            <td>${gadget.price}</td>
            <td>${gadget.cpuName}</td>
            <td>${gadget.memoryName}</td>
            <td>${gadget.batteryName}</td>
            <td>${gadget.videoName}</td>
            <td>${gadget.audioName}</td>
            <td>${gadget.categoryName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/controller?cart=delete&&id=${gadget.id}" class="btn btn-primary">${deleteTranslate}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="footer.jsp" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
