package configuration.config;

import benefits.BENEFIT_TYPE;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Config {
    // Mocking a config that gets initalized as part of the  application
	
    private static HashMap<String,String> appConfigMap;
    private static void initMap() {
    	appConfigMap= new HashMap<String,String>(){{
    	//get all the config parameters ;
    	//this.allowedCompanyBenefits
    		this.put("Locale","us-eng");
    		this.put("companyid=1", "true");
    		this.put("isDiscountStillValid","true");
    	}};
    }
    
    public static Config inst;
    static {
    		inst=new Config();
    }
    private Config() {
    	initMap();
    }
    
    public boolean isCompanyBenefitsOpen(long companyId){
    	return Boolean.getBoolean(appConfigMap.getOrDefault("companyId="+companyId, "true"));
    }
    public String getDefaultLanguage() {
    	return appConfigMap.getOrDefault("Locale", "us-eng");
    }
    
    public boolean isDiscountStillValid() {
    	return Boolean.valueOf(appConfigMap.getOrDefault("isDiscountStillValid", "true"));
    }
    
}
