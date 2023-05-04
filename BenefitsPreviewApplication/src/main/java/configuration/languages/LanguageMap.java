package configuration.languages;


import java.util.EnumMap;
import java.util.Locale;

public class LanguageMap {
    final String languageOfChoice;
    final String region;
    private static EnumMap<STRING_IDS,String> currentLangMap;
    
    public LanguageMap(String languageOfChoice, String region) {
        this.region=region;
        this.languageOfChoice = languageOfChoice;
        this.currentLangMap = initLangMap(new Locale(languageOfChoice,region));
    }

    private static EnumMap<STRING_IDS, String> initLangMap(Locale locale) {
        // initialzie with string ids and later on populate it from the database
        EnumMap<STRING_IDS, String> map = new EnumMap<STRING_IDS,String>(STRING_IDS.class){{
            for(STRING_IDS string_ids: STRING_IDS.setOfStringIds){
                this.put(string_ids,string_ids.toString());
            }
        }};
        //Mocking instead of reading from the database loaded at initialization of application, based on the Locale that was passed
        //log.info("langMap initializing");
        map.put(STRING_IDS.ENTER_EMPLOYEE_ID,"Enter Employee Id");
        map.put(STRING_IDS.ENTER_COMPANY_ID,"Enter company Id");
        map.put(STRING_IDS.EMPLOYEE_BENEFIT_COST,"Benefits cost is : ");
        map.put(STRING_IDS.ADD_DEPENDENT_BUTTON,"ADD DEPENDENT");
        map.put(STRING_IDS.DEPENDENT_TYPE,"Dependent type");
        map.put(STRING_IDS.CHILD_LIST_BOX_ENTRY,"Child");
        map.put(STRING_IDS.SPOUSE_LIST_BOX_ENTRY,"Spouse");
        map.put(STRING_IDS.DEPENDENT_NAME,"Dependent name");
        map.put(STRING_IDS.FORCE_REFRESH,"Force Refresh?");
        map.put(STRING_IDS.SUBMIT_BUTTON,"Submit");
        map.put(STRING_IDS.PREVIEW_BUTTON,"Preview");
        map.put(STRING_IDS.REFRESH_BUTTON,"Refresh");
        map.put(STRING_IDS.BACK_BUTTON,"Back");
        map.put(STRING_IDS.EMP_NOT_FOUND,"Employee not found.");
        

        // Stub code for  FUNCTIONALITY HERE THAT READ THE STRINGS
        /*String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(locale.getDisplayName().toUpperCase()))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split("=", 2);
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    String value = keyValuePair[1];
                    if (DupKeyOption.OVERWRITE == dupKeyOption) {
                        map.put(key, value);
                    } else if (DupKeyOption.DISCARD == dupKeyOption) {
                        map.putIfAbsent(key, value);
                    }
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return map;
    }
    public String get(STRING_IDS string_id){
    	return currentLangMap.get(string_id);
    }


}

