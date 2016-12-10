package com.litop.motorroom.controller.admin;

import com.litop.motorroom.auth.PasswordUtils;
import com.litop.motorroom.db.bean.Admin;
import com.litop.motorroom.db.mapper.AdminMapper;
import com.litop.motorroom.sys.LitopSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by hanliyao on 2016.09.01.
 */
@Controller
@RequestMapping(value = "/adminInfo")
public class AdminInfoController {
    @Autowired  AdminMapper adminDao;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        LitopSession litopSession = new LitopSession(request);
        Admin admin = adminDao.getAdminByUserName(litopSession.getUserName());
        model.addAttribute("admin",admin);
        return "/adminInfo/index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Param("admin") Admin admin) throws Exception{
        try {
            admin.setAddTime(new Date());
            admin.setLastTime(new Date());
            System.out.println(admin);
            adminDao.updateByPrimaryKey(admin);
            return "redirect:/adminInfo/index";
        }
        catch (Exception e){
            //跳转到出错页面
            return "redirect:/adminInfo/index";
        }
    }

    //修改密码
    @RequestMapping(value = "/updatepwd", method = RequestMethod.GET)
    public String updatepwd(@Param("admin") Admin admin) throws Exception{
        try {

            return "/adminInfo/updatepwd";
        }
        catch (Exception e){
            //跳转到出错页面
            return "/adminInfo/index";
        }
    }

    @RequestMapping(value = "/pwdupdate", method = RequestMethod.GET)
    public String pwdupdate(HttpServletRequest request) throws Exception{
        try {
            LitopSession litopSession = new LitopSession(request);
            String oldpwd = request.getParameter("oldpwd");
            Admin adminData = adminDao.getAdminByUserName(litopSession.getUserName());
            if(PasswordUtils.equalsPassword(litopSession.getUserName(), oldpwd, adminData.getPassword()))
            {
                String newpwd = request.getParameter("newpwd");
                adminData.setPassword(newpwd);
                adminDao.updateByPrimaryKey(adminData);//理论上是这个样子的
            }else {
                //输入的密码和原始的密码不符合，请重新输入
            }
            return "/adminInfo/updatepwd";
        }
        catch (Exception e){
            //跳转到出错页面
            return "/adminInfo/index";
        }
    }
}
