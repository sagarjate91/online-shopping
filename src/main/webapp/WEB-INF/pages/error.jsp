<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}'	
</script>


<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

   <%@include file="./shared/navbar.jsp"%>
        

<div class="container">
         
         
        <div class="col-md-3">
        
          
        </div>
         
                     <div class="jumbotron">

						<h1>${errorTitle}</h1><hr/>

						<blockquote>
							${errorDescription}
						</blockquote>
						
						<h1>Not Found Page..!!!</h1>
						

         </div>
               
      </div>
  
    <!-- Footer -->
    <%@ include file="./shared/footer.jsp" %>
    
   
  </body>

</html>
