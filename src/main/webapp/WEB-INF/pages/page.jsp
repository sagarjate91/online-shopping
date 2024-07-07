<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/assets/css" />
<spring:url var="js" value="/assets/js" />
<spring:url var="images" value="/assets/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Cloud">


<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'
	window.userID='${sessionScope.role}'
	
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	
	<div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		 <!-- Message -->

           <c:if test="${not empty message}">
        		<div class="row">
        			<div class="col-xs-12 col-md-offset-2 col-md-8">
        				<div class="alert alert-info fade in"><h6 align="center">${message}</h6></div>
        			</div>
        		</div>
        	</c:if>

		<!-- Page Content -->

		<div class="content">

			<c:if test="${sessionScope.role==null}">

			            <c:if test="${userClickHome==true}">
				            <%@ include file="index.jsp" %>
			            </c:if>

			            <c:if test="${userClickUser==true or userClickAdmin==true}">
				            <%@ include file="login.jsp" %>
			            </c:if>

			            <c:if test="${userClickRegister==true}">
				            <%@ include file="sign-up.jsp" %>
			            </c:if>

            </c:if>


            <c:if test="${sessionScope.role=='USER'}">

                        <c:if test="${userClickUserHome==true or userClickHomeCategory==true}">
            				<%@ include file="user-home.jsp" %>
            			</c:if>

            			<c:if test="${userClickUserViewProfile==true}">
            				<%@ include file="view-profile.jsp" %>
            			</c:if>

            			<c:if test="${userClickUserPurchaseProduct==true}">
            				<%@ include file="purchase-product.jsp" %>
            			</c:if>

            			<c:if test="${userClickUserMyOrder==true}">
                        	<%@ include file="my-order.jsp" %>
                        </c:if>

                        <c:if test="${userClickShowSingleProduct==true}">
                            <%@ include file="single-product.jsp" %>
                        </c:if>

                        <!-- Load only when user clicks manage product -->
                        <c:if test="${userClickShowCart == true}">
                        	<%@include file="cart.jsp"%>
                        </c:if>

            </c:if>

            <c:if test="${sessionScope.role=='ADMIN'}">

                        <c:if test="${userClickAdminHome==true}">
            				<%@ include file="admin-home.jsp" %>
            			</c:if>

            			<c:if test="${userClickAdminProduct==true}">
            				<%@ include file="admin-product.jsp" %>
            			</c:if>

            			<c:if test="${userClickAdminProductList==true or userClickCategoryProducts==true}">
            				<%@ include file="product-list.jsp" %>
            			</c:if>

            			<c:if test="${adminClickShowSingleProduct==true}">
                            <%@ include file="single-product.jsp" %>
                        </c:if>

            </c:if>


		</div>


		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
		<!-- DataTable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>
		
		<!-- DataTable Bootstrap Script -->
		<script src="${js}/bootbox.min.js"></script>
		
		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>