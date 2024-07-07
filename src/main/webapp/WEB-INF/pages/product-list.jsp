  
   <div class="container">
   
   <div class="col-md-3">
        
          <%@include file="./shared/sidebar.jsp"%>
           
        </div>

          <!-- to display the actual products -->
             		<div class="col-md-9">

             			<!-- Added breadcrumb component -->
             			<div class="row">

             				<div class="col-lg-12">

             					<c:if test="${userClickAdminProductList == true}">
                                    <script>
             							window.category ='';
             						</script>

             						<ol class="breadcrumb">

             							<li><a href="${contextRoot}/admin/adminHome.htm">Home</a></li>
             							<li class="active">All Products</li>

             						</ol>
             					</c:if>

             					<c:if test="${userClickCategoryProducts == true}">
             						<script>
             							window.category = '${category}';
             						</script>

             						<ol class="breadcrumb">

             							<li><a href="${contextRoot}/admin/adminHome.htm">Home</a></li>
             							<li class="active">Category</li>
	                                    <li class="active">${category}</li>
             						</ol>
             					</c:if>

             				</div>

             			</div>


         <div class="col-md-9">
            
            
            <div class="row">
            
                  <div  class="row">
                  
                     <div class="col-xs-12">
             
              			<table  class="table table-striped table-bordered" id="productListTable">

                                                                        <thead>

                                                                           <tr>

                                                                               <th>Id</th>
                                                                               <th>Image</th>
                                                                               <th>Product</th>
                                                                               <th>Category</th>
                                                                               <th>Model</th>
                                                                               <th>Price</th>
                                                                               <th>Quantity</th>
                                                                               <th>Description</th>
                                                                               <th>Active</th>
                                                                               <th>view</th>
                                                                               <th>Edit</th>
                                                                            </tr>

                                                                           </thead>

                                                                           <tbody>

                                                                             <tr>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                                  <td></td>
                                                                             </tr>

                                                                           </tbody>

                                                                      </table>
             
             </div>
             
           </div>
             
        </div>
        
     </div>
             
             
        
   </div>
