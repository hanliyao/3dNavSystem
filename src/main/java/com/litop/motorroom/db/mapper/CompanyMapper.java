package com.litop.motorroom.db.mapper;

import com.litop.motorroom.db.bean.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectCompanyListPage(Map paramMap);

    List<Company> selectCompanyList(Map paramMap);

    int getCounts(Map paramMap);
}