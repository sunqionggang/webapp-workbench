package com.workbench.auth.role.controller;

import com.github.pagehelper.Page;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.role.entity.RoleMenu;
import com.workbench.auth.role.service.RoleMenuManageService;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by SongCQ on 2017/7/6.
 */

@Controller
@RequestMapping("sys/roleMenu")
public class RoleMenuManageController {

    @Autowired
    private RoleMenuManageService roleMenuManageService;

    @RequestMapping("getMenuByRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String getMenuByRole(int user_role_id){
        List<Menu> roleMenuList = roleMenuManageService.getMenuByRole(user_role_id);

        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, roleMenuList);

        return jsonpResult;
    }

    @RequestMapping("getMenuOutRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String getMenuOutRole(int user_role_id){
        List<Menu> roleMenuList = roleMenuManageService.getMenuOutRole(user_role_id);

        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, roleMenuList);

        return jsonpResult;
    }

    @RequestMapping("pagingMenuByRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String pagingMenuByRole(int user_role_id,int currPage,int pageSize){
        Page<Menu> roleMenuPage = roleMenuManageService.pagingMenuByRole(user_role_id,currPage,pageSize);
        PageResult pageResult = PageResult.pageHelperList2PageResult(roleMenuPage);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, pageResult);

        return result;
    }

    @RequestMapping("saveMenuForRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String  saveMenuForRole(RoleMenu roleMenu){
        roleMenuManageService.saveMenuForRole(roleMenu);

        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "删除成功", null, null);

        return jsonpResult;
    }

    @RequestMapping("saveMenusForRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String  saveMenusForRole(Integer user_role_id,String menus){
        roleMenuManageService.saveMenusForRole(user_role_id, (List<Integer>) JsonSupport.jsonToObect(menus,ArrayList.class));

        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "保存成功", null, null);

        return jsonpResult;
    }

    @RequestMapping("delMenuFromRole")
    @ResponseBody
    @JsonpCallback
    @CrossOrigin(allowCredentials="true")
    public String  delMenuFromRole(RoleMenu roleMenu){
        roleMenuManageService.delMenuFromRole(roleMenu);
        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "删除成功", null, null);
        return jsonpResult;
    }

//    @RequestMapping("modifyMenuForRole")
//    @ResponseBody
//    @Transactional
//    public String modifyMenuForRole(int user_role_id,List<Integer> moduleList,HttpServletRequest request){
//        roleMenuManageService.delMenuByRoleId(user_role_id);
//        for(Integer model_id:moduleList){
//            RoleMenu roleMenu = new RoleMenu();
//            roleMenu.setModule_id(model_id);
//            roleMenu.setUser_role_id(user_role_id);
//            roleMenuManageService.saveMenuForRole(roleMenu);
//        }
//        String jsonpResult = JsonpSupport.makeJsonpResponse(
//                JsonResult.RESULT.SUCCESS, "保存成功", null, null, request);
//        return jsonpResult;
//    }

}
