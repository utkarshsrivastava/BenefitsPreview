package benefits;

import employee.Employee;

public class ChildBenefit extends DependentBenefit {

	private BENEFIT_TYPE benefitType;

	public ChildBenefit(long benefitId, double benefitCost, Employee emp) {
		super(benefitId, benefitCost, emp);
		this.benefitType=BENEFIT_TYPE.CHILD_BENEFIT;
	}

	@Override
	public BENEFIT_TYPE getBenefitType() {
		return benefitType;
	}

}
