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
    private int delay;
    private List<Slide> slides =new ArrayList<Slide>();

    public PresentationTab() {

    }

    public List<Slide> getSlides() {
        return slides;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public void addSlide(Slide slide) {
        slides.add(slide);
    }
}
