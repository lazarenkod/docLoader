package com.geopack.dataloader;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.geopack.models.DictColumnModel;
import com.geopack.models.DictData;
import com.geopack.models.DictRow;
import com.geopack.models.DictTableModel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Lazarenko.Dmitry
 * Date: 06.04.2010
 * Time: 12:33:58
 */
public class DataLoader {

    /**
     * Считывает данные из csv файла и на основле модели столбцов задает им тип
     *
     * @param tableModel
     * @return список строк
     */
    public List<DictRow> readDataFile(DictTableModel tableModel) {
        try {
            String fileName = tableModel.getSrcFile();
            CSVReader reader = new CSVReader(new FileReader(fileName), ';');
            List<DictRow> result = new ArrayList<DictRow>();
            List csvEnties = reader.readAll();
            for (Object entry : csvEnties) {
                DictRow row = new DictRow();
                final String[] stringItems = (String[]) entry;
                for (int i = 0; i < stringItems.length; i++) {
                    String stringValue = stringItems[i];
                    final DictColumnModel columnModel = tableModel.getColumnModels().get(i);
                    Object rawValue = null;
                    if (columnModel.getType().equals(String.class)) {
                        rawValue = stringValue;
                    } else if (columnModel.getType().equals(Integer.class)) {
                        rawValue = Integer.parseInt(stringValue);
                    } else if (columnModel.getType().equals(BigDecimal.class)) {
                        rawValue = new BigDecimal(stringValue);
                    } else if (columnModel.getType().equals(Date.class)) {
                        rawValue = new SimpleDateFormat().parse(stringValue);
                    }

                    DictData data = new DictData(rawValue);
                    row.addData(data);
                }
                result.add(row);
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Записывает данные в csv файл 
     *
     * @param tableModel
     * @return список строк
     */
    public void writeDataFile(DictTableModel tableModel, List<DictRow> rowList) {
        try {
            String fileName = tableModel.getSrcFile();
            CSVWriter writer = new CSVWriter(new FileWriter(fileName), ';');
            List<String[]> result = new ArrayList<String[]>();
            for (DictRow dictRow : rowList) {
                result.add(dictRow.toArray());
            }

            writer.writeAll(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
