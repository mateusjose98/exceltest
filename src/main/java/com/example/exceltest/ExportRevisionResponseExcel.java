package com.example.exceltest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExportRevisionResponseExcel extends ExcelExportUtility<Entidade> {


    void fillData(List<Entidade> dataList) {

        CellStyle normalStyle = getNormalStyle();
        int rownum = 1;
        SimpleDateFormat dtFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

        for (Entidade rev : dataList) {

            Row row = sh.createRow(rownum);

            Cell cell_0 = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell_0.setCellStyle(normalStyle);
            cell_0.setCellValue(rev.getId());

            Cell cell_1 = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell_1.setCellStyle(normalStyle);
            cell_1.setCellValue(rev.getNome());

            rownum++;
        }
    }

}