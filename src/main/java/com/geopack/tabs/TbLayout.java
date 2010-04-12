package com.geopack.tabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 0:49:32
 */
public class TbLayout {
    private String name;
    private List<LayoutTab> tabList = new ArrayList<LayoutTab>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LayoutTab> getTabList() {
        return Collections.unmodifiableList(tabList);
    }

    public TbLayout() {
    }

    public void addTab(LayoutTab tab) {
        tabList.add(tab);
    }
}
