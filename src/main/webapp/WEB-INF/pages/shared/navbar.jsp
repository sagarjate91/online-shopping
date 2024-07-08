<%@taglib prefix="c" uri="jakarta.tags.core"%>
	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextRoot}/customer/">Online shopping Apps</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
					<c:if test="${sessionScope.role==null}">

						<li id="home"><a href="${contextRoot}/customer/home.htm">Home</a></li>
						<li id="user"><a href="${contextRoot}/customer/user.htm">User</a></li>
						<li id="admin"><a href="${contextRoot}/customer/adminPanel.htm">Admin</a></li>
						<li id="signup"><a href="${contextRoot}/customer/registerPanel.htm">Signup</a></li>

					</c:if>

					<c:if test="${sessionScope.role=='USER'}">

						<li id="userhome"><a href="${contextRoot}/customer/user-home.htm">All Products</a></li>
						<li id="myorder"><a href="${contextRoot}/customer/cart/show">My Order</a></li>
						<li id="userviewprofile"><a href="${contextRoot}/customer/${sessionScope.userID}/view-profile.htm">Profile</a></li>

                        <li class="dropdown" id="userModel">
                        						  <a class="btn btn-default dropdown-toggle" href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        						    ${userModel.firstName}
                        						    <span class="caret"></span>
                        						  </a>
                        						  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        		                       <li id="cart">
                        			                        <a href="${contextRoot}/customer/cart/show">
                        			                        	<span class="glyphicon glyphicon-shopping-cart"></span>&#160;<span class="badge">${userModel.cart.cartLines}</span> - &#8377; ${userModel.cart.grandTotal}
                        			                        </a>
                        			                    </li>
                        			                	<li role="separator" class="divider"></li>

                        							<li id="logout">
                        		                        <a href="${contextRoot}/customer/logout">Logout</a>
                        		                    </li>
                        						  </ul>
                        						</li>

					</c:if>

					<c:if test="${sessionScope.role=='ADMIN'}">

						<li id="adminhome"><a href="${contextRoot}/admin/adminHome.htm">Home</a></li>
						<li id="adminproduct"><a href="${contextRoot}/admin/Product.htm">Product</a></li>
						<li id="adminproductlist"><a href="${contextRoot}/admin/ProductList.htm">Product List</a></li>
						<li><a href="${contextRoot}/admin/logout">Logout</a></li>

					</c:if>

				</ul>
			    

                
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

