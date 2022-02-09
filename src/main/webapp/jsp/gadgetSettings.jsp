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
            </tr>
        </c:forEach>
        </tbody>
    </table>

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
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?gadget=get&&page=${requestScope.nextPage}">${nextTranslate}</a></li>
        </c:if>
        <c:if test="${requestScope.nextPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">${nextTranslate}</a></li>
        </c:if>
    </ul>

    <div class="d-flex justify-content-around">
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${addTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?gadget=add" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="gadgetNameAdd">${nameTranslate}</label>
                    <input type="text" name="gadgetName" class="form-control" id="gadgetNameAdd" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetBigDescriptionAdd">${bdTranslate}</label>
                    <input type="text" name="gadgetBigDescription" class="form-control" id="gadgetBigDescriptionAdd" placeholder="${bdTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetSmallDescriptionAdd">${sdTranslate}</label>
                    <input type="text" name="gadgetSmallDescription" class="form-control" id="gadgetSmallDescriptionAdd" placeholder="${sdTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetPriceAdd">${priceTranslate}</label>
                    <input type="number" name="gadgetPrice" class="form-control" id="gadgetPriceAdd" placeholder="${priceTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetCpuAdd">${cpuTranslate}</label>
                    <select class="form-control" id="gadgetCpuAdd" name="gadgetCpu">
                        <c:forEach var="cpu" items="${requestScope.cpuList}">
                            <option value="${cpu.id}">${cpu.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetMemoryAdd">${memTranslate}</label>
                    <select class="form-control" id="gadgetMemoryAdd" name="gadgetMemory">
                        <c:forEach var="memory" items="${requestScope.memoryList}">
                            <option value="${memory.id}">${memory.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetBatteryAdd">${batTranslate}</label>
                    <select class="form-control" id="gadgetBatteryAdd" name="gadgetBattery">
                        <c:forEach var="battery" items="${requestScope.batteryList}">
                            <option value="${battery.id}">${battery.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetVideoAdd">${vidTranslate}</label>
                    <select class="form-control" id="gadgetVideoAdd" name="gadgetVideo">
                        <c:forEach var="video" items="${requestScope.videoList}">
                            <option value="${video.id}">${video.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetAudioAdd">${audTranslate}</label>
                    <select class="form-control" id="gadgetAudioAdd" name="gadgetAudio">
                        <c:forEach var="audio" items="${requestScope.audioList}">
                            <option value="${audio.id}">${audio.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetCategoryAdd">${catTranslate}</label>
                    <select class="form-control" id="gadgetCategoryAdd" name="gadgetCategory">
                        <c:forEach var="category" items="${requestScope.categoryList}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="gadgetPhoto" accept="image/*" class="custom-file-input" id="customFileAdd">
                        <label class="custom-file-label" for="customFileAdd">${fileTranslate}</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${addTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${updateTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?gadget=update" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="gadgetIdUpdate">${idTranslate}</label>
                    <input type="number" name="gadgetId" class="form-control" id="gadgetIdUpdate" placeholder="${idTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetNameUpdate">${nameTranslate}</label>
                    <input type="text" name="gadgetName" class="form-control" id="gadgetNameUpdate" placeholder="${nameTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetBigDescriptionUpdate">${bdTranslate}</label>
                    <input type="text" name="gadgetBigDescription" class="form-control" id="gadgetBigDescriptionUpdate" placeholder="${bdTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetSmallDescriptionUpdate">${sdTranslate}</label>
                    <input type="text" name="gadgetSmallDescription" class="form-control" id="gadgetSmallDescriptionUpdate" placeholder="${sdTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetPriceUpdate">${priceTranslate}</label>
                    <input type="number" name="gadgetPrice" class="form-control" id="gadgetPriceUpdate" placeholder="${priceTranslate}">
                </div>
                <div class="form-group">
                    <label for="gadgetCpuUpdate">${cpuTranslate}</label>
                    <select class="form-control" id="gadgetCpuUpdate" name="gadgetCpu">
                        <c:forEach var="cpu" items="${requestScope.cpuList}">
                            <option value="${cpu.id}">${cpu.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetMemoryUpdate">${memTranslate}</label>
                    <select class="form-control" id="gadgetMemoryUpdate" name="gadgetMemory">
                        <c:forEach var="memory" items="${requestScope.memoryList}">
                            <option value="${memory.id}">${memory.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetBatteryUpdate">${batTranslate}</label>
                    <select class="form-control" id="gadgetBatteryUpdate" name="gadgetBattery">
                        <c:forEach var="battery" items="${requestScope.batteryList}">
                            <option value="${battery.id}">${battery.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetVideoUpdate">${vidTranslate}</label>
                    <select class="form-control" id="gadgetVideoUpdate" name="gadgetVideo">
                        <c:forEach var="video" items="${requestScope.videoList}">
                            <option value="${video.id}">${video.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetAudioUpdate">${audTranslate}</label>
                    <select class="form-control" id="gadgetAudioUpdate" name="gadgetAudio">
                        <c:forEach var="audio" items="${requestScope.audioList}">
                            <option value="${audio.id}">${audio.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="gadgetCategoryUpdate">${catTranslate}</label>
                    <select class="form-control" id="gadgetCategoryUpdate" name="gadgetCategory">
                        <c:forEach var="category" items="${requestScope.categoryList}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="gadgetPhoto" accept="image/*" class="custom-file-input" id="customFile">
                        <label class="custom-file-label" for="customFile">${fileTranslate}</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">${updateTranslate}</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">${deleteTranslate}</div>
            <form action="${pageContext.request.contextPath}/controller?gadget=delete" method="post">
                <div class="form-group">
                    <label for="gadgetIdDelete">${idTranslate}</label>
                    <input type="number" name="gadgetId" class="form-control" id="gadgetIdDelete" placeholder="${idTranslate}">
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
