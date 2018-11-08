
<!DOCTYPE html>
<%@tag description="Simple Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_content" fragment="true" %>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="/jsp/commonHead.jsp"></jsp:include>
    <jsp:invoke fragment="head_area"/>
</head>
<body>
    <jsp:include page="/jsp/navbar.jsp"></jsp:include>
    <div class="container-fluid" id="page">
        <div class="row">
            <jsp:include page="/jsp/leftSideBar.jsp"></jsp:include>
            <jsp:invoke fragment="body_content"/>
        </div>
    </div>
</body>
</html>
