package com.bentest.gbf.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bentest.gbf.domain.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where userName= #{userName}")
	public User getUserByUserName(@Param("userName") String userName);
}
