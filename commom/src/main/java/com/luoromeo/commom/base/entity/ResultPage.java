package com.luoromeo.commom.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultPage<T> implements Serializable {
    private static final long serialVersionUID = 2886678203167255461L;

    /**
     * 数据列表
     */
    private List<T> rows;

    /**
     * 总行数
     */
    private Long total;

    /**
     * 页码总数
     */
    private int pages;

    /**
     * 分页列表
     *
     * @param rows 数据列表
     * @param total 总行数
     * @param pages 页码总数
     */
    public ResultPage(List<T> rows, Long total, int pages) {
        this.rows = rows;
        this.total = total;
        this.pages = pages;
        if (this.rows == null) {
            this.rows = new ArrayList<T>();
        }
    }

    /**
     * @description 数据列表
     * @return 数据列表
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @description 数据列表
     * @param rows 数据列表
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * @description 总行数
     * @return 总行数
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @description 总行数
     * @param total 总行数
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @description 页码总数
     * @return 页码总数
     */
    public int getPages() {
        return pages;
    }

    /**
     * @description 页码总数
     * @param pages 页码总数
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * @description 空分页列表
     * @return 空分页列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ResultPage empty() {
        return new ResultPage(new ArrayList(), 0L, 0);
    }

}
