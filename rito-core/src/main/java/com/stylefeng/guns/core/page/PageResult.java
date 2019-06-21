package com.stylefeng.guns.core.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -4071521319254024213L;
    private Integer page = 1;
    private Integer pageSize = 20;
    private Integer totalPage = 0;
    private Long totalRows = 0L;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Page<T> page) {
        this.setRows(page.getRecords());
        this.setTotalRows(Long.valueOf(page.getTotal()));
        this.setPage((int)page.getCurrent());
        this.setPageSize((int)page.getSize());
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public Long getTotalRows() {
        return this.totalRows;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setPage(final Integer page) {
        this.page = page;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(final Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalRows(final Long totalRows) {
        this.totalRows = totalRows;
    }

    public void setRows(final List<T> rows) {
        this.rows = rows;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$page = this.getPage();
                    Object other$page = other.getPage();
                    if (this$page == null) {
                        if (other$page == null) {
                            break label71;
                        }
                    } else if (this$page.equals(other$page)) {
                        break label71;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                label57: {
                    Object this$totalPage = this.getTotalPage();
                    Object other$totalPage = other.getTotalPage();
                    if (this$totalPage == null) {
                        if (other$totalPage == null) {
                            break label57;
                        }
                    } else if (this$totalPage.equals(other$totalPage)) {
                        break label57;
                    }

                    return false;
                }

                Object this$totalRows = this.getTotalRows();
                Object other$totalRows = other.getTotalRows();
                if (this$totalRows == null) {
                    if (other$totalRows != null) {
                        return false;
                    }
                } else if (!this$totalRows.equals(other$totalRows)) {
                    return false;
                }

                Object this$rows = this.getRows();
                Object other$rows = other.getRows();
                if (this$rows == null) {
                    if (other$rows == null) {
                        return true;
                    }
                } else if (this$rows.equals(other$rows)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $page = this.getPage();
        result = result * 59 + ($page == null ? 43 : $page.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : $totalPage.hashCode());
        Object $totalRows = this.getTotalRows();
        result = result * 59 + ($totalRows == null ? 43 : $totalRows.hashCode());
        Object $rows = this.getRows();
        result = result * 59 + ($rows == null ? 43 : $rows.hashCode());
        return result;
    }

    public String toString() {
        return "PageResult(page=" + this.getPage() + ", pageSize=" + this.getPageSize() + ", totalPage=" + this.getTotalPage() + ", totalRows=" + this.getTotalRows() + ", rows=" + this.getRows() + ")";
    }
}

