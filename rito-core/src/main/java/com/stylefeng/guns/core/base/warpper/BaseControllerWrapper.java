package com.stylefeng.guns.core.base.warpper;



import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.page.PageResult;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/28 14:08
 */
public abstract class BaseControllerWrapper {
    private Page<Map<String, Object>> page = null;
    private PageResult<Map<String, Object>> pageResult = null;
    private Map<String, Object> single = null;
    private List<Map<String, Object>> multi = null;

    public BaseControllerWrapper(Map<String, Object> single) {
        this.single = single;
    }

    public BaseControllerWrapper(List<Map<String, Object>> multi) {
        this.multi = multi;
    }

    public BaseControllerWrapper(Page<Map<String, Object>> page) {
        if (page != null && page.getRecords() != null) {
            this.page = page;
            this.multi = page.getRecords();
        }

    }

    public BaseControllerWrapper(PageResult<Map<String, Object>> pageResult) {
        if (pageResult != null && pageResult.getRows() != null) {
            this.pageResult = pageResult;
            this.multi = pageResult.getRows();
        }

    }

    public <T> T wrap() {
        if (this.single != null) {
            this.wrapTheMap(this.single);
        }

        if (this.multi != null) {
            Iterator var1 = this.multi.iterator();

            while(var1.hasNext()) {
                Map<String, Object> map = (Map)var1.next();
                this.wrapTheMap(map);
            }
        }

        if (this.page != null) {
            return (T) this.page;
        } else if (this.pageResult != null) {
            return (T) this.pageResult;
        } else if (this.single != null) {
            return (T) this.single;
        } else {
            return this.multi != null ? (T)this.multi : null;
        }
    }

    protected abstract void wrapTheMap(Map<String, Object> map);
}
