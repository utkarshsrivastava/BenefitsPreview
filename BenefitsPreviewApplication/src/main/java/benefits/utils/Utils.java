package benefits.utils;

import benefits.IBenefit;
import benefits.InvalidBenefitException;
import commons.InvalidSalaryException;
import configuration.config.Config;
import employee.Employee;

import java.util.List;

public class Utils {
	public static final double DISCOUNT_START_A = 0.10d;//10%
    public static double calcSalAfterBenefits(Employee emp, List<IBenefit> listOfBenefits)
            throws  InvalidSalaryException {
        double salary=emp.getSalary();
        if(salary<=0){
            throw new InvalidSalaryException("salary cant be <0");
        }
        if(listOfBenefits==null || listOfBenefits.size()==0){
            return salary;//log a warn
        }
        for(IBenefit benefit : listOfBenefits){
        	salary=benefit.applyCost(salary);
            //log.debug("salary after "+benefit.getBenefitId());
            if(salary<0){
                throw new InvalidSalaryException("salary is negative. salary="+salary);
            }
        }
    return salary;
    }
    public static double getCostAfterDiscounts(String dependentName, double benefitCost) {
    	if(Config.inst.isDiscountStillValid()) { // switch off the discuount whenver you want
    		if(dependentName.charAt(0)=='A' || dependentName.charAt(0)=='a'){
    			return Math.round(benefitCost*(1-DISCOUNT_START_A));
    		}
    	}
    	return benefitCost;
    }
    
    public static boolean isBenefitsValid(long benefitId, double benefitCost) {
    	if(benefitId<=0 || benefitCost<=0){
			return false;
		}
    	return true;
    }
}
