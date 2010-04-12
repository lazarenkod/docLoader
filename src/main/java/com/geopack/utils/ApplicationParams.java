package com.geopack.utils;

import com.geopack.tabs.TbLayout;

import java.util.List;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 0:41:05
 */
public class ApplicationParams {

    private TbLayout selectedLayout;

    private List<TbLayout> loadedLayouts;

    private static ApplicationParams instance;

    private ApplicationParams() {
    }

    public static ApplicationParams getInstance() {
        if (instance == null)
            instance = new ApplicationParams();
        return instance;
    }

    public TbLayout getSelectedLayout() {
        return selectedLayout;
    }

    public void setSelectedLayout(TbLayout selectedLayout) {
        this.selectedLayout = selectedLayout;
    }

    public List<TbLayout> getLoadedLayouts() {
        return loadedLayouts;
    }

    public void setLoadedLayouts(List<TbLayout> loadedLayouts) {
        this.loadedLayouts = loadedLayouts;
    }
}
