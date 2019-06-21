package com.stylefeng.guns.core.page;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/28 14:06
 */
public class PageQuery {
    private Integer pageSize;
    private Integer pageNo;
    private String sort;
    private String orderByField;

    public PageQuery() {
    }

    public PageQuery(Integer pageSize, Integer pageNo, String sort, String orderByField) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.sort = sort;
        this.orderByField = orderByField;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public String getSort() {
        return this.sort;
    }

    public String getOrderByField() {
        return this.orderByField;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(final Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }

    public void setOrderByField(final String orderByField) {
        this.orderByField = orderByField;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageQuery)) {
            return false;
        } else {
            PageQuery other = (PageQuery)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$pageSize = this.getPageSize();
                    Object other$pageSize = other.getPageSize();
                    if (this$pageSize == null) {
                        if (other$pageSize == null) {
                            break label59;
                        }
                    } else if (this$pageSize.equals(other$pageSize)) {
                        break label59;
                    }

                    return false;
                }

                Object this$pageNo = this.getPageNo();
                Object other$pageNo = other.getPageNo();
                if (this$pageNo == null) {
                    if (other$pageNo != null) {
                        return false;
                    }
                } else if (!this$pageNo.equals(other$pageNo)) {
                    return false;
                }

                Object this$sort = this.getSort();
                Object other$sort = other.getSort();
                if (this$sort == null) {
                    if (other$sort != null) {
                        return false;
                    }
                } else if (!this$sort.equals(other$sort)) {
                    return false;
                }

                Object this$orderByField = this.getOrderByField();
                Object other$orderByField = other.getOrderByField();
                if (this$orderByField == null) {
                    if (other$orderByField != null) {
                        return false;
                    }
                } else if (!this$orderByField.equals(other$orderByField)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageQuery;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : $sort.hashCode());
        Object $orderByField = this.getOrderByField();
        result = result * 59 + ($orderByField == null ? 43 : $orderByField.hashCode());
        return result;
    }

    public String toString() {
        return "PageQuery(pageSize=" + this.getPageSize() + ", pageNo=" + this.getPageNo() + ", sort=" + this.getSort() + ", orderByField=" + this.getOrderByField() + ")";
    }
}
