<div class="container">

	<!-- Breadcrumb -->
	<div class="row">
		
		<div class="col-xs-12">
		
			
			<ol class="breadcrumb">

			<c:if test="${sessionScope.role =='ADMIN'}">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.category}</li>
			</c:if>

		    <c:if test="${sessionScope.role =='USER'}">
				<li><a href="${contextRoot}/customer/user-home.htm">Home</a></li>
                <li><a href="${contextRoot}/customer/user-home.htm">Products</a></li>
                <li class="active">${product.category}</li>
			</c:if>

			</ol>

		</div>

	</div>
	
	
	<div class="row">
	
		<!-- Display the product image -->
		<div class="col-xs-12 col-sm-4">
		
			<div class="thumbnail">
							
				<img src="/assets/images/${product.fileName}" class="img img-responsive"/>
						
			</div>
		
		</div>
	
		
		<!-- Display the product description -->	
		<div class="col-xs-12 col-sm-8">
		
			<h3>${product.category}</h3>
			<hr/>
			
			<p>${product.description}</p>
			<hr/>
			
			<h4>Price: <strong> &#8377; ${product.price} /-</strong></h4>
			<hr/>
			
			
			
			<c:choose>
				
				<c:when test="${product.quantity < 1}">
				
					<h6>Qty. Available: <span style="color:red">Out of Stock!</span></h6>
					
				</c:when>
				<c:otherwise>				
					
					<h6>Qty. Available: ${product.quantity}</h6>
						
				</c:otherwise>
			
			</c:choose>

			 <c:if test="${sessionScope.role=='ADMIN'}">
				<a href="${contextRoot}/admin/manage/${product.productId}/product" class="btn btn-success">
				<span class="glyphicon glyphicon-pencil"></span> Edit</a>
			 </c:if>

            <c:if test="${sessionScope.role =='ADMIN'}">

			<a href="${contextRoot}/admin/ProductList.htm" class="btn btn-warning">Continue Shopping</a>

            </c:if>

		    <c:if test="${sessionScope.role =='USER'}">

            	<c:choose>

                	<c:when test="${product.quantity < 1}">

                			<a href="javascript:void(0)" class="btn btn-success disabled"><strike>
                			<span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</strike></a>

               		</c:when>
               		<c:otherwise>

          				<a href="${contextRoot}/customer/cart/add/${product.productId}/product" class="btn btn-success">
           				<span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</a>

       				</c:otherwise>
                </c:choose>

                    <a href="${contextRoot}/customer/user-home.htm" class="btn btn-warning">Continue Shopping</a>

             </c:if>
					
		</div>

	
	</div>

</div>