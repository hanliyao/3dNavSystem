package com.litop.motorroom.controller.admin;

import com.litop.motorroom.auth.PasswordUtils;
import com.litop.motorroom.db.bean.Admin;
import com.litop.motorroom.db.bean.Role;
import com.litop.motorroom.db.mapper.AdminMapper;
import com.litop.motorroom.db.mapper.RoleMapper;

import com.litop.motorroom.litop.LitopMsg;
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
 * Created by litop on 2016/9/13.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
    //开始注入
    @Autowired AdminMapper adminDao;
    @Autowired RoleMapper roleDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response)  throws Exception {
        try {
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
            String name=request.getParameter("name");
            //创建查询条件
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("name", name);
            paramMap.put("offset", currentResult);
            paramMap.put("pagesize", pageSize);

            //查询条件返回界面的数据
            Map<String, Object> responseMap = new HashMap<String, Object>();
            responseMap.put("name",name);

            List<Admin> list=adminDao.selectAdminListPage(paramMap);

            //总共多少条记录
            Map<String, Object> countMap = new HashMap<String, Object>();
            countMap.put("name", name);
            int totalCount = adminDao.getCounts(countMap);

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
            model.addAttribute("adminList", list);
            model.addAttribute("pageStr", pageStr);
            model.addAttribute("page", page);
            model.addAttribute("response", responseMap);
            return "/admin/index";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @RequestMapping(value = "/preInsert", method = RequestMethod.GET)
    public String preInsert(Model model) {
        List<Role> roleList = roleDao.getAll();
        model.addAttribute("roleList", roleList);
        return "/admin/insert";
    }

    //提交表单内容
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@Param("admin") Admin admin,HttpServletResponse response) throws Exception{
        try {
            Admin adminData=adminDao.getAdminByUserName(admin.getUserName());
            if(adminData==null||adminData.equals(null)){
                admin.setPassword(PasswordUtils.encryptPassword(admin.getUserName(),admin.getPassword()));
                admin.setAddTime(new Date());
                admin.setLastTime(new Date());
                adminDao.insert(admin);
                return "redirect:/admin/index";
            }
            else{              /*response.setContentType("text/html;charset=gb2312");

              PrintWriter out = response.getWriter();
              out.flush();
              out.println("<script>");
              out.print("alert('用户名已存在')");
              out.println("</script>");*/
                return "redirect:/user/index";
            }

        }
        catch (Exception e){
            return "redirect:/admin/index";
        }
    }

    //传参数  跳转到更新页面
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable int id, Model model) {
        Admin admin = adminDao.selectByPrimaryKey(id);
        model.addAttribute("admin", admin);

        List<Role> roleList = roleDao.getAll();
        model.addAttribute("roleList", roleList);

        return "/admin/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Param("admin") Admin admin) throws Exception{
        try {
            adminDao.updateByPrimaryKey(admin);
            return "redirect:/admin/index";
        }
        catch (Exception e){
            return "redirect:/admin/index";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        adminDao.deleteByPrimaryKey(id);
        return "redirect:/admin/index";
    }

    //ajax 初始化密码   初始化密码为  123456
    @RequestMapping(value = "/initPassword/{id}", method = RequestMethod.GET)
    public void initPassword(@PathVariable int id,HttpServletRequest request,HttpServletResponse response) throws Exception{
        try {
            int status=0;
            Admin admin = adminDao.selectByPrimaryKey(id);

            if (admin != null) {
                Map<String, Object> whereMap = new HashMap<String, Object>();
                whereMap.put("id", id);
                whereMap.put("password", PasswordUtils.encryptPassword(admin.getUserName(),"123456"));
                status=adminDao.initPassword(whereMap);
                if(status!=0){
                    render(response, new LitopMsg<String>(true, "初始化密码成功!"));
                }
            }
            else{
                render(response, new LitopMsg<String>(false, "初始化密码失败!"));
            }
        }
        catch (Exception e){
            render(response, new LitopMsg<String>(false, "初始化密码失败!"));
        }
    }


}
