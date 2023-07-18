package io.yujie.springboot.example.mapper;

import io.yujie.springboot.example.entity.model.UserModel;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface UserMapper extends BaseMapper<UserModel>, InsertListMapper<UserModel> {

}
