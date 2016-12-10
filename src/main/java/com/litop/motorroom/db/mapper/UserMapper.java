package com.litop.motorroom.db.mapper;

import java.util.List;
import java.util.Map;

import com.litop.motorroom.db.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by litop on 2016/7/18.
 */
@Mapper
public interface UserMapper {

  List<User> getAll();

  void insert(User user);

  User findUserById(int id);

  void update(User user);

  void delete(int id);

  User findUserByUserName(String userName);

  List<User> selectUserListPage(Map paramMap);

  int getCounts(Map paramMap);
}
