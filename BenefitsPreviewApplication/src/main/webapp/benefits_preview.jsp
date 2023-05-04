<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"
	import="configuration.config.Config" 
	import="configuration.languages.*"
	import="configuration.cache.Cache"  
	import="java.util.*"
	import="benefits.utils.Utils"
	import="employee.Employee"
	import="benefits.*"%>
<html>
<head>
<title>Benefits Preview Package</title>
</head>
<body>
	<%
	String lang= Config.inst.getDefaultLanguage();
	if(! Arrays.asList(LANGUAGE_SUPPORTED.values()).contains(lang)){
		//warning or error
		lang="English";
	}
	LanguageMap langMap = new LanguageMap("English","US");// we could have an option in the Ui to change the langauge. mocking for now
	%>
	<h1>Employee Salary Preview</h1>
	<%
	String sqlEmpBenefits = "SELECT * FROM emp WHERE employeeId = ? AND companyId = ?";
	String sqlBenfitInfo = "SELECT benefitId,benefitCost,benefitType FROM benefits WHERE companyId = ?";
	Class.forName("org.sqlite.JDBC");
	try(Connection con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\utkar\\eclipse-workspace\\chinook\\chinook.db");
			PreparedStatement pstmt = con.prepareStatement(sqlEmpBenefits);
			PreparedStatement pstmt2 = con.prepareStatement(sqlBenfitInfo);){
		// Retrieve the input values from the form
		String emplId = request.getParameter("empId");
		String compId = request.getParameter("companyId");
		String dependentName1 = request.getParameter("dependentName1");
		String dependentName2 = request.getParameter("dependentName2");
		
		Employee emp=null;
		long empId=Long.parseLong(emplId);
		long companyId=Long.parseLong(compId);
		List<IBenefit> benefits =null;
		//boolean isForceRefresh = "isForceRefresh".equals(request.getParameterValues("isForceRefresh")[0]);
		boolean isForceRefresh=true;
		if(Cache.containsKeyy(empId,companyId) && !isForceRefresh){
			emp=Cache.getEmpDetails(empId,companyId);
			benefits=Cache.getBenefitDetails(companyId);
		}
		else{
		//log.debug "missed cache" 
		// Establish a database connection
			
		// Prepare a SQL statement to retrieve the employee salary
			pstmt.setString(1, emplId);
			pstmt.setString(2, compId);
		
		// Execute the SQL statement and retrieve the result
			ResultSet rs = pstmt.executeQuery();
			double sal= Double.parseDouble(rs.getString("salary"));
			String fname= rs.getString("FirstName");
			String lname= rs.getString("LastName");
			int age = Integer.parseInt(rs.getString("age"));		
		// Display the result
			if(rs.next()) {
				out.println("<p>Employee empId=" + rs.getString("employeeId")+" salary="+rs.getString("salary") + "</p>");
			}
			else {
				out.println("<p>"+langMap.get(STRING_IDS.EMP_NOT_FOUND)+"</p>");
				return;
			}
			emp= new Employee(empId,sal,fname,lname,age,companyId);	
			
			// now to preview benefits
			pstmt2.setString(1, compId);
		
		// Execute the SQL statement and retrieve the result
			ResultSet rsBenefit = pstmt2.executeQuery();
			long benefitId = -1;
			double benefitCost = -1;
			BENEFIT_TYPE benefitType = null;
			Map<BENEFIT_TYPE,IBenefit> avlblBenefitByType = new EnumMap<BENEFIT_TYPE,IBenefit>(BENEFIT_TYPE.class);
			while(rsBenefit.next()){
				benefitId=Long.parseLong(rsBenefit.getString("benefitId"));
				benefitCost=Double.parseDouble(rsBenefit.getString("benefitCost"));
				benefitType=BENEFIT_TYPE.valueOf(rsBenefit.getString("benefitType"));
				//log.debug(benefitType+" rsbenefit benefit cost :"+benefitCost);
				avlblBenefitByType.put(benefitType,BenefitFactory.getBenefitInstance(benefitId, 
						benefitCost, benefitType, emp));
			}		
			
			
			//depdennts populated
			int numOfDependents=2;
			//List<Benefits>
			benefits = new ArrayList<IBenefit>(numOfDependents);
			for(int num=1; num<=numOfDependents;num++){
				BENEFIT_TYPE depBenefitType = BENEFIT_TYPE.valueOf(request.getParameter("dependentType"+num));
				if(avlblBenefitByType.containsKey(depBenefitType)){
					IBenefit benefit=avlblBenefitByType.get(depBenefitType);
					benefit.setBenefitCost(
							Utils.getCostAfterDiscounts(request.getParameter("dependentName"+num),benefit.getBenefitCost()));
					//log.debug(benefit.getBenefitType()+" avlblBenefitByType benefit cost :"+benefit.getBenefitCost());
					benefits.add(benefit);
				}
				else{
					throw new InvalidBenefitException("benefitType="+benefitType+" is not valid for companyId="+companyId);
				}
			}
			//add self benefit
			IBenefit empBenefit=avlblBenefitByType.get(BENEFIT_TYPE.SELF_EMP_BENEFIT);
			empBenefit.setBenefitCost(
					Utils.getCostAfterDiscounts(emp.getName(),empBenefit.getBenefitCost()));
			//log.debug("benefit cost :"+empBenefit.getBenefitCost());
			benefits.add(empBenefit);
			
		//	 Close the database connection
			out.println("<label> net salary after benefits is : "+Utils.calcSalAfterBenefits(emp, benefits)+"</label>");
			rs.close();
			rsBenefit.close();
		}
	}catch(SQLException sqle){
		out.println(sqle.getMessage());
		//sqle.printStackTrace(LoggerPrintStream);
	}
	catch(InvalidBenefitException ibe){
		out.println(ibe.getMessage());
	}
	//catch(Exception e){
		//out.println(e.getMessage());
	//}
	%>
	<br><a href="emp_benefits_details.jsp">Back<%langMap.get(STRING_IDS.BACK_BUTTON);%></a>	
</body>	
</html>