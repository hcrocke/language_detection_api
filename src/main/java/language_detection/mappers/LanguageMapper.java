package language_detection.mappers;

import language_detection.models.language.MultiLanguage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LanguageMapper {

    String INSERT_INFO = "INSERT INTO `language-users`.`language` (`searchTermOne`, `searchTermTwo`, `languageOne`, `languageTwo`, `sameLanguage`) " +
            "VALUES (#{searchTermOne}, #{searchTermTwo}, #{searchLanguageOne}, #{searchLanguageTwo}, #{sameLanguage})";

    String SELECT_INFO = "SELECT * from `language-users`.`language` where " +
            "searchTermOne = #{arg0} and searchTermTwo = #{arg1}";

    String UPDATE_INFO = "UPDATE `language-users`.`language` SET searchTermOne = #{searchTermOne}, searchTermTwo = #{searchTermTwo}, "
            + "languageOne = #{searchLanguageOne}, languageTwo = #{searchLanguageTwo} WHERE searchTermOne = #{searchTermOne}";

    String DELETE_ENTRY = "DELETE FROM `mybatis-test`.`language_info` WHERE id = #{id}";




    @Insert(INSERT_INFO)
    public int insertLangInfo(MultiLanguage result);

    @Select(SELECT_INFO)
    public MultiLanguage selectRecord(String searchTermOne, String searchTermTwo);

    @Update(UPDATE_INFO)
    public int updateInfo(MultiLanguage update);

    @Delete(DELETE_ENTRY)
    public int deleteEntry(int id);
}
