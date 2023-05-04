package benefits;

import employee.Employee;

public class SpouseBenefit extends DependentBenefit {
	long benefitId;
    double benefitCost;
    private Employee dependentOn;
    
    public SpouseBenefit(long benefitId, double benefitCost, Employee emp){
    	super(benefitId,benefitCost,emp);
    }

	@Override
	public BENEFIT_TYPE getBenefitType() {
		return BENEFIT_TYPE.SPOUSE_BENEFIT;
	}
}
