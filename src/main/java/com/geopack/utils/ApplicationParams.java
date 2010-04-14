package com.geopack.utils;

import com.geopack.gui.MainForm;
import com.geopack.maps.ShapeFiles;
import com.geopack.modules.IModule;
import com.geopack.tabs.PresentationTab;
import com.geopack.tabs.TbLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 0:41:05
 */
public class ApplicationParams {

	private ShapeFiles loadedShapes;

    private TbLayout selectedLayout;

    private List<TbLayout> loadedLayouts=new ArrayList<TbLayout>();

	private Map<String,IModule> moduleMap=new HashMap<String, IModule>();

    private List<PresentationTab> presentationTabs=new ArrayList<PresentationTab>();

	private MainForm mainForm;

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

	public void registerModule(IModule module) {
		moduleMap.put(module.getName(),module);
	}

	public IModule getModule(String moduleName) {
		return moduleMap.get(moduleName);
	}

	public Map<String, IModule> getModuleMap() {
		return moduleMap;
	}

	public ShapeFiles getLoadedShapes() {
		return loadedShapes;
	}

	public void setLoadedShapes(ShapeFiles loadedShapes) {
		this.loadedShapes = loadedShapes;
	}

    public List<PresentationTab> getPresentationTabs() {
        return presentationTabs;
    }

    public void setPresentationTabs(List<PresentationTab> presentationTabs) {
        this.presentationTabs = presentationTabs;
    }

	public MainForm getMainForm() {
		return mainForm;
	}

	public void setMainForm(MainForm mainForm) {
		this.mainForm = mainForm;
	}
}

