<%--
  Created by IntelliJ IDEA.
  User: ishan
  Date: 1/6/2022
  Time: 5:58 PM
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

<main>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:forEach var="gadget" items="${requestScope.gadgetList}">
                    <div class="card">
                        <c:if test="${gadget.mainPhoto[0] eq null}">
                            <img src="${pageContext.request.contextPath}/img/question.png" class="card-img-top" alt="${gadget.id} image" width="250" height="250">
                        </c:if>
                        <c:if test="${gadget.mainPhoto[0] ne null}">
                            <img src="data:image/jpg;base64,${gadget.photoToString}" class="card-img-top" alt="${gadget.id} image" width="250" height="250">
                        </c:if>
                        <div class="card-body">
                            <h5 class="card-title">${gadget.name}</h5>
                            <p class="card-text">${gadget.smallDescription}</p>
                            <c:if test="${sessionScope.user.role ne null}">
                                <a href="${pageContext.request.contextPath}/controller?cart=add&&id=${gadget.id}" class="btn btn-primary"><th>${atcTranslate}</th></a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <ul class="pagination pagination-lg">
        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.prevPage}">${prevTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.prevPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${prevTranslate}</a></li>
        </c:if>

        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.prevPage}">${requestScope.prevPage}</a></li>
        </c:if>

        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.currentPage}">${requestScope.currentPage}</a></li>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.nextPage}">${requestScope.nextPage}</a></li>
        </c:if>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.nextPage}"><th>${nextTranslate}</th></a></li>
        </c:if>
        <c:if test="${requestScope.nextPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${nextTranslate}</a></li>
        </c:if>
    </ul>

</main>
<jsp:include page="footer.jsp" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
