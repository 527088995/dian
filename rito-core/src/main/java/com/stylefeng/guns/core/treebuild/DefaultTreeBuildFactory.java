package com.stylefeng.guns.core.treebuild;

import com.stylefeng.guns.core.node.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/29 17:35
 */
public class DefaultTreeBuildFactory<T extends Tree> extends AbstractTreeBuildFactory<T> {
    private String rootParentId = "-1";

    private void buildChildNodes(List<T> totalNodes, T node, List<T> childNodeLists) {
        if (totalNodes != null && node != null) {
            List<T> nodeSubLists = this.getSubChildsLevelOne(totalNodes, node);
            if (nodeSubLists.size() != 0) {
                Iterator var5 = nodeSubLists.iterator();

                while (var5.hasNext()) {
                    T nodeSubList = (T) var5.next();
                    this.buildChildNodes(totalNodes, nodeSubList, new ArrayList());
                }
            }

            childNodeLists.addAll(nodeSubLists);
            node.setChildrenNodes(childNodeLists);
        }
    }

    private List<T> getSubChildsLevelOne(List<T> list, T node) {
        List<T> nodeList = new ArrayList();
        Iterator var4 = list.iterator();

        while (var4.hasNext()) {
            T nodeItem = (T) var4.next();
            if (nodeItem.getNodeParentId().equals(node.getNodeId())) {
                nodeList.add(nodeItem);
            }
        }

        return nodeList;
    }

    protected List<T> beforeBuild(List<T> nodes) {
        return nodes;
    }

    protected List<T> executeBuilding(List<T> nodes) {
        Iterator var2 = nodes.iterator();

        while (var2.hasNext()) {
            T treeNode = (T) var2.next();
            this.buildChildNodes(nodes, treeNode, new ArrayList());
        }

        return nodes;
    }

    protected List<T> afterBuild(List<T> nodes) {
        ArrayList<T> results = new ArrayList();
        Iterator var3 = nodes.iterator();

        while (var3.hasNext()) {
            T node = (T) var3.next();
            if (node.getNodeParentId().equals(this.rootParentId)) {
                results.add(node);
            }
        }

        return results;
    }

    public DefaultTreeBuildFactory() {
    }

    public String getRootParentId() {
        return this.rootParentId;
    }

    public void setRootParentId(final String rootParentId) {
        this.rootParentId = rootParentId;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DefaultTreeBuildFactory)) {
            return false;
        } else {
            DefaultTreeBuildFactory<?> other = (DefaultTreeBuildFactory) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$rootParentId = this.getRootParentId();
                Object other$rootParentId = other.getRootParentId();
                if (this$rootParentId == null) {
                    if (other$rootParentId != null) {
                        return false;
                    }
                } else if (!this$rootParentId.equals(other$rootParentId)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DefaultTreeBuildFactory;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $rootParentId = this.getRootParentId();
        result = result * 59 + ($rootParentId == null ? 43 : $rootParentId.hashCode());
        return result;
    }

    public String toString() {
        return "DefaultTreeBuildFactory(rootParentId=" + this.getRootParentId() + ")";
    }
}
