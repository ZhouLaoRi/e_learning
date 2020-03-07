package com.atguigu.springboot.qo;

import java.io.Serializable;

public class BasePageQueryParam implements Serializable {
    private int pageIndex = 1;
    private int pageSize = 10;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
