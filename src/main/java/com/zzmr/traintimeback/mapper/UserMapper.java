package com.zzmr.traintimeback.mapper;

// import org.apache.ibatis.annotations.Mapper;

import com.zzmr.traintimeback.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 8:01
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    @Insert("insert into user(account, password, create_time) values(#{account},#{password},#{createTime})")
    void insert(User user);

    @Select("select id,account,password,create_time from user where account = #{account};")
    User getByAccount(String account);
}
