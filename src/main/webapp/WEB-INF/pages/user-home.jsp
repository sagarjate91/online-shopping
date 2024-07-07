
<div class="container">

    <div class="col-md-3">

        <%@include file="./shared/sidebar.jsp"%>

    </div>

                               <c:if test="${userClickUserHome == true}">
                                    <script>
             							window.category ='';
             						</script>

             								</c:if>

             					<c:if test="${userClickHomeCategory == true}">
             						<script>
             							window.category = '${category}';
             						</script>

             					</c:if>


    <div class="col-md-9">


        <div class="row">

            <div  class="row">

                <div class="col-xs-12">

                    <table  class="table table-striped table-bordered" id="productListTable1">

                        <thead>

                        <tr>

                            <th></th>
                            <th>Product</th>
                            <th>Model</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Description</th>
                            <th>Book</th>
                           
                        </tr>

                        </thead>


                    </table>

                </div>

            </div>

        </div>

    </div>



</div>
