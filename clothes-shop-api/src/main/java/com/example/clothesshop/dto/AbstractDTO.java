package com.example.clothesshop.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class AbstractDTO<T> {
    private Long id;

    private String createdBy;

    private String modifiedBy;

    private Date createdDate;

    private Date modifiedDate;

    private MultipartFile file;

//    private List<T> listResult = new ArrayList<>();
//
//    private Integer page;
//
//    private Integer totalPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

//    public List<T> getListResult() {
//        return listResult;
//    }
//
//    public void setListResult(List<T> listResult) {
//        this.listResult = listResult;
//    }
//
//    public Integer getPage() {
//        return page;
//    }
//
//    public void setPage(Integer page) {
//        this.page = page;
//    }
//
//    public Integer getTotalPage() {
//        return totalPage;
//    }
//
//    public void setTotalPage(Integer totalPage) {
//        this.totalPage = totalPage;
//    }
}
