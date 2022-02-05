<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${sessionScope.user.role eq null}">
    <jsp:include page="headerGuest.jsp" />
</c:if>
<c:if test="${sessionScope.user.role ne null}">
    <jsp:include page="headerUser.jsp" />
</c:if>