package com.litop.motorroom.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.litop.motorroom.controller.admin.BaseController;
import com.litop.motorroom.db.bean.Company;
import com.litop.motorroom.db.mapper.CompanyMapper;
import com.litop.motorroom.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by litop on 2016/7/18.
 */
@Controller
public class HomeController extends BaseController {

  @Autowired
  CompanyMapper companyDao;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model, HttpServletRequest request, HttpServletResponse response) {


    //查询条件的数据
    String name=request.getParameter("keyWord");
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("name", name);
    List<Company> list=companyDao.selectCompanyList(paramMap);

    model.addAttribute("companyList", list);

    return "index";
  }



  @RequestMapping(value = "/searchAjax", method = RequestMethod.POST)
  public void searchAjax(Model model, HttpServletRequest request, HttpServletResponse response) {


      //查询条件的数据
      String name=request.getParameter("keyWord");
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("name", name);
      List<Company> list=companyDao.selectCompanyList(paramMap);

      model.addAttribute("companyList", list);
      @SuppressWarnings("unchecked")
      String jsonList= JsonUtil.listToJson(list);
     // System.out.println(jsonList.toString());//json格式的数据
      try {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().print(jsonList.toString());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

  }


}
