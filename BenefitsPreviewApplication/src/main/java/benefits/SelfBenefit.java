package benefits;

import employee.Employee;

public class SelfBenefit extends DependentBenefit {

	private BENEFIT_TYPE benefitType;
    
    public SelfBenefit(long benefitId, double benefitCost, Employee emp){
    	super(benefitId,benefitCost,emp);
    	this.benefitType=BENEFIT_TYPE.SELF_EMP_BENEFIT;
    }
    
	@Override
	public double applyCost(double amount) {
		return amount-benefitCost;
	}
	@Override
	public long getBenefitId() {
		return benefitId;
	}

	public Employee getEmp() {
		return dependentOn; //self
	}

	@Override
	public long getCompanyId() {
		// TODO Auto-generated method stub
		return dependentOn.getCompanyId();
	}

	@Override
	public BENEFIT_TYPE getBenefitType() {
		return benefitType;
	}

}
