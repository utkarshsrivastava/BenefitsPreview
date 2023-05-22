package benefits;

import java.util.Set;


public interface IBenefit extends ICost {
    long getBenefitId();
    BENEFIT_TYPE getBenefitType();
    long getCompanyId();
    double getBenefitCost();
    boolean setBenefitCost(double cost);
}
