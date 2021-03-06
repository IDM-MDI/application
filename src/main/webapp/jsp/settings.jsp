<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${titleTranslate}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
<ul class="list-group">
    <a href="${pageContext.request.contextPath}/controller?audio=get" class="list-group-item list-group-item-action list-group-item-secondary">${audioTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?battery=get" class="list-group-item list-group-item-action list-group-item-secondary">${batteryTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?category=get" class="list-group-item list-group-item-action list-group-item-secondary">${categoryTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?cpu=get" class="list-group-item list-group-item-action list-group-item-secondary">${cpuTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?gadget=get" class="list-group-item list-group-item-action list-group-item-secondary">${gadgetTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?memory=get" class="list-group-item list-group-item-action list-group-item-secondary">${memoryTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?user=get" class="list-group-item list-group-item-action list-group-item-secondary">${userTranslate}</a>
    <a href="${pageContext.request.contextPath}/controller?video=get" class="list-group-item list-group-item-action list-group-item-secondary">${videoTranslate}</a>
</ul>
<jsp:include page="footer.jsp" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" type="text/javascript"></script>

</body>
</html>