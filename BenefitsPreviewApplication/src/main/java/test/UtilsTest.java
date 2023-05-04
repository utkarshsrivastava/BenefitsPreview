package test;
import org.junit.Test;

import benefits.*;
import benefits.utils.Utils;
import employee.*;
import commons.InvalidSalaryException;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    @Test
    public void testCalcSalAfterBenefits() throws InvalidSalaryException {
        // create an employee object
        Employee emp = new Employee(1,10000,"test","test",32,1);
        // create a list of benefits
        List<IBenefit> listOfBenefits = new ArrayList<>();
        listOfBenefits.add(new ChildBenefit(1,1000.00d,emp));//long benefitId, double benefitCost, Employee emp
        listOfBenefits.add(new SpouseBenefit(1,1000.00d,emp));

        // calculate the salary after benefits
        double salary = Utils.calcSalAfterBenefits(emp, listOfBenefits);

        // assert that the salary is correct
        assertEquals(8000, salary, 0.01);
    }

    @Test(expected = InvalidSalaryException.class)
    public void testCalcSalAfterBenefitsWithNegativeSalary() throws InvalidSalaryException {
        // create an employee object with negative salary
    	Employee emp = new Employee(1,-10000,"test","test",32,1);

        // create an empty list of benefits
        List<IBenefit> listOfBenefits = new ArrayList<>();

        // try to calculate the salary after benefits
        Utils.calcSalAfterBenefits(emp, listOfBenefits);
    }

    @Test
    public void testCalcSalAfterBenefitsWithNoBenefits() throws InvalidBenefitException, InvalidSalaryException {
        // create an employee object
    	Employee emp = new Employee(1,10000,"test","test",32,1);
    	
        // create an empty list of benefits
        List<IBenefit> listOfBenefits = new ArrayList<>();

        // calculate the salary after benefits
        double salary = Utils.calcSalAfterBenefits(emp, listOfBenefits);

        // assert that the salary is correct
        assertEquals(10000, salary, 0.01);
    }

    @Test(expected = InvalidSalaryException.class)
    public void testCalcSalAfterBenefitsWithInvalidBenefit() throws  InvalidSalaryException {
        // create an employee object
    	Employee emp = new Employee(1,100,"test","test",32,1);

        // create a list of invalid benefits
        List<IBenefit> listOfBenefits = new ArrayList<>();
        listOfBenefits.add(new ChildBenefit(1,200.00d,emp));

        // try to calculate the salary after benefits
        Utils.calcSalAfterBenefits(emp, listOfBenefits);
    }
}