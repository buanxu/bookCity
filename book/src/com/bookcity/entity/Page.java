package com.bookcity.entity;

import java.util.List;

/**
 * Page是分页的模型对象
 * @param <T> 是具体的模块的JavaBean类型
 */
public class Page<T> {
    public static final Integer PAGE_SIZE=4;

    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //每页显示的记录数
    private Integer PageSize;
    //总的记录数
    private Integer PageTotalCounts;
    //当前页显示的javaBean数据
    private List<T> items;
    // 分页条的请求地址
    private String url;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageTotalCounts() {
        return PageTotalCounts;
    }

    public void setPageTotalCounts(Integer pageTotalCounts) {
        PageTotalCounts = pageTotalCounts;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", PageSize=" + PageSize +
                ", PageTotalCounts=" + PageTotalCounts +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
