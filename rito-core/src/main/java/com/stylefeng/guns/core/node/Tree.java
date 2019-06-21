package com.stylefeng.guns.core.node;

import java.util.List;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/29 15:33
 */
public interface Tree {
    String getNodeId();

    String getNodeParentId();

    void setChildrenNodes(List childrenNodes);
}
