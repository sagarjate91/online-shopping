<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="row">
     
     <div class="col-md-offset-3 col-md-6">
      
      <div class="panel panel-primary">
       
       <div class="panel-heading">
        <h4>Login</h4>
       </div>
       
       <div class="panel-body">

      <sf:form action="${contextRoot}/${action}" modelAttribute="command" method="POST" class="form-horizontal" id="loginForm">

          <div class="form-group">
              <label for="email" class="col-md-4 control-label">Email: </label>
              <div class="col-md-8">
                  <sf:input path="email" id="email" class="form-control" placeholder="Enter the email"/>
                  <sf:errors path="email" cssClass="help-block" element="em"/>
              </div>
          </div>

          <div class="form-group">
              <label for="password" class="col-md-4 control-label">Password: </label>
              <div class="col-md-8">
                  <sf:input type="password" path="password" id="password" class="form-control" placeholder="xxxxxxxxx"/>
                  <sf:errors path="password" cssClass="help-block" element="em"/>
              </div>
          </div>

         <div class="form-group">
          <div class="col-md-offset-4 col-md-8">
           <input type="submit" value="Login" class="btn btn-primary"/>
          </div>
         </div>
      </sf:form>
       
       </div>
      
     
     <c:if test="${userClickUser==true}">
     	
       <div class="panel-footer">
       	<div class="text-right">
       	
             New User - <a href="${contextRoot}/customer/registerPanel.htm">Register Here</a>
     	      
       	</div>
        </div>
       
     </c:if>    
     
      </div> 
    
     </div>
     
    </div>    
   
