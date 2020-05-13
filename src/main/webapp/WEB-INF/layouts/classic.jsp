<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
 <!-- CSS only -->
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/static/css/styles.css" rel="stylesheet" >
</head>
 
<body>
	<div class="container-fluid"> 
	    <div class="row">
	    	 <div class="col-12">
	    	 	<tiles:insertAttribute name="header" />
	    	 </div>
	    </div>
	     <div class="row">
	    	 <div class="col-12">
	    	 	<tiles:insertAttribute name="menu" />
	    	 </div>
	    </div>
	    
	    <div class="row">
	    	 <div class="col-12">
	    	 	<tiles:insertAttribute name="body" />

	    	 </div>
	    </div>
	    
	    <div class="row">
	    	 <div class="col-12">
	    	 	 <tiles:insertAttribute name="footer" />
	    	 </div>
	    </div>
	</div>
</body>
</html>