package com.stylefeng.guns.core.treebuild;

import java.util.List;

/**
 * @Description:
 * @Auther: wj
 * @Date: 2019/5/29 17:36
 */
public abstract class AbstractTreeBuildFactory<T> {
    public AbstractTreeBuildFactory() {
    }

    public List<T> doTreeBuild(List<T> nodes) {
        List<T> readyToBuild = this.beforeBuild(nodes);
        List<T> builded = this.executeBuilding(readyToBuild);
        return this.afterBuild(builded);
    }

    protected abstract List<T> beforeBuild(List<T> nodes);

    protected abstract List<T> executeBuilding(List<T> nodes);

    protected abstract List<T> afterBuild(List<T> nodes);
}

