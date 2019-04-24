package com.example.demo.common;

/**
 * Created by Kkai on 2018/3/25.
 */
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

public class BaseListReq {
    public static final int DEFAULT_PAGE_SIZE = 30;
    public static final int MAX_PAGE_SIZE = 1000;
    @Min(
            value = 0L,
            message = "offset参数不能小于0"
    )
    public long offset = 0L;
    @Range(
            min = 1L,
            max = 1000L,
            message = "limit参数必须在1到1000之间"
    )
    public int limit = 30;

    public BaseListReq() {
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

