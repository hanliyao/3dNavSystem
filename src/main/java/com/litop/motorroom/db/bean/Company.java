package com.litop.motorroom.db.bean;

import java.util.Date;

public class Company {
    private Integer id;

    private String name;

    private String positionId;

    private String logo;

    private String product;

    private String content;

    private Integer sort;

    private Date addtime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public void companyUpload2Company(CompanyUpload companyUpload)
    {
        this.id = companyUpload.getId();
        this.name = companyUpload.getName();
        this.positionId = companyUpload.getPositionId();
        this.logo = "/upload/images/logo/"+companyUpload.getLogo().getOriginalFilename();//这里应该会出问题
        this.product = companyUpload.getProduct();
        this.content = companyUpload.getContent();
        this.sort  = companyUpload.getSort();
        this.addtime = companyUpload.getAddtime();
        this.status  = companyUpload.getStatus();
    }


}