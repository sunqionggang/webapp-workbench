package com.seaboxdata.cqny.record.entity;

import com.webapp.support.json.JsonSupport;

import java.util.List;

public class ExcelTemplateSheet {

    private Integer id;

    private Integer template_id;

    private Integer sheet_num;

    private String sheet_name;


    private List<ExcelTemplateCell> excelTemplateCells;

    private List<ExcelTemplateCellMerged> excelTemplateCellMergeds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }

    public Integer getSheet_num() {
        return sheet_num;
    }

    public void setSheet_num(Integer sheet_num) {
        this.sheet_num = sheet_num;
    }

    public String getSheet_name() {
        return sheet_name;
    }

    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

    public String toString(){
        return JsonSupport.objectToJson(this);
    }

    public List<ExcelTemplateCell> getExcelTemplateCells() {
        return excelTemplateCells;
    }

    public void setExcelTemplateCells(List<ExcelTemplateCell> excelTemplateCells) {
        this.excelTemplateCells = excelTemplateCells;
    }

    public List<ExcelTemplateCellMerged> getExcelTemplateCellMergeds() {
        return excelTemplateCellMergeds;
    }

    public void setExcelTemplateCellMergeds(List<ExcelTemplateCellMerged> excelTemplateCellMergeds) {
        this.excelTemplateCellMergeds = excelTemplateCellMergeds;
    }
}
