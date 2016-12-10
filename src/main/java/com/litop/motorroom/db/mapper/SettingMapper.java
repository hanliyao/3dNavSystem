package com.litop.motorroom.db.mapper;

import com.litop.motorroom.db.bean.Setting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by litop on 2016/7/18.
 */
@Mapper
public interface SettingMapper {

  List<Setting> getAll();

  Setting findSettingByName(Setting name);

  void update(Map paramMap);

}
