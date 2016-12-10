package com.litop.motorroom.controller.admin;
import com.google.common.io.Files;
import com.litop.motorroom.db.bean.Company;
import com.litop.motorroom.db.mapper.CompanyMapper;
import com.litop.motorroom.page.PageInfo;
import com.litop.motorroom.utils.DeleteUtils;
import com.litop.motorroom.utils.StringUtils;
import org.aspectj.asm.AsmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by litop on 2016/7/18.
 */
@Controller
@RequestMapping(value = "/dataBaseManage")
public class DataBaseManageController extends BaseController{



	@Autowired
    CompanyMapper companyDao;

	@Autowired
	HttpServletRequest request;

	//模型属性基础数据管理
	@RequestMapping(value = "/company", method = RequestMethod.GET)
  public String company(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
		  String name="";
		  name=request.getParameter("keyWord");

		  //创建查询条件
		  Map<String, Object> paramMap = new HashMap<String, Object>();
		  paramMap.put("name", name);
		  paramMap.put("offset", currentResult);
		  paramMap.put("pagesize", pageSize);

		  //查询条件返回界面的数据
		  Map<String, Object> responseMap = new HashMap<String, Object>();
		  responseMap.put("keyWord",name);

		  List<Company> list=companyDao.selectCompanyListPage(paramMap);

		  //总共多少条记录
		  Map<String, Object> countMap = new HashMap<String, Object>();
		  countMap.put("name", name);
		  int totalCount = companyDao.getCounts(countMap);

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
		  model.addAttribute("companyList", list);
		  model.addAttribute("pageStr", pageStr);
		  model.addAttribute("page", page);
		  model.addAttribute("response", responseMap);


      return "/dataBaseManage/company";
    }
    catch (Exception e)
    {
      throw e;
    }

  }


	@RequestMapping(value = "/preInsertCompany", method = RequestMethod.GET)
	public String preInsertCompany(Model model) {
		return "/dataBaseManage/companyInsert";
	}


    @RequestMapping(value="/insertCompany", method=RequestMethod.POST)
    public String insertCompany(@Param("company") Company company, @RequestParam("logoFile") MultipartFile logoFile) throws IOException {
        if (!logoFile.isEmpty()) {
            String name = logoFile.getOriginalFilename();

			// 获取图片的扩展名
			String extensionName = name
					.substring(name.lastIndexOf(".") + 1);
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(System.currentTimeMillis())
					+ "." + extensionName;

			try {
				File filedLogo=new File( request.getServletContext().getRealPath("/") +File.separator+"logo");
				filedLogo.mkdir();

                //创建文件夹
				Calendar date=Calendar.getInstance();
				SimpleDateFormat format=new SimpleDateFormat( "yyyyMMdd");
				String filedName=format.format(date.getTime());
				File file=new File( request.getServletContext().getRealPath("/") +File.separator+"logo"+"/"+filedName);
				file.mkdir();

				String filepath =  request.getServletContext().getRealPath("/") +File.separator+"logo"+"/"+filedName+"/"+ newFileName;
                Files.write(logoFile.getBytes(), new File(filepath));
                //文件路径 保存到数据库表中
                String logoUrl="/"+"logo"+"/"+filedName+"/"+ newFileName;
                try {

                    company.setAddtime(new Date());
                    company.setLogo(logoUrl);
                    companyDao.insert(company);
                    return "redirect:/dataBaseManage/company";
                }
                catch (Exception e){
                    return "redirect:/dataBaseManage/company";
                }
            } catch (Exception e) {

                return "redirect:/dataBaseManage/company";
            }
        } else {
            //上传没有上传LOGO
            try {

                company.setAddtime(new Date());
                company.setLogo("");
                companyDao.insert(company);
                return "redirect:/dataBaseManage/company";
            }
            catch (Exception e){
                return "redirect:/dataBaseManage/company";
            }
        }
    }





	//传参数  跳转到更新页面
	@RequestMapping(value = "/updateCompany/{id}", method = RequestMethod.GET)
	public String updateCompany(@PathVariable int id, Model model) {
		Company company = companyDao.selectByPrimaryKey(id);
		model.addAttribute("company", company);

		return "/dataBaseManage/companyUpdate";
	}


	@RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
	public String updateCompany(@Param("company") Company company, @RequestParam("logoFile") MultipartFile logoFile) throws IOException{

		if (!logoFile.isEmpty()) {
			String name = logoFile.getOriginalFilename();

			// 获取图片的扩展名
			String extensionName = name
					.substring(name.lastIndexOf(".") + 1);
			// 新的图片文件名 = 获取时间戳+"."图片扩展名
			String newFileName = String.valueOf(System.currentTimeMillis())
					+ "." + extensionName;

			try {

				File filedLogo=new File( request.getServletContext().getRealPath("/") +File.separator+"logo");
				filedLogo.mkdir();

				//创建文件夹
				Calendar date=Calendar.getInstance();
				SimpleDateFormat format=new SimpleDateFormat( "yyyyMMdd");
				String filedName=format.format(date.getTime());
				File file=new File( request.getServletContext().getRealPath("/") +File.separator+"logo"+"/"+filedName);
				file.mkdir();

				String filepath =  request.getServletContext().getRealPath("/") +File.separator+"logo"+"/"+filedName+"/"+ newFileName;
				Files.write(logoFile.getBytes(), new File(filepath));
				//文件路径 保存到数据库表中
				String logoUrl="/"+"logo"+"/"+filedName+"/"+ newFileName;
			    try {
			    	//删除当前的文件
					DeleteUtils.deleteFile(request.getServletContext().getRealPath("/") +File.separator+request.getParameter("currentLogo"));
					//重新设置文件路径
					company.setLogo(logoUrl);
					//更新数据
					companyDao.updateByPrimaryKey(company);
					return "redirect:/dataBaseManage/company";
			}
				catch (Exception e){
					return "redirect:/dataBaseManage/company";
				}
			}
			catch (Exception e){
				return "redirect:/dataBaseManage/company";
			}
		} else {
			try {
				company.setLogo(request.getParameter("currentLogo"));
				companyDao.updateByPrimaryKey(company);
				return "redirect:/dataBaseManage/company";
			}
			catch (Exception e){
				return "redirect:/dataBaseManage/company";
			}

		}
	}

	@RequestMapping(value = "/deleteCompany/{id}", method = RequestMethod.GET)
	public String deleteCompany(@PathVariable int id) throws Exception{
		try {
			//先查询记录
			Company company=new Company();
			company=companyDao.selectByPrimaryKey(id);
            String logo=company.getLogo();
			if(logo.length()>0){
				DeleteUtils.deleteFile(request.getServletContext().getRealPath("/") +File.separator+logo);
			}
			companyDao.deleteByPrimaryKey(id);
			return "redirect:/dataBaseManage/company";
		}
		catch (Exception e){
			return "redirect:/dataBaseManage/company";
		}

	}











}
