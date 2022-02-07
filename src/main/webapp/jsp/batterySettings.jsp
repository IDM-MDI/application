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

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>MAH</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="battery" items="${requestScope.batteryList}">
                <tr>
                    <th scope="row">${battery.id}</th>
                    <td>${battery.name}</td>
                    <td>${battery.mah}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination pagination-lg">
            <c:if test="${requestScope.prevPage > 0}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?battery=get&&page=${requestScope.prevPage}">Previous</a></li>
            </c:if>
            <c:if test="${requestScope.prevPage <= 0}">
                <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">Previous</a></li>
            </c:if>

            <c:if test="${requestScope.prevPage > 0}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?battery=get&&page=${requestScope.prevPage}">${requestScope.prevPage}</a></li>
            </c:if>

            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?battery=get&&page=${requestScope.currentPage}">${requestScope.currentPage}</a></li>

            <c:if test="${requestScope.nextPage > 0}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?battery=get&&page=${requestScope.nextPage}">${requestScope.nextPage}</a></li>
            </c:if>

            <c:if test="${requestScope.nextPage > 0}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?battery=get&&page=${requestScope.nextPage}">Next</a></li>
            </c:if>
            <c:if test="${requestScope.nextPage <= 0}">
                <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">Next</a></li>
            </c:if>
        </ul>

        <div class="d-flex justify-content-around">
            <div class="p-2">
                <div class="alert alert-dark" role="alert">Add</div>
                <form action="${pageContext.request.contextPath}/controller?battery=add" method="post">
                    <div class="form-group">
                        <label for="batteryNameAdd">Name</label>
                        <input type="text" name="batteryName" class="form-control" id="batteryNameAdd" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label for="batteryMahAdd">MA/H</label>
                        <input type="number" name="batteryMah" class="form-control" id="batteryMahAdd" placeholder="MAH">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <div class="alert alert-dark" role="alert">Update</div>
                <form action="${pageContext.request.contextPath}/controller?battery=update" method="post">
                    <div class="form-group">
                        <label for="batteryIdUpdate">ID</label>
                        <input type="number" name="batteryId" class="form-control" id="batteryIdUpdate" placeholder="ID">
                    </div>
                    <div class="form-group">
                        <label for="batteryNameUpdate">Name</label>
                        <input type="text" name="batteryName" class="form-control" id="batteryNameUpdate" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label for="batteryMahUpdate">MA/H</label>
                        <input type="number" name="batteryMah" class="form-control" id="batteryMahUpdate" placeholder="MAH">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <div class="alert alert-dark" role="alert">Delete</div>
                <form action="${pageContext.request.contextPath}/controller?battery=delete" method="post">
                    <div class="form-group">
                        <label for="batteryIdDelete">ID</label>
                        <input type="number" name="batteryId" class="form-control" id="batteryIdDelete" placeholder="ID">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous" type="text/javascript"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>
