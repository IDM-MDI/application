<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="${pageContext.request.contextPath}/controller?jsp_switcher=index" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/controller?jsp_switcher=gadgets" class="nav-link px-2 text-white">${gadgetsTranslate}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?jsp_switcher=pricing" class="nav-link px-2 text-white">${pricingTranslate}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?jsp_switcher=faq" class="nav-link px-2 text-white">${faqTranslate}</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?jsp_switcher=about" class="nav-link px-2 text-white">${aboutTranslate}</a></li>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="${pageContext.request.contextPath}/controller?controller=search">
                <input type="search" name="search" class="form-control form-control-dark" placeholder="${searchTranslate}" aria-label="Search">
            </form>
            <div class="btn-group">
                <button type="button" class="btn btn-dark">
                    <a href="${pageContext.request.contextPath}/controller?jsp_switcher=account" class="d-block link-dark text-decoration-none" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:if test="${sessionScope.user.photo[0] eq null}">
                            <img src="${pageContext.request.contextPath}/img/noneAvatar.jpeg" alt="mdo" width="32" height="32" class="rounded-circle">
                        </c:if>
                        <c:if test="${sessionScope.user.photo[0] ne null}">
                            <img src="data:image/jpg;base64,${avatar}" alt="mdo" width="32" height="32" class="rounded-circle">
                        </c:if>
                    </a>
                </button>
                <button type="button" class="btn btn-dark dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only"></span>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?jsp_switcher=account">${profileTranslate}</a>
                    <c:if test="${sessionScope.user.role == 'ADMIN'}">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?jsp_switcher=settings">${settingsTranslate}</a>
                    </c:if>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?user=exit">${signoutTranslate}</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle"
                        type="button" id="dropdownMenu1" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    ${languageTranslate}
                </button>
                <div class="dropdown-menu w-75" aria-labelledby="dropdownMenu1">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?language=ru">
                        <img src="${pageContext.request.contextPath}/img/1200px-Flag_of_Russia.svg.png" alt="mdo" width="64" height="32">
                    </a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?language=en">
                        <img src="${pageContext.request.contextPath}/img/1235px-US_flag_51_stars.svg.png" alt="mdo" width="64" height="32">
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>