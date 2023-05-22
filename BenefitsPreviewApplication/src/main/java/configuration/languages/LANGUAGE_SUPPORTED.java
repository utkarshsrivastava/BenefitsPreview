package configuration.languages;

import java.util.Locale;

public enum LANGUAGE_SUPPORTED{
    English(Locale.ENGLISH, Locale.US);
    Locale lang;
    Locale region;
    LANGUAGE_SUPPORTED(Locale lang, Locale region) {
        this.lang = lang;
        this.region= region;
    }
}