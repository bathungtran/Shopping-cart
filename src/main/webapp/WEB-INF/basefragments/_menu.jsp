<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
<div class="menu-container" >
	<ul class="nav">
	  <li class="nav-item">
	    <a href="${pageContext.request.contextPath}/">Home</a>
	  </li>
	  <li class="nav-item">
	    <a href="${pageContext.request.contextPath}/productList">
	      Product List
	   </a>
	  </li>
	  <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
	     <li class="nav-item">
	     <a href="${pageContext.request.contextPath}/orderList">
	         Order List
	     </a>
	    </li>
	   </security:authorize>
	   <security:authorize  access="hasRole('ROLE_MANAGER')">
	   <li class="nav-item">
	         <a href="${pageContext.request.contextPath}/product">
	                        Create Product
	         </a>
	   </li>
	   </security:authorize>
	</ul>
	<div class="float-right">
		  <a href="${pageContext.request.contextPath}/shoppingCart">
		  		<i class="fas fa-shopping-cart"></i> Cart
		  </a>
	 </div> 
	
</div>
