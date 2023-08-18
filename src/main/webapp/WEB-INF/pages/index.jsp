<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  </head>
<body>
	
	<div class="container">
	    <div style="padding:40px">
	      <h2 style="color: #0ee334"><center>Insurance Report</center></h2>
	    </div>
		
	<div style="padding:40px">
		<frm:form action="search"  method="post" modelAttribute="searchRequest">
		  <table class="table table-borderless">
		  	<tr>
		  		<td>
		  		    <h5 style="text-align: center">Plan Name</h5>
		  		</td>
		  		<td>    
					<frm:select class="form-select" path="planName" >
						<frm:option value="">-SELECT-</frm:option> 
						<c:forEach items="${planNameList}" var="name">
							<frm:option value="${name}">${name}</frm:option>  
						</c:forEach> 
					</frm:select>
					
		  		</td>
		  		<td>
		  		    <h5 style="text-align: center">Plan Status</h5>
		  		</td>
		  		<td>    
					<frm:select class="form-select" path="planStatus">
						<frm:option value="">-SELECT-</frm:option> 
						<c:forEach items="${planStatusList}" var="status">
							<frm:option value="${status}">${status}</frm:option>  
						</c:forEach> 
					</frm:select>
		  		</td>
		  		<td>
		  		    <h5 style="text-align: center">Gender</h5>
		  		</td>
		  		<td>
		  			<frm:select class="form-select" path="gender">
						<frm:option value="">-SELECT-</frm:option> 
						<c:forEach items="${genderList}" var="gender">
							<frm:option value="${gender}">${gender}</frm:option>  
						</c:forEach> 
					</frm:select>    
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>
		  			<h5 style="text-align: center">Start Date</h5>
		  		</td>
		  		<td>
		  			<frm:input type="date" class="form-control" path="startDate" value="${startDate}"/>
		  		</td>
		  		<td>
		  			<h5 style="text-align: center">Start End</h5>
		  		</td>
		  		<td>
		  			<frm:input type="date" class="form-control" path="endDate" value="${endDate}"/>
		  		</td>
		  		<td colspan="2">
		  			<center><button type="submit" class="btn btn-primary">Search</button></center>
		  		</td>
		  	</tr>
		  </table>
		  
		</frm:form>
		</div>
		
		<c:if test="${listCitizens != null}">
			<hr>
				<div class="pt-3 pb-3">
					<table class="table table-striped table-hover">
						<tr>
							<th>SL No.</th>
							<th>Name</th>
							<th>Plan Name</th>
							<th>Plan Status</th>
							<th>Gender</th>
							<th>Start Date</th>
							<th>End Date</th>
						</tr>
						<c:if test="${empty listCitizens}">
							<tr><td style="text-align: center" colspan="7">No Results Found</td></tr>
						</c:if>
						<c:forEach items="${listCitizens}" var="citizens" varStatus="index.count">
						  <tr>
								<td>${index.count}</td>
								<td>${citizens.citizenName}</td>
								<td>${citizens.planName}</td>
								<td>${citizens.planStatus}</td>
								<td>${citizens.gender}</td>
								<td>${citizens.planStartDate}</td>
								<td>${citizens.planEndDate}</td>
						  </tr>
					    </c:forEach>
					</table>
		      </div>
			<hr>
		</c:if>
		
		
		<div style="padding:40px; text-align: center">
		  		Export:
		  		<a href="./export" class="btn btn-outline-info">EXCEL</a>
		  		<a href="./pdf"  class="btn btn-outline-info">PDF</a>
		  		
		</div>
		
	</div>

  


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>