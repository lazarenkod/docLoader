/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geopack.maps;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultRepository;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.DefaultMapLayer;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.TextSymbolizer;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.simple.SimpleFeature;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import java.io.BufferedReader;
import java.io.FileReader;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;

/**
 * Отображение карт
 *
 * @author pavel.shatrov
 */
public class ShapeFrame extends JMapFrame {
    private DefaultRepository repository = new DefaultRepository();
    StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
    FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
    private MapContext context;
    private String title = "Карта";
    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = null;

    public ShapeFrame() {
        enableLayerTable(true);
        enableStatusBar(true);
        enableToolBar(true);
        initComponents();

        // Label layers
        collection = FeatureCollections.newCollection();

        parseCSV();

        workWith();

        setSize(850, 600);
        setLocation(getGraphicsConfiguration().getBounds().width / 2 - getBounds().width / 2, getGraphicsConfiguration().getBounds().height / 2 - getSize().height / 2);
        
    }

    private void parseCSV() {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

        SimpleFeatureType TYPE = null;
        try {
            TYPE = DataUtilities.createType(
                    "Location", // <- the name for our feature type
                    "location:Point," + // <- the geometry attribute: Point type
                            "name:String" // <- a String attribute
            );
        } catch (SchemaException schemaException) {
            schemaException.printStackTrace();
        }
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/points.csv"));
            try {
                String line = reader.readLine();
                //System.out.println("Header: " + line);

                for (line = reader.readLine(); line != null; line = reader.readLine()) {
                    String tokens[] = line.split("\\,");

                    double longitude = Double.parseDouble(tokens[0]);
                    double latitude = Double.parseDouble(tokens[1]);
                    String name = tokens[2].trim();

                    /* Longitude (= x coord) first ! */
                    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

                    featureBuilder.add(point);
                    featureBuilder.add(name);
                    SimpleFeature feature = featureBuilder.buildFeature(null);
                    collection.add(feature);
                }

            } finally {
                reader.close();
            }
        } catch (IOException ignored) {
        } catch (NumberFormatException ignored) {
        }
    }

    private void workWith() {
        // Все shp файлы здесь
        File dataDir = new File("data");
        if (dataDir.isDirectory()) {
            String[] ls = dataDir.list();

            for (String string : ls) {
                if (string.endsWith(".shp")) {
                    File f = new File("data/" + string);

                    try {
                        addShapefile(f.toURL());
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }

            }
        }
    }

    public void addShapefile(URL shapefileURL) {
        if (shapefileURL == null) {
            throw new IllegalArgumentException("shapefileURL must not be null");
        }
        ShapefileDataStore dstore = null;

        DataStore found = repository.dataStore(shapefileURL.toString());
        if (found != null && found instanceof ShapefileDataStore) {
            dstore = (ShapefileDataStore) found;
        } else {
            try {
                dstore = new ShapefileDataStore(shapefileURL);
            } catch (MalformedURLException urlEx) {
                throw new RuntimeException(urlEx);
            }
            try {
                repository.register(shapefileURL.toString(), dstore);
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }

        try {
            dstore.getSchema();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }

        String typeName = dstore.getTypeNames()[0];
        Style style = null;

        // Создаю стили
        style = createLineStyle(false);

        MapLayer layer = null;
        try {
            layer = new DefaultMapLayer(dstore.getFeatureSource(typeName), style);
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        addLayer(layer);

        // add label layer
        Style style2 = createPointStyle();// createLineStyle(true);
        writeLabel(style2);


        MapLayer layer2 = null;


        layer2 = new DefaultMapLayer(collection /*dstore.getFeatureSource(typeName)*/, style2);

        addLayer(layer2);
    }

    public void addLayer(MapLayer layer) {
        if (context == null) {
            CoordinateReferenceSystem crs = layer.getBounds().getCoordinateReferenceSystem();
            if (crs == null) {
                crs = DefaultGeographicCRS.WGS84;
            }
            context = new DefaultMapContext(crs);
            context.setTitle(title);
            setMapContext(context);
            setRenderer(new StreamingRenderer());
        }

        context.addLayer(layer);
    }

    private Style createLineStyle(boolean usetransp) {
        Stroke stroke = null;
        if (usetransp) {
            stroke = styleFactory.createStroke(
                    filterFactory.literal(Color.BLUE),
                    filterFactory.literal(1), filterFactory.literal(0.1d));
        } else {
            stroke = styleFactory.createStroke(
                    filterFactory.literal(Color.BLACK),
                    filterFactory.literal(1));
        }

        LineSymbolizer sym = styleFactory.createLineSymbolizer(stroke, null);


        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]
                {
                        rule
                });

        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }

    private Style writeLabel(Style style) {
        StyleBuilder styleBuilder = new StyleBuilder();
        TextSymbolizer textSym = styleBuilder.createStaticTextSymbolizer(Color.BLUE, styleBuilder.createFont("Arial", false, true, 12), "Документ 1");
        //textSym.setHalo(styleBuilder.createHalo(Color.WHITE, 1));
        textSym.setPlacement(styleBuilder.createPointPlacement(0.5, 1.5, 0, 0, 0));
        //textSym.addToOptions("spaceAround", "-1");
        Rule rule4 = styleBuilder.createRule(textSym);

        Rule[] rules =
                {
                        rule4
                };
        FeatureTypeStyle featureTypeStyle = styleBuilder.createFeatureTypeStyle("Feature", rules);
        style.addFeatureTypeStyle(featureTypeStyle);

        return style;
    }

    private Style createPointStyle() {
        Graphic gr = styleFactory.createDefaultGraphic();

        Mark mark = styleFactory.getCircleMark();

        mark.setStroke(styleFactory.createStroke(
                filterFactory.literal(Color.BLUE), filterFactory.literal(1)));

        mark.setFill(styleFactory.createFill(filterFactory.literal(Color.ORANGE)));

        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);
        gr.setSize(filterFactory.literal(5));

        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
}
