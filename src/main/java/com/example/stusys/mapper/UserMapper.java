package com.example.stusys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.stusys.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取密钥
     */
    @Select("select secret_key from secret_key where id = #{id}")
    String getSecretKey(int id);

    @Update("update user set credit = #{credit} where user_id = #{studentId}")
    boolean updateCredit(float credit, String studentId);
}
