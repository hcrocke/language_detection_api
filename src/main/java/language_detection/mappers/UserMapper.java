package language_detection.mappers;

import language_detection.models.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String GET_ALL__ACTIVE_USERS = "SELECT * FROM `language-users`.Users where isActive = 1";
    String GET_BY_ID = "SELECT * FROM `language-users`.Users where id = #{id}";
    String INSERT_USER = "INSERT INTO `language-users`.Users (givenName, surname, primaryLanguage, isActive, apiKey) " +
            "VALUES (#{givenName}, #{surname}, #{primaryLanguage}, #{isActive}, #{apiKey})";
    String UPDATE_USER = "UPDATE `language-users`.Users SET givenName = #{givenName}, surname = #{surname}, " +
            "primaryLanguage = #{primaryLanguage}, isActive = #{isActive} WHERE id = #{id}";
    String DELETE_USER = "UPDATE `language-users`.Users set isActive = 0 WHERE id = #{id}";
    String GET_BY_NAME = "SELECT * FROM `language-users`.Users where givenName = #{arg0} and surname = #{arg1}";
    String AUTHENTICATE = "SELECT isActive from `language-users` .Users WHERE apiKey = #{apiKey}";
    //    String CUSTOM_QUERY = "SELECT * FROM `language-users`.Users where primaryLanguage = #{primaryLanguage}";

    @Select(GET_ALL__ACTIVE_USERS)
    public ArrayList<User> getAllUsers();

    @Select(GET_BY_ID)
    public User getByID(int id);

    @Select(GET_BY_NAME)
    public User getByName(String givenName, String surname);

    @Insert(INSERT_USER)
    public int insertUser (User user);

    @Update(UPDATE_USER)
    public int updateUser (User user);

    @Delete(DELETE_USER)
    public int deleteUser(int id);

    @Select(AUTHENTICATE)
    boolean authenticate(String apiKey);

//    @Select(CUSTOM_QUERY)
//    public ArrayList<User> getUserByPrimaryLanguage(String primaryLanguage);
//
}
