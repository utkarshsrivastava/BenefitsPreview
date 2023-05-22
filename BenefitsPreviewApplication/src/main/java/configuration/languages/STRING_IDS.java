package configuration.languages;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum STRING_IDS {
    ENTER_EMPLOYEE_ID,
    ENTER_COMPANY_ID,
    EMPLOYEE_BENEFIT_COST,
    ADD_DEPENDENT_BUTTON,
    DEPENDENT_TYPE,
    CHILD_LIST_BOX_ENTRY,
    SPOUSE_LIST_BOX_ENTRY,
    DEPENDENT_NAME,
    FORCE_REFRESH,
    SUBMIT_BUTTON,
    PREVIEW_BUTTON,
    REFRESH_BUTTON,
    BACK_BUTTON,
    EMP_NOT_FOUND;
    final static Set<STRING_IDS> setOfStringIds= new HashSet<>(){{
        for (STRING_IDS string_ids:STRING_IDS.values()) {
            this.add(string_ids);
        }
        Collections.unmodifiableSet(this);
    }};
}
