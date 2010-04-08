package com.geopack.models.column;

public class CellType {
    private Class type;
    private String srcTableName;
    private String srcTableColumsName;
    private boolean keyField;
    private String srcTableColumnViewName;

    public CellType() {
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getSrcTableName() {
        return srcTableName;
    }

    public void setSrcTableName(String srcTableName) {
        this.srcTableName = srcTableName;
    }

    public String getSrcTableColumsName() {
        return srcTableColumsName;
    }

    public void setSrcTableColumsName(String srcTableColumsName) {
        this.srcTableColumsName = srcTableColumsName;
    }

    public boolean isKeyField() {
		return keyField;
	}

	public void setKeyField(boolean keyField) {
		this.keyField = keyField;
	}

    public String getSrcTableColumnViewName() {
		return srcTableColumnViewName;
	}

	public void setSrcTableColumnViewName(String srcTableColumnViewName) {
		this.srcTableColumnViewName = srcTableColumnViewName;
	}
}