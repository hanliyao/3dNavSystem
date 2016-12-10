package com.litop.motorroom.controller.admin;

import com.litop.motorroom.db.bean.Node;
import com.litop.motorroom.db.bean.Role;
import com.litop.motorroom.db.mapper.RoleMapper;
import com.litop.motorroom.page.PageInfo;
import com.litop.motorroom.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by litop on 2016/7/18.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController{
 //开始注入
  @Autowired RoleMapper roleDao;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

      int currentPage = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));
      int pageSize = 10;
      if (currentPage<=0){
          currentPage =1;
      }
      int currentResult = (currentPage-1) * pageSize;

      PageInfo page = new PageInfo();
      page.setShowCount(pageSize);
      page.setCurrentResult(currentResult);
      page.setCurrentPage(currentPage);


      String moduleName=request.getParameter("moduleName");
      //创建查询条件
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("moduleName", moduleName);
      paramMap.put("offset", currentResult);
      paramMap.put("pagesize", pageSize);

      //查询条件返回界面的数据
      Map<String, Object> responseMap = new HashMap<String, Object>();
      responseMap.put("moduleName",moduleName);

      List<Role> list=roleDao.selectRoleListPage(paramMap);

      //总共多少条记录
      Map<String, Object> countMap = new HashMap<String, Object>();
      countMap.put("moduleName", moduleName);
      int totalCount = roleDao.getCounts(countMap);

      //总共多少页
      int lastPage=0;
      if (totalCount % pageSize==0){
          lastPage = totalCount / pageSize;
      }
      else{
          lastPage =1+ totalCount / pageSize;
      }

      page.setTotalPage(lastPage);
      page.setTotalResult(totalCount);


      String pageStr = "";
      pageStr= StringUtils.doPageString(currentPage, lastPage, request.getRequestURI());
      model.addAttribute("roleList", list);
      model.addAttribute("pageStr", pageStr);
      model.addAttribute("page", page);
      model.addAttribute("response", responseMap);
    return "/role/index";
  }

    @RequestMapping(value = "/preInsert", method = RequestMethod.GET)
    public String preInsert() {


        return "/role/insert";
    }

    //提交表单内容
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@Param("role") Role role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        roleDao.insert(role);
        return "redirect:/role/index";
    }

    //传参数  跳转到更新页面
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable int id, Model model) {
        Role role = roleDao.selectByPrimaryKey(id);
        model.addAttribute("role", role);
        return "/role/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Param("role") Role role) {
        role.setUpdateTime(new Date());
        roleDao.updateByPrimaryKey(role);
        return "redirect:/role/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        roleDao.deleteByPrimaryKey(id);
        return "redirect:/role/index";
    }
}
