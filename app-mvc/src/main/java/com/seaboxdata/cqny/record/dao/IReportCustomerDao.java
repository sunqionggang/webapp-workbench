package com.seaboxdata.cqny.record.dao;

import com.github.pagehelper.Page;
import com.seaboxdata.cqny.record.entity.ReportCustomer;
import com.seaboxdata.cqny.record.entity.ReportCustomerData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IReportCustomerDao {

    @Select("select rc.* from report_customer rc inner join " +
            "(select origin_id from user_origin_assign where user_id = #{userId}) org on " +
            "rc.report_origin=org.origin_id")
    Page<ReportCustomer> pageReport(@Param("currPage") Integer currPage,
                                    @Param("pageSize") Integer pageSize,
                                    @Param("userId") Integer userId);


    @Select("select * from report_customer_data where report_id=#{reportId} and unit_id=#{unitId}")
    List<ReportCustomerData> getColumDatas(@Param("reportId") String reportId, @Param("unitId") String unitId);
}
