<%--
  Created by IntelliJ IDEA.
  User: ishan
  Date: 1/30/2022
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${titleTranslate}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" type="text/css">
</head>
<body>
<jsp:include page="header.jsp" />
${email}${password}${username}
<jsp:include page="footer.jsp" />
</body>
</html>
