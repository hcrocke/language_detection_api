package language_detection.services.language;


import language_detection.mappers.LanguageMapper;
import language_detection.models.language.LanguageData;
import language_detection.models.language.LanguageDetection;
import language_detection.models.language.LanguageRoot;
import language_detection.models.language.MultiLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LanguageService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LanguageMapper languageMapper;

    public LanguageRoot detectLanguage(String words) {

        String fQuery = "http://ws.detectlanguage.com/0.2/detect?q=" + words + "&key=a5cf64502b79711b2e44e64993d05ccb";
        System.out.println(fQuery);

        LanguageRoot dataRootObject = restTemplate.getForObject(fQuery, LanguageRoot.class);
        LanguageData internalData = dataRootObject.getData();
        LanguageDetection[] arrayData = internalData.getDetections();
        LanguageDetection obj1 = arrayData[0];
        String languageString = obj1.getLanguage();

        return dataRootObject;
    }

    public MultiLanguage multiLanguages(String term1, String term2, boolean persist) {

        LanguageRoot langOne = detectLanguage(term1);
        LanguageRoot langTwo = detectLanguage(term2);

        MultiLanguage obj = new MultiLanguage();

        obj.setSearchLanguageOne(langOne.getData().getDetections()[0].getLanguage());
        obj.setSearchLanguageTwo(langTwo.getData().getDetections()[0].getLanguage());
        obj.setSearchTermOne(term1);
        obj.setSearchTermTwo(term2);
        obj.setSameLanguage(obj.getSearchLanguageOne().equalsIgnoreCase(obj.getSearchLanguageTwo()));

        if(persist) {
            languageMapper.insertLangInfo(obj);
        }

        return obj;
    }

    public MultiLanguage insertLanguageInfo(MultiLanguage result) {

        String searchTermOne = result.getSearchTermOne();
        String searchTermTwo = result.getSearchTermTwo();

        int insertReturn = languageMapper.insertLangInfo(result);
        return languageMapper.selectRecord(searchTermOne, searchTermTwo);
    }
}
