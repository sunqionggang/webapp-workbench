package com.seaboxdata.cqny.record.controller;

import com.seaboxdata.cqny.record.entity.ReportCustomer;
import com.seaboxdata.cqny.record.entity.ReportCustomerData;
import com.seaboxdata.cqny.record.entity.ReportUnitCustomerContext;
import com.seaboxdata.cqny.record.entity.onedim.SimpleColumDefined;
import com.seaboxdata.cqny.record.service.ReportCustomerService;
import com.seaboxdata.cqny.reportunit.entity.UnitEntity;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.webapp.support.session.SessionSupport;
import com.workbench.auth.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("reportCust")
public class ReportCustomerController {

    @Autowired
    private ReportCustomerService reportCustomerService;

    /**
     * 获取当前用户权限下报送列表
     * @param currPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pagerReport")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult pagerReport(Integer currPage, Integer pageSize){
        User currUser = SessionSupport.checkoutUserFromSession();
        int currUserId = currUser.getUser_id();

        PageResult pageResult = reportCustomerService.pagerReport(currPage, pageSize, currUserId);

        JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS, "获取欧成功", null,pageResult);
        return jsonResult;
    }

    /**
     * 获取当前报表填报的所在步骤
     * @param reportId
     * @return
     */
    @RequestMapping("checkUnitStep")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult checkUnitStep(String reportId){

        ReportCustomer unitStepInfo = reportCustomerService.checkReportCustomer(reportId);


        JsonResult jsonResult = JsonSupport.makeJsonpResult(
                JsonResult.RESULT.SUCCESS, "获取欧成功", null,unitStepInfo);
        return jsonResult;
    }

    @RequestMapping("getUnitContext")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult getUnitContext(String reportId, String unitId, String unitType){
        ReportCustomer reportCustomer = reportCustomerService.checkReportCustomer(reportId);
        Integer currOrder = 0;
        Integer getUnitOrder = 0;
        Integer activeUnitId = reportCustomer.getActive_unit();
        for (UnitEntity unitEntity : reportCustomer.getUnitEntities()) {
            if(unitEntity.getUnit_id().equals(activeUnitId)){
                currOrder = unitEntity.getUnit_order();
            }

            if(unitEntity.getUnit_id().toString().equals(unitId)){
                getUnitOrder = unitEntity.getUnit_order();
            }


        }
        if(getUnitOrder>currOrder){
            JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.FAILD, "该步骤还未填写", null,null);
            return jsonResult;

        }
        ReportUnitCustomerContext unitContext = reportCustomerService.getUnitContext(reportId, unitId, unitType);
        JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS, "获取欧成功", null,unitContext);

        return jsonResult;
    }

    @RequestMapping("saveSimpleUnitContext")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult saveSimpleUnitContext(@RequestBody SaveSimpleUnitContext saveSimpleUnitContext){

        reportCustomerService.updateSimpleUnitContext(saveSimpleUnitContext.getDefinedColums(),saveSimpleUnitContext.getColumDatas());
//        ReportUnitCustomerContext unitContext = reportCustomerService.getUnitContext(reportId, unitId, unitType);
        JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS, "获取欧成功", null,null);

        return jsonResult;
    }

    @RequestMapping("validateSimpleUnitContext")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult validateSimpleUnitContext(@RequestBody SaveSimpleUnitContext saveSimpleUnitContext){

        Map<String, String> validateResult = reportCustomerService.validateSimpleUnitContext(saveSimpleUnitContext.getDefinedColums(),saveSimpleUnitContext.getColumDatas());
//        ReportUnitCustomerContext unitContext = reportCustomerService.getUnitContext(reportId, unitId, unitType);
        JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS, "校验完成", null,validateResult);

        return jsonResult;
    }

    @RequestMapping("updateStep")
    @ResponseBody
    @CrossOrigin(allowCredentials="true")
    public JsonResult updateStep(String reportId){

        reportCustomerService.updateStep(reportId);
//        ReportUnitCustomerContext unitContext = reportCustomerService.getUnitContext(reportId, unitId, unitType);
        JsonResult jsonResult = JsonSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS, "保存成功", null,null);

        return jsonResult;
    }

    class SaveSimpleUnitContext{
        private ArrayList<ReportCustomerData> columDatas;
        private ArrayList<SimpleColumDefined> definedColums;

        public ArrayList<ReportCustomerData> getColumDatas() {
            return columDatas;
        }

        public void setColumDatas(ArrayList<ReportCustomerData> columDatas) {
            this.columDatas = columDatas;
        }

        public ArrayList<SimpleColumDefined> getDefinedColums() {
            return definedColums;
        }

        public void setDefinedColums(ArrayList<SimpleColumDefined> definedColums) {
            this.definedColums = definedColums;
        }
    }
}