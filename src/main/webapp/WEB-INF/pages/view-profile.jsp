<div class="container">

    <div class="col-md-3">

        <%@include file="./shared/sidebar.jsp"%>

    </div>

    <div class="col-md-9">


        <div class="row">

            <div  class="row">

                <div class="col-xs-12">

                    <table  class="table table-striped table-bordered" >

                    <thead>

                          <tr>

                            <th>Id</th>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>PinCode</th>
                            <th>Address</th>
                          </tr>

                            </thead>

                            <tbody>

                            <tr>
                               <td>${customer.id}</td>
                               <td>${customer.firstName}</td>
                               <td>${customer.lastName}</td>
                               <td>${customer.email}</td>
                               <td>${customer.mobileNumber}</td>
                               <td>${customer.address}</td>
                               <td>${customer.pinCode}</td>
                            </tr>

                            </tbody>

                </table>



                </div>

            </div>

        </div>

    </div>



</div>
