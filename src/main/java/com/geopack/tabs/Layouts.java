package com.geopack.tabs;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 12.04.2010
 * Time: 0:55:24
 */
public class Layouts {
    private List<TbLayout> layouts=new ArrayList<TbLayout>();
    public Layouts() {
    }

    public void addLayout(TbLayout layout) {
        layouts.add(layout);
    }

    public List<TbLayout> getLayouts() {
        return layouts;
    }
}
