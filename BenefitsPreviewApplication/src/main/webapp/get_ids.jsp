<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="configuration.config.Config" 
	import="configuration.languages.*"
	import="configuration.cache.Cache"
	import="java.util.Arrays"  
%>
<html>
<head>
<title>Benefits Preview Package</title>
</head>
<body>
	<%
	String lang= Config.inst.getDefaultLanguage();
	if(!Arrays.asList(LANGUAGE_SUPPORTED.values()).contains(lang)){
		//warning or error
		lang="English";
	}
	LanguageMap langMap = new LanguageMap("English","US");// we could have an option in the Ui to change the langauge. mocking for now
	%>
	<h1>Enter Employee Details</h1>
	<form action="emp_benefits_details.jsp" method="post">
		<label><%langMap.get(STRING_IDS.ENTER_EMPLOYEE_ID);%>:</label>
		<input type="text" name="empId"><br>
		<label><%langMap.get(STRING_IDS.ENTER_COMPANY_ID);%>:</label>
		<input type="text" name="companyId"><br>
		<input type="submit" value="Submit">
	</form>
</body>	
</html>