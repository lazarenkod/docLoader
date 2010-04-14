/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geopack.maps;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultRepository;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.DefaultMapLayer;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.*;
import org.geotools.styling.Stroke;
import org.geotools.swing.JMapPane;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Отображение карт
 * @author pavel.shatrov
 */
public class ShapeFiles extends JMapPane
{
    private DefaultRepository repository = new DefaultRepository();
    StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
    FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
    private MapContext context;
    private String title = "Карта";
    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = null;
    FeatureCollection<SimpleFeatureType, SimpleFeature> polyCollection = null;

    public ShapeFiles()
    {
        setBackground(Color.WHITE);
        //enableLayerTable(true);
        //enableStatusBar(true);
        //enableToolBar(true);
        //initComponents();

        // Label layers
        collection = FeatureCollections.newCollection();
        polyCollection = FeatureCollections.newCollection();

        parseCSV();

        workWith();

        //addLabelLayer();
        addPolygonLayer();

        //setSize(850, 600);
        //setLocation(getGraphicsConfiguration().getBounds().width/2 - getBounds().width/2, getGraphicsConfiguration().getBounds().height/2 - getSize().height/2);
        //setVisible(true);
    }

    private void parseCSV()
    {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

        SimpleFeatureType TYPE = null;
        try
        {
            TYPE  = DataUtilities.createType(
                    "Location", // <- the name for our feature type
                    "location:Point,"+ // <- the geometry attribute: Point type
                    "name:String" // <- a String attribute
                    );
        } catch (SchemaException schemaException)
        {
            schemaException.printStackTrace();
        }
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("data/maps/points.csv"));
            try
            {
                String line = reader.readLine();
                //System.out.println("Header: " + line);

                for (line = reader.readLine(); line != null; line = reader.readLine())
                {
                    String tokens[] = line.split("\\,");

                    double longitude = Double.parseDouble(tokens[0]);
                    double latitude = Double.parseDouble(tokens[1]);
                    String name = tokens[2].trim();

                    /* Longitude (= x coord) first ! */
                    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
                    //createPolygon(geometryFactory);

                    featureBuilder.add(point);
                    featureBuilder.add(name);
                    SimpleFeature feature = featureBuilder.buildFeature(null);
                    collection.add(feature);
                }

            } finally
            {
                reader.close();
            }
        } catch (IOException iOException)
        {
        } catch (NumberFormatException numberFormatException)
        {
        }
    }

    private void createPolygon(GeometryFactory geometryFactory)
    {
        Coordinate[] bbox = new Coordinate[5];
        bbox[0] = new Coordinate(679916.6, 5727884.75);
        bbox[1] = new Coordinate(699380.37, 5728327.10);
        bbox[2] = new Coordinate(701444.71, 5716678.33);
        bbox[3] = new Coordinate(684635.09, 5716530.88);
        bbox[4] = new Coordinate(679916.6, 5727884.75);

        LinearRing bboxRing = geometryFactory.createLinearRing(bbox);
        Polygon bboxPolygon = geometryFactory.createPolygon(bboxRing, null);

        SimpleFeatureType st = null;
        try
        {
            st = (SimpleFeatureType) DataUtilities.createType("Location", "Location:Polygon,name:String");
        } catch (SchemaException schemaException)
        {
            schemaException.printStackTrace();
        }
        SimpleFeature featurePoly = SimpleFeatureBuilder.build(st, new Object[] { bboxPolygon, "wvcwecwecwecewc"}, null);
        polyCollection.add(featurePoly);
    }

    private void workWith()
    {
        // Все shp файлы здесь
        File dataDir = new File("data/maps");
        if (dataDir.isDirectory())
        {
            String[] ls = dataDir.list();

            for (int i = 0; i < ls.length; i++)
            {
                String string = ls[i];
                if (string.endsWith(".shp"))
                {
                    File f = new File("data/maps/" + string);

                    try
                    {
                        addShapefile(f.toURL(), string);
                    } catch (IOException iOException)
                    {
                        iOException.printStackTrace();
                    }
                }

            }
        }
    }

    public void addShapefile(URL shapefileURL, String name)
    {
        if (shapefileURL == null)
        {
            throw new IllegalArgumentException("shapefileURL must not be null");
        }
        ShapefileDataStore dstore = null;

        DataStore found = repository.dataStore(shapefileURL.toString());
        if (found != null && found instanceof ShapefileDataStore)
        {
            dstore = (ShapefileDataStore) found;
        } else
        {
            try
            {
                dstore = new ShapefileDataStore(shapefileURL);
            } catch (MalformedURLException urlEx)
            {
                throw new RuntimeException(urlEx);
            }
            try
            {
                repository.register(shapefileURL.toString(), dstore);
            } catch (IOException iOException)
            {
                iOException.printStackTrace();
            }
        }
 
        try
        {
            dstore.getSchema();
        } catch (IOException iOException)
        {
            iOException.printStackTrace();
        }

        String typeName = dstore.getTypeNames()[0];
        Style style = null;

        // Создаю стили        
       
       style = createFromSLD(name);
       if(style == null)
       {
            style = createLineStyle(false);
       }
       
        MapLayer layer = null;
        try
        {
            layer = new DefaultMapLayer(dstore.getFeatureSource(typeName), style);
        } catch (IOException iOException)
        {
            iOException.printStackTrace();
        }
        addLayer(layer);

        // add label layer
        /*MapLayer layer2 = null;
        Style style2 = createPointStyle();// createLineStyle(true);
        writeLabel(style2);

        layer2 = new DefaultMapLayer(collection, style2);

        addLayer(layer2);*/
    }

    private void addLabelLayer()
    {
        MapLayer layer2 = null;
        Style style2 = createPointStyle();
        writeLabel(style2);

        addLayer(new DefaultMapLayer(collection, style2));
    }

    private void addPolygonLayer()
    {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        createPolygon(geometryFactory);
        Style st = createLineStyle(false);
        writeLabel(st);
        addLayer(new DefaultMapLayer(polyCollection, st));
    }

    public void addLayer(MapLayer layer)
    {
        if (context == null)
        {
            CoordinateReferenceSystem crs = layer.getBounds().getCoordinateReferenceSystem();
            if (crs == null)
            {
                crs = DefaultGeographicCRS.WGS84;
            }
            context = new DefaultMapContext(crs);
            context.setTitle(title);
            setMapContext(context);
            setRenderer(new StreamingRenderer());
        }

        context.addLayer(layer);
    }

    private Style createLineStyle(boolean usetransp)
    {
        Stroke stroke = null;
        if (usetransp == true)
        {
            stroke = styleFactory.createStroke(
                    filterFactory.literal(Color.BLUE),
                    filterFactory.literal(1), filterFactory.literal(0.1d));
        }else
        {
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

    private Style writeLabel(Style style)
    {
        StyleBuilder styleBuilder = new StyleBuilder();
        TextSymbolizer textSym = styleBuilder.createStaticTextSymbolizer(Color.BLACK, styleBuilder.createFont("Arial", false, true, 10), "Месторождение Лунное");
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

       private Style createFromSLD(String name) 
       {
           String[] sp = name.split(".shp");
           String sldFile = sp[0];
           File sld = new File("data/maps/"+sldFile + ".sld");
           try
           {
               SLDParser stylereader = new SLDParser(styleFactory, sld.toURI().toURL());
               Style[] style = stylereader.readXML();
               
               return style[0];

           } catch (Exception e)
           {
               e.printStackTrace();
           }
           return null;
    }
}
