<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">

    <div class="col-md-3">

        <%@include file="./shared/sidebar.jsp"%>

    </div>

    <div class="col-md-9">

               <c:if test="${userClickAdminProduct == true}">
                                    <script>
             							window.category ='';
             						</script>
             	</c:if>

        <div class="row">

            <div  class="row">

                <div class="col-xs-12">

                    <div class="panel panel-primary">

                        <div class="panel-heading">
                            <h4>Product Details</h4>
                        </div>

                        <div class="panel-body">

                            <sf:form action="${contextRoot}/${action}" modelAttribute="command" method="POST" enctype="multipart/form-data" class="form-horizontal" id="productForm">



                                <div class="form-group">
                                    <label for="post_name" class="col-md-4 control-label">Product Name: </label>
                                    <div class="col-md-8">
                                        <sf:input path="postName" id="postName" class="form-control" placeholder="Enter the product name"/>
                                        <sf:errors path="postName" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="category" class="col-md-4 control-label">Category: </label>
                                    <div class="col-md-8">
                                        <sf:select path="category" items="${categories}" itemLabel="name" itemValue="name" class="form-control"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="model_no" class="col-md-4 control-label">Model No: </label>
                                    <div class="col-md-8">
                                        <sf:input path="modelNo" id="modelNo" class="form-control" placeholder="Enter the model no"/>
                                        <sf:errors path="modelNo" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="price" class="col-md-4 control-label">Price: </label>
                                    <div class="col-md-8">
                                        <sf:input path="price" id="price" class="form-control" placeholder="xxxxx"/>
                                        <sf:errors path="price" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="quantity" class="col-md-4 control-label">Quantity: </label>
                                    <div class="col-md-8">
                                        <sf:input path="quantity" id="uses" class="form-control" placeholder="Enter the quantity"/>
                                        <sf:errors path="quantity" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="description" class="col-md-4 control-label">Description: </label>
                                    <div class="col-md-8">
                                        <sf:input path="description" id="description" class="form-control" placeholder="Enter the description"/>
                                        <sf:errors path="description" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="file" class="col-md-4 control-label">Select File: </label>
                                    <div class="col-md-8">
                                        <sf:input type="file" path="file" />
                                        <sf:errors path="file" cssClass="help-block" element="em"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" value="Add" class="btn btn-primary"/>
                                        <div class="text-right">
                                           	<br/>
                                          	<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#myCategoryModal">Add New Category</button>
                                        </div>
                                    </div>
                                </div>
                            </sf:form>



                                  <!-- Modal -->
                                        <div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title" id="myModalLabel">New Category</h4>
                                                    </div>
                                                    <div class="modal-body">

                                                        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category-model" action="${contextRoot}/admin/manage/category" method="POST">

                                                            <div class="form-group">
                                                                <label class="control-label col-md-4">Name</label>
                                                                <div class="col-md-8 validate">
                                                                    <sf:input type="text" path="name" class="form-control"
                                                                              placeholder="Category Name" />
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-4">Description</label>
                                                                <div class="col-md-8 validate">
                                                                    <sf:textarea path="description_category" class="form-control"
                                                                                 placeholder="Enter category description here!" />
                                                                </div>
                                                            </div>


                                                            <div class="form-group">
                                                                <div class="col-md-offset-4 col-md-4">
                                                                    <input type="submit" name="submit" value="Category" class="btn btn-primary"/>
                                                                </div>
                                                            </div>
                                                        </sf:form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                        </div>

                    </div>

                     <hr/>
                                   <h1>Available Products</h1>
                     <hr/>

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
                                                   <tr>

                                                   </tbody>



                                              </table>

                                 </div>

                               </div>

                </div>

            </div>

        </div>

    </div>
</div>
