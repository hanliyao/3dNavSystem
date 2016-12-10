package com.litop.motorroom.controller.admin;

import com.google.common.io.Files;
import com.litop.motorroom.db.bean.ModuleTree;
import com.litop.motorroom.db.bean.Node;
import com.litop.motorroom.db.bean.Setting;
import com.litop.motorroom.db.mapper.ACLMapper;
import com.litop.motorroom.db.mapper.NodeMapper;
import com.litop.motorroom.db.mapper.SettingMapper;
import com.litop.motorroom.db.mapper.URMapper;
import com.litop.motorroom.litop.LitopMsg;
import com.litop.motorroom.sys.LitopSession;
import com.litop.motorroom.utils.DeleteUtils;
import com.litop.motorroom.utils.List2String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by litop on 2016/7/18.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController {

	//开始注入
	@Autowired
	NodeMapper nodeDao;
	@Autowired
	ACLMapper aclDao;
	@Autowired
	URMapper urDao;
	@Autowired
	SettingMapper settingDao;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
  public String index(Model model,HttpServletRequest request) throws Exception{
	  try {

          LitopSession litopSession = new LitopSession(request);

		  model.addAttribute("litopAdminName", litopSession.getUserName());
          System.out.println("````````````````````````````````````````````");
		  int userId =  litopSession.getUserId();
		 // int roleId = urDao.getRolebyUserID(userId);//获取到角色的ID
		  int roleId = litopSession.getRoleId();//获取到角色的ID
		  List<String> ACList = List2String.ACL2String(aclDao.getACLLbyGroupID(roleId));
			//获取根节点列表
		  List<Node> nodesList= nodeDao.selectNodesByPid(0);
		  Iterator<Node> piter = nodesList.iterator();
		  while (piter.hasNext())
		  {
			  Node pnode = piter.next();
			  if(!ACList.contains(pnode.getId().toString()))
			  {
				  piter.remove();
			  }else
			  {
				  List<Node> childList = nodeDao.selectNodesByPid(pnode.getId());
				  Iterator<Node> iter = childList.iterator();
				  while (iter.hasNext())
				  {
					  Node node = iter.next();
					  if(!ACList.contains(node.getId().toString()))
					  {
						  iter.remove();
					  }
				  }
				  if(childList.size()>0)
				  {
					  pnode.setChildNode(childList);
				  }else
				  {
					  piter.remove();
				  }
			  }
		  }
		  model.addAttribute("nodesList",nodesList);
		  return "/system/index";
		}
		catch (Exception e)
		{
		  throw e;
		}

  }



	//首页内容信息
	@RequestMapping(value = "/indexInfo", method = RequestMethod.GET)
	public String indexInfo(Model model) {

		return "/system/indexInfo";
	}




	//权限管理
	@RequestMapping(value = "/sysAuth", method = RequestMethod.GET)
	public String sysAuth(Model model) {
		List<Node> rootNode = nodeDao.selectNodesByPid(0);
		List<String> ACList = List2String.ACL2String(aclDao.getACLLbyGroupID(1));
		ModuleTree root = new ModuleTree();
		root.setModuleID("0");
		root.setModuleName("root");
		for (Node node : rootNode) {
			ModuleTree TreeNode = new ModuleTree();
			TreeNode.setModuleID(node.getId().toString());
			TreeNode.setModuleName(node.getModuleName());
			if(ACList.contains(node.getId().toString()))
			{
				//标注已经访问控制列表中的节点
				TreeNode.setStatus(1);
			}
			List<Node> items = nodeDao.selectNodesByPid(node.getId());
			for(Node item : items)
			{
				ModuleTree TreeItem = new ModuleTree();
				TreeItem.setModuleName(item.getModuleName());
				TreeItem.setModuleID(item.getId().toString());
				if(ACList.contains(item.getId().toString()))
				{
					//标注已经访问控制列表中的节点
					TreeItem.setStatus(1);
				}
				List<Node> actions = nodeDao.selectNodesByPid(item.getId());
				for(Node action : actions)
				{
					ModuleTree TreeAction = new ModuleTree();
					TreeAction.setModuleID(action.getId().toString());
					TreeAction.setModuleName(action.getModuleName());
					if(ACList.contains(action.getId().toString()))
					{
						//标注已经访问控制列表中的节点
						TreeAction.setStatus(1);
					}
					TreeItem.addChild(TreeAction);
				}
				TreeNode.addChild(TreeItem);
			}
			root.addChild(TreeNode);
		}
		System.out.println(root);
		model.addAttribute("root",root);//得到了权限的树形结构
		return "/system/sysAuth";
	}

    //系统设置
	@RequestMapping(value = "/sysSet", method = RequestMethod.GET)
	public String sysSet(Model model) {
		List<Setting> settingList = settingDao.getAll();
        Map<String, Object> settingData = new HashMap<String, Object>();
        for(Setting item : settingList)
        {
            settingData.put(item.getName(),item.getData());
        }
        model.addAttribute("settingData",settingData);
		return "/system/sysSet";
	}





	@RequestMapping(value = "/saveSysSet", method = RequestMethod.POST)
	public String saveSysSet(Model model, HttpServletRequest request,@RequestParam("logoFile") MultipartFile logoFile) throws Exception{
		try {
			Map<String, String[]> params = request.getParameterMap();
			for (String key : params.keySet()) {
                Map<String, Object> updateMap = new HashMap<String, Object>();
                updateMap.put("name", key);
				String[] values = params.get(key);
                String value="";
				for (int i = 0; i < values.length; i++) {
                    value+=values[i];
				}
                updateMap.put("data", value);
                //更新数据
                settingDao.update(updateMap);
		    }

			if (!logoFile.isEmpty()) {
				String name = logoFile.getOriginalFilename();

				File filedLogo=new File( request.getServletContext().getRealPath("/") +File.separator+"logo");
				filedLogo.mkdir();

				// 获取图片的扩展名
				String extensionName = name
						.substring(name.lastIndexOf(".") + 1);
				// 新的图片文件名 = 获取时间戳+"."图片扩展名
				String newFileName = String.valueOf(System.currentTimeMillis())
						+ "." + extensionName;
				try {
					String filepath =  request.getServletContext().getRealPath("/") +File.separator+"logo"+"/"+ newFileName;
					Files.write(logoFile.getBytes(), new File(filepath));
					//文件路径 保存到数据库表中
					String logoUrl="/"+"logo"+"/"+ newFileName;
					try {
						//删除当前的文件
						DeleteUtils.deleteFile(request.getServletContext().getRealPath("/") +File.separator+request.getParameter("currentLogo"));
						//重新设置文件路径
						Map<String, Object> updateMap = new HashMap<String, Object>();
						updateMap.put("name", "logo");
						updateMap.put("data", logoUrl);
						//更新数据
						settingDao.update(updateMap);

					}
					catch (Exception e){
						return "redirect:/system/sysSet";
					}
				}
				catch (Exception e){
					return "redirect:/system/sysSet";
				}
			}
            return "redirect:/system/sysSet";
        }
		catch (Exception e){
			return "redirect:/system/sysSet";
		}
	}





	@RequestMapping(value = "/authChange", method = RequestMethod.POST)
	public void authChange(Model model, HttpServletRequest request,HttpServletResponse response) {
		List<String> ACList = List2String.ACL2String(aclDao.getACLLbyGroupID(1));
		String message = request.getParameter("message");
		Map<String,String> map = List2String.String2Map(message);
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			if(entry.getValue().equals("1") && !ACList.contains(entry.getKey()))
			{
				//添加访问控制列表
				aclDao.insertModulebyModuleID(Integer.parseInt(entry.getKey()));
			}
			if(entry.getValue().equals("0") && ACList.contains(entry.getKey()))
			{
				//删除访问控制列
				aclDao.deleteModulebyModuleID(Integer.parseInt(entry.getKey()));
			}
		}


	}

}
