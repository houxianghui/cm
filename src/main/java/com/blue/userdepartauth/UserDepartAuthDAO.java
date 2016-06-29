package com.blue.userdepartauth;

import com.blue.userdepartauth.UserDepartAuth;
import com.blue.userdepartauth.UserDepartAuthExample;
import java.util.List;

public interface UserDepartAuthDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int countByExample(UserDepartAuthExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int deleteByExample(UserDepartAuthExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    void insert(UserDepartAuth record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    void insertSelective(UserDepartAuth record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    List selectByExample(UserDepartAuthExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    UserDepartAuth selectByPrimaryKey(Long id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int updateByExampleSelective(UserDepartAuth record, UserDepartAuthExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int updateByExample(UserDepartAuth record, UserDepartAuthExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int updateByPrimaryKeySelective(UserDepartAuth record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table user_depart_auth
     *
     * @ibatorgenerated Mon Mar 17 16:40:14 CST 2014
     */
    int updateByPrimaryKey(UserDepartAuth record);
}