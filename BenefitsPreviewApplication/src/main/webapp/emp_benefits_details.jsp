<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"
	import="configuration.config.Config" 
	import="configuration.languages.*"
	import="java.util.*"
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
	<h1>Employee Salary Preview</h1>
	<form action="benefits_preview.jsp" method="post">
		<label><%out.write(langMap.get(STRING_IDS.ENTER_EMPLOYEE_ID));%>:</label>
		<input type="text" name="empId"><br>
		<label><%out.write(langMap.get(STRING_IDS.ENTER_COMPANY_ID));%>:</label>
		<input type="text" name="companyId"><br>
		<!--<input type="hidden" name="empId" value="${empId}">  
		<input type="hidden" name="companyId" value="${companyId}">  <br>-->
		<label><%out.write(langMap.get(STRING_IDS.DEPENDENT_NAME));%>:</label>
		<input type="text" name="dependentName1"><br>
		<label><%out.write(langMap.get(STRING_IDS.DEPENDENT_TYPE));%>:</label>
		<!-- We can iterate through BENEFIT_TYPE and create a list box too for dependent type -->
		<input type="text" name="dependentType1" value = ""><br>
		<label><%out.write(langMap.get(STRING_IDS.DEPENDENT_NAME));%>:</label>
		<input type="text" name="dependentName2" ><br>
		<label><%out.write(langMap.get(STRING_IDS.DEPENDENT_TYPE));%>:</label>
		<input type="text" name="dependentType2"  value="" ><br>
		<input type="checkbox" name="isForceRefresh" value="isForceRefresh"><%langMap.get(STRING_IDS.FORCE_REFRESH);%><br>
		<input type="submit" value="Submit">
		
		<label><br>NOTE:
		<br><br>FOR NOW, FOR DEMO, I AM ASSUMING THAT THERE ARE 2 DEPEDNENTS. IDEALLY, WE SHOULD HAVE A BUTTON 
		<br>CALLED "ADD DEPENDENT" TO DYNAMICALLY ALLOW DEPENDENT.
		<br> BUT AS IMPLIED OVER EMAIL, THE FOCUS IS LESS ON UI  :) <br>
		</label>
		
	</form>	
		
</body>	
</html>