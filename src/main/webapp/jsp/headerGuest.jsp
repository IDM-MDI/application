<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="${pageContext.request.contextPath}/controller?jsp_switcher=index" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
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

            <div class="text-end">
                <a href="${pageContext.request.contextPath}/controller?jsp_switcher=signin" class="btn btn-outline-light me-2">${signinTranslate}</a>
                <a href="${pageContext.request.contextPath}/controller?jsp_switcher=signup" class="btn btn-warning">${signupTranslate}</a>
            </div>
        </div>
    </div>
</header>
