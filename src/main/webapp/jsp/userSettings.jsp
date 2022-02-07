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
            <th>Photo</th>
            <th>Email</th>
            <th>Name</th>
            <th>Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.userList}">
            <tr>
                <c:if test="${user.photo[0] eq null}">
                    <td>
                        <img src="${pageContext.request.contextPath}/img/noneAvatar.jpeg" alt="mdo" width="32" height="32" class="rounded-circle">
                    </td>
                </c:if>
                <c:if test="${user.photo[0] ne null}">
                    <td>
                        <img src="data:image/jpg;base64,${user.photoToString}" alt="mdo" width="32" height="32" class="rounded-circle">
                    </td>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.prevPage}">Previous</a></li>
                </c:if>
                <th scope="row">${user.email}</th>
                <td>${user.name}</td>
                <td>${user.pass}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <ul class="pagination pagination-lg">
        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.prevPage}">Previous</a></li>
        </c:if>
        <c:if test="${requestScope.prevPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">Previous</a></li>
        </c:if>

        <c:if test="${requestScope.prevPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.prevPage}">${requestScope.prevPage}</a></li>
        </c:if>

        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.currentPage}">${requestScope.currentPage}</a></li>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.nextPage}">${requestScope.nextPage}</a></li>
        </c:if>

        <c:if test="${requestScope.nextPage > 0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?user=get&&page=${requestScope.nextPage}">Next</a></li>
        </c:if>
        <c:if test="${requestScope.nextPage <= 0}">
            <li class="page-item disabled"><a class="page-link" href="#!" tabindex="-1">Next</a></li>
        </c:if>
    </ul>

    <div class="d-flex justify-content-around">
        <div class="p-2">
            <div class="alert alert-dark" role="alert">Update</div>
            <form action="${pageContext.request.contextPath}/controller?user=update" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="userEmailUpdate">Email</label>
                    <input type="email" name="email" class="form-control" id="userEmailUpdate" aria-describedby="emailHelp" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="userNameUpdate">Name</label>
                    <input type="text" name="username" class="form-control" id="userNameUpdate" placeholder="Name">
                </div>
                <div class="form-group">
                    <label for="userPasswordUpdate">Password</label>
                    <input type="password" name="password" class="form-control" id="userPasswordUpdate" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="userRoleUpdate">Role</label>
                    <select class="form-control" id="userRoleUpdate" name="role">
                        <option>Admin</option>
                        <option>User</option>
                    </select>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="userPhoto" accept="image/*" class="custom-file-input" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </form>
        </div>
        <div class="p-2">
            <div class="alert alert-dark" role="alert">Delete</div>
            <form action="${pageContext.request.contextPath}/controller?user=delete" method="post">
                <div class="form-group">
                    <label for="userEmailDelete">Email</label>
                    <input type="email" name="email" class="form-control" id="userEmailDelete" aria-describedby="emailHelp" placeholder="Enter email">
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