package org.hzt.springscaffold.user.dal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select count(*) from user_extend_img")
    int count();

}
