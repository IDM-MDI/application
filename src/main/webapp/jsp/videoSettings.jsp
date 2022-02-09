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
            <th>${resolTranslate}</th>
            <th>${ratioTranslate}</th>
            <th>${britTranslate}</th>
            <th>${typeTranslate}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="video" items="${requestScope.videoList}">
            <tr>
                <th scope="row">${video.id}</th>
                <td>${video.name}</td>
                <td>${video.resolution}</td>
                <td>${video.ratio}</td>
                <td>${video.brightness}</td>
                <td>${video.type}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <ul class="pagination pagination-lg">
        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?video=get&&page=${requestScope.prevPage}">${prevTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.prevPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${prevTranslate}</a></li>
        </c:if>

        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?video=get&&page=${requestScope.prevPage}">${requestScope.prevPage}</a></li>
        </c:if>

        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?video=get&&page=${requestScope.currentPage}">${requestScope.currentPage}</a></li>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?video=get&&page=${requestScope.nextPage}">${requestScope.nextPage}</a></li>
        </c:if>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?video=get&&page=${requestScope.nextPage}">${nextTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.nextPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${nextTranslate}</a></li>
        </c:if>
    </ul>

    <div class="d-flex justify-content-around">
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${addTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?video=add" method="post">
                <div class="form-group">
                    <label for="videoNameAdd">${nameTranslate}</label>
                    <input type="text" name="videoName" class="form-control" id="videoNameAdd" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoResolutionAdd">${resolTranslate}</label>
                    <input type="text" name="videoResolution" class="form-control" id="videoResolutionAdd" placeholder="${resolTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoRatioAdd">${ratioTranslate}</label>
                    <input type="text" name="videoRatio" class="form-control" id="videoRatioAdd" placeholder="${ratioTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoBrightnessAdd">${britTranslate}</label>
                    <input type="number" name="videoBrightness" class="form-control" id="videoBrightnessAdd" placeholder="${britTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoTypeAdd">${typeTranslate}</label>
                    <select class="form-control" id="videoTypeAdd" name="videoType">
                        <option>TN</option>
                        <option>VA</option>
                        <option>IPS</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${addTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${updateTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?video=update" method="post">
                <div class="form-group">
                    <label for="videoIdUpdate">${idTranslate}</label>
                    <input type="number" name="videoId" class="form-control" id="videoIdUpdate" placeholder="${idTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoNameUpdate">${nameTranslate}</label>
                    <input type="text" name="videoName" class="form-control" id="videoNameUpdate" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoResolutionUpdate">${resolTranslate}</label>
                    <input type="text" name="videoResolution" class="form-control" id="videoResolutionUpdate" placeholder="${resolTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoRatioUpdate">${ratioTranslate}</label>
                    <input type="text" name="videoRatio" class="form-control" id="videoRatioUpdate" placeholder="${ratioTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoBrightnessUpdate">${britTranslate}</label>
                    <input type="number" name="videoBrightness" class="form-control" id="videoBrightnessUpdate" placeholder="${britTranslate}">
                </div>
                <div class="form-group">
                    <label for="videoTypeUpdate">${typeTranslate}</label>
                    <select class="form-control" id="videoTypeUpdate" name="videoType">
                        <option>TN</option>
                        <option>VA</option>
                        <option>IPS</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${updateTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${deleteTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?video=delete" method="post">
                <div class="form-group">
                    <label for="videoIdDelete">${idTranslate}</label>
                    <input type="number" name="videoId" class="form-control" id="videoIdDelete" placeholder="${idTranslate}">
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
