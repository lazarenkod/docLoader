package com.geopack.tabs;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 0:50:41
 */
public class LayoutTab {
    private String name;
    private String moduleName;
    private String hint;

    public LayoutTab() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LayoutTab layoutTab = (LayoutTab) o;

        if (hint != null ? !hint.equals(layoutTab.hint) : layoutTab.hint != null) return false;
        if (!moduleName.equals(layoutTab.moduleName)) return false;
        if (!name.equals(layoutTab.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + moduleName.hashCode();
        result = 31 * result + (hint != null ? hint.hashCode() : 0);
        return result;
    }
}
