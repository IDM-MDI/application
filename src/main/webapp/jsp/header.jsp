<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if roleTest="${role == null}">
    <jsp:include page="headerGuest.jsp" />
</c:if>
<c:if test="${role != null}">
    <jsp:include page="headerUser.jsp" />
</c:if>