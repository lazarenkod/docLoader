package com.geopack.dataloader;

import com.geopack.tabs.Layouts;
import com.geopack.utils.ApplicationParams;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * User: Administrator
 * Date: 11.04.2010
 * Time: 23:49:42
 */
public class LayoutTabLoader {
    public static final String SCHEME_PATH = "data";

    public LayoutTabLoader() {
    }

    private Layouts loadLayout(File file) throws IOException, SAXException {
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addObjectCreate("Layouts", "com.geopack.tabs.Layouts");
        digester.addObjectCreate("*/Layout", "com.geopack.tabs.TbLayout");
        digester.addSetProperties("*/Layout", "name", "name");
        digester.addObjectCreate("*/Layout/Tab", "com.geopack.tabs.LayoutTab");
        digester.addSetProperties("*/Layout/Tab", "name", "name");
        digester.addSetProperties("*/Layout/Tab", "module", "moduleName");
        digester.addSetProperties("*/Layout/Tab", "hint", "hint");
        digester.addSetNext("*/Layout/Tab", "addTab");
        digester.addSetNext("*/Layout", "addLayout");
        return (Layouts) digester.parse(file);
    }

    public void loadLayouts() {
        File schemeDirectory = new File(SCHEME_PATH);
        if (schemeDirectory.isDirectory()) {
            final File[] schemeFiles = schemeDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".lut.xml");
                }
            });
            for (File file : schemeFiles) {
                try {
                    ApplicationParams.getInstance().setLoadedLayouts(loadLayout(file).getLayouts());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
