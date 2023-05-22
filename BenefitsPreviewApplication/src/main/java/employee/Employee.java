package employee;

import java.util.List;

public class Employee extends Person  {
    long empId;
    private double sal;
    private List<Person> dependents;
    private String title;
	private long companyId;

    public Employee(long empId, double sal, String firstName, String lastName, int age, long companyId) {
        super(firstName, lastName, age);
        this.empId=empId;
        this.sal=sal;
        this.companyId=companyId;
        if(age<0 || companyId<0 || firstName==null || lastName==null) {
        	throw new IllegalArgumentException("invalid details" ); // print all employee details
        }
    }

    public double getSalary() {
        return this.sal;
    }

	public void addDependent(Dependent dependent) {
		dependents.add(dependent);
	}
	
	public void addDependent(List<Dependent> dependents) {
		dependents.addAll(dependents);	
	}

	public long getCompanyId() {
		return companyId;
	}
}
