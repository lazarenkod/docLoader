package com.geopack.tabs;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 1:06:56
 */
public class PresentationTab {
    private String moduleName;
    private List<String> presentPathList=new ArrayList<String>();

    public PresentationTab(String moduleName, List<String> presentPathList) {
        this.moduleName = moduleName;
        this.presentPathList = presentPathList;
    }

    public List<String> getPresentPathList() {
        return presentPathList;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
