<?xml version="1.0" encoding="ISO-8859-1" standalone="yes"?>
<sld:StyledLayerDescriptor version="1.0.0" xmlns:sld="http://www.opengis.net/sld" xmlns:ogc="http://www.opengis.net/ogc" xmlns:xlink="http://www.w3.org/1999/xlink">
  <sld:NamedLayer>
    <sld:Name>dnp</sld:Name>
    <sld:UserStyle>
      <sld:Name>Style1</sld:Name>
      <sld:FeatureTypeStyle>
        <sld:FeatureTypeName>dnp</sld:FeatureTypeName>
        <sld:Rule>
          <sld:Name>1</sld:Name>
          <sld:Title>1</sld:Title>
          <ogc:Filter>
            <ogc:PropertyIsEqualTo>
              <ogc:PropertyName>DNP_CODE</ogc:PropertyName>
              <ogc:Literal>1</ogc:Literal>
            </ogc:PropertyIsEqualTo>
          </ogc:Filter>
          <sld:PointSymbolizer>
            <sld:Graphic>
              <sld:Mark>
                <sld:WellKnownName>circle</sld:WellKnownName>
                <sld:Fill>
                  <sld:CssParameter name="fill">#FF0000</sld:CssParameter>
                  <sld:CssParameter name="fill-opacity">1.0</sld:CssParameter>
                </sld:Fill>
              </sld:Mark>
              <sld:Size>8</sld:Size>
              <sld:Rotation>180</sld:Rotation>
            </sld:Graphic>
          </sld:PointSymbolizer>
        </sld:Rule>
        <sld:Rule>
          <sld:Name>301810</sld:Name>
          <sld:Title>301810</sld:Title>
          <ogc:Filter>
            <ogc:PropertyIsEqualTo>
              <ogc:PropertyName>DNP_CODE</ogc:PropertyName>
              <ogc:Literal>301810</ogc:Literal>
            </ogc:PropertyIsEqualTo>
          </ogc:Filter>
          <sld:PointSymbolizer>
            <sld:Graphic>
              <sld:Mark>
                <sld:WellKnownName>circle</sld:WellKnownName>
                <sld:Fill>
                  <sld:CssParameter name="fill">#0000FF</sld:CssParameter>
                  <sld:CssParameter name="fill-opacity">1.0</sld:CssParameter>
                </sld:Fill>
              </sld:Mark>
              <sld:Size>8</sld:Size>
              <sld:Rotation>180</sld:Rotation>
            </sld:Graphic>
          </sld:PointSymbolizer>
        </sld:Rule>
      </sld:FeatureTypeStyle>
    </sld:UserStyle>
  </sld:NamedLayer>
</sld:StyledLayerDescriptor>
