package configuration.cache;

import java.util.LinkedHashMap;
import java.util.List;

import benefits.IBenefit;
import employee.Employee;

public class Cache extends LinkedHashMap{
	public static boolean containsKeyy(long empId,long companyId) {
		return false;
	}
	public static Employee getEmpDetails(long empId,long companyId) {
		return null;
	}
	public static List<IBenefit> getBenefitDetails(long companyId) {
		return null;
	}
//just mocking a cache that will keep the most recently searched employees.
// this will percent from unnecessary DB reads
}