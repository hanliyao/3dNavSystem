package com.litop.motorroom.controller.admin;

import java.util.List;

import com.litop.motorroom.auth.AuthService;
import com.litop.motorroom.auth.PasswordUtils;
import com.litop.motorroom.db.bean.Admin;
import com.litop.motorroom.db.bean.User;
import com.litop.motorroom.db.mapper.AdminMapper;
import com.litop.motorroom.db.mapper.UserMapper;
import com.litop.motorroom.litop.LitopMsg;
import com.litop.motorroom.sys.LitopSession;
import com.litop.motorroom.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by litop on 2016/7/18.
 */
@Controller
@RequestMapping(value = "/public")
public class PublicController extends BaseController {

	@Autowired
	AdminMapper adminDao;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {

    return "/public/login";
  }
//退出登录
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		LitopSession litopSession = new LitopSession(request);
		litopSession.removeFromSession();
		return "redirect:/public/login";
	}

  //登录操作
  @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@Param("admin") Admin admin, HttpServletRequest request) throws Exception {
		try {
			Admin adminData=adminDao.getAdminByUserName(admin.getUserName());
			System.out.println(admin.getUserName());
			if(adminData== null){
				//账号不存在
				System.out.println("user not existed");
				return "redirect:/public/login";
			}
			if(PasswordUtils.equalsPassword(admin.getUserName(), admin.getPassword(), adminData.getPassword())){

				//保存用户登录信息
				LitopSession litopSession = new LitopSession(request);
				String ip = request.getRemoteAddr();
				litopSession.setUserId(adminData.getId());
				litopSession.setUserName(adminData.getUserName());
				litopSession.setRoleName(adminData.getRoleName());
				litopSession.setRoleId(adminData.getRoleId());
				litopSession.setIp(ip);


				//登录成功  跳转到系统主页面
				return "redirect:/system/index";
			}
			else{
				//密码不正确
				System.out.println("密码不正确");
				return "redirect:/public/login";

			}


		}
		catch (Exception e){
			return "redirect:/public/login";
		}



	}



	@RequestMapping("/403")
	public String forbidden(){
		return "/public/403";
	}


	public String header(Model model,HttpServletRequest request) {
		LitopSession litopSession = new LitopSession(request);
		model.addAttribute("litopAdminName", litopSession.getUserName());

		return "/public/header";
	}





}
