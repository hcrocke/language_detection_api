package language_detection.models.language;

public class MultiLanguage {

    String searchLanguageOne;
    String searchLanguageTwo;
    String searchTermOne;
    String searchTermTwo;
    boolean sameLanguage;
    String apiKey;

    public String getApiKey() { return apiKey; }

    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getSearchLanguageOne() {
        return searchLanguageOne;
    }

    public void setSearchLanguageOne(String searchLanguageOne) {
        this.searchLanguageOne = searchLanguageOne;
    }

    public String getSearchLanguageTwo() {
        return searchLanguageTwo;
    }

    public void setSearchLanguageTwo(String searchLanguageTwo) {
        this.searchLanguageTwo = searchLanguageTwo;
    }

    public String getSearchTermOne() {
        return searchTermOne;
    }

    public void setSearchTermOne(String searchTermOne) {
        this.searchTermOne = searchTermOne;
    }

    public String getSearchTermTwo() {
        return searchTermTwo;
    }

    public void setSearchTermTwo(String searchTermTwo) {
        this.searchTermTwo = searchTermTwo;
    }

    public boolean isSameLanguage() {
        return sameLanguage;
    }

    public void setSameLanguage(boolean sameLanguage) {
        this.sameLanguage = sameLanguage;
    }

}
