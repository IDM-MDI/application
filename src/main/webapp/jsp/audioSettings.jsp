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
            <th>${idTranslate}</th>
            <th>${nameTranslate}</th>
            <th>${freqTranslate}</th>
            <th>${typeTranslate}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="audio" items="${requestScope.audioList}">
            <tr>
                <th scope="row">${audio.id}</th>
                <td>${audio.name}</td>
                <td>${audio.frequency}</td>
                <td>${audio.type}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <ul class="pagination pagination-lg">
        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?audio=get&&page=${requestScope.prevPage}">${prevTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.prevPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${prevTranslate}</a></li>
        </c:if>

        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?audio=get&&page=${requestScope.prevPage}">${requestScope.prevPage}</a></li>
        </c:if>

        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?audio=get&&page=${requestScope.currentPage}">${requestScope.currentPage}</a></li>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?audio=get&&page=${requestScope.nextPage}">${requestScope.nextPage}</a></li>
        </c:if>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?audio=get&&page=${requestScope.nextPage}">${nextTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.nextPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${nextTranslate}</a></li>
        </c:if>
    </ul>

    <div class="d-flex justify-content-around">
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${addTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?audio=add" method="post">
                <div class="form-group">
                    <label for="audioNameAdd">${nameTranslate}</label>
                    <input type="text" name="audioName" class="form-control" id="audioNameAdd" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="audioFrequencyAdd">${freqTranslate}</label>
                    <input type="number" name="audioFrequency" class="form-control" id="audioFrequencyAdd" placeholder="${freqTranslate}">
                </div>
                <div class="form-group">
                    <label for="audioTypeAdd">${typeTranslate}</label>
                    <select class="form-control" id="audioTypeAdd" name="audioType">
                        <option>Surround</option>
                        <option>Stereo</option>
                        <option>Mono</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${addTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${updateTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?audio=update" method="post">
                <div class="form-group">
                    <label for="audioIdUpdate">${idTranslate}</label>
                    <input type="number" name="audioId" class="form-control" id="audioIdUpdate" placeholder="${idTranslate}">
                </div>
                <div class="form-group">
                    <label for="audioNameUpdate">${nameTranslate}</label>
                    <input type="text" name="audioName" class="form-control" id="audioNameUpdate" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="audioFrequencyUpdate">${freqTranslate}</label>
                    <input type="number" name="audioFrequency" class="form-control" id="audioFrequencyUpdate" placeholder="${freqTranslate}">
                </div>
                <div class="form-group">
                    <label for="audioTypeUpdate">${typeTranslate}</label>
                    <select class="form-control" id="audioTypeUpdate" name="audioType">
                        <option>Surround</option>
                        <option>Stereo</option>
                        <option>Mono</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${updateTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${deleteTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?audio=delete" method="post">
                <div class="form-group">
                    <label for="audioIdDelete">${idTranslate}</label>
                    <input type="number" name="audioId" class="form-control" id="audioIdDelete" placeholder="${idTranslate}">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${deleteTranslate}</button>
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
