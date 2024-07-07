<%@taglib prefix="c" uri="jakarta.tags.core"%>

  <div class="list-group">

    <c:if test="${sessionScope.role=='ADMIN'}">

   	<c:forEach items="${categories}" var="category">
   		<a href="${contextRoot}/admin/show/category/${category.name}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
   	</c:forEach>

   	</c:if>


      <!--  User Panel -->
      <c:if test="${sessionScope.role =='USER'}">

          <c:forEach items="${categories}" var="category">
   		        <a href="${contextRoot}/customer/show/category/${category.name}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
   	       </c:forEach>

       </c:if>

  </div>