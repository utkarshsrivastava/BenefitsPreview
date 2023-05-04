package benefits;

import employee.Employee;

public class BenefitFactory {
	
	public static IBenefit getBenefitInstance(long benefitId, double benefitCost, BENEFIT_TYPE benefitType, Employee emp) {
		if (benefitType == BENEFIT_TYPE.CHILD_BENEFIT) {
			return new ChildBenefit(benefitId,benefitCost,emp);
		}
		if (benefitType == BENEFIT_TYPE.SPOUSE_BENEFIT) {
			return new SpouseBenefit(benefitId,benefitCost, emp);
		}
		if (benefitType == BENEFIT_TYPE.SELF_EMP_BENEFIT) {
			return new SelfBenefit(benefitId,benefitCost,emp);
		}
		return null;
	}

}
