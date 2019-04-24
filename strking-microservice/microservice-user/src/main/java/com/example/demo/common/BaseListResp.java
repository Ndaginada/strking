package com.example.demo.common;

/**
 * Created by Kkai on 2018/3/25.
 */

import java.util.ArrayList;
import java.util.List;

public class BaseListResp<T> {
    private long totalCount = 0L;
    private List<T> items = new ArrayList();

    public BaseListResp() {
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

