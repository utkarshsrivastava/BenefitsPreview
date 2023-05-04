package benefits;

import employee.Employee;

public abstract class DependentBenefit implements IBenefit {
	protected long benefitId;
    protected Employee dependentOn;
	protected double benefitCost;
    
    public DependentBenefit(long benefitId, double benefitCost, Employee emp){
    	this.benefitId=benefitId;
    	this.dependentOn = emp;
    	this.benefitCost=benefitCost;
    }
    
	@Override
	public long getBenefitId() {
		return benefitId;
	}
	
	@Override
	public boolean setBenefitCost(double cost) {
		this.benefitCost=cost;
		return true;
	}
	
	@Override
	public double getBenefitCost() {
		return this.benefitCost;
	}
	
	@Override
	public double applyCost(double amount) {
		return amount - benefitCost;
	}
	
	public Employee getDependentOn() {
		return dependentOn;
	}
	
	@Override
	public long getCompanyId() {
		return dependentOn.getCompanyId();
	}
}
