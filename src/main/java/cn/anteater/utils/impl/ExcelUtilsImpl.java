package cn.anteater.utils.impl;

import cn.anteater.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ExcelUtilsImpl implements ExcelUtils {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @param in
     * @param file
     * @return
     * @throws IOException
     */
    public Workbook getWorkbok(FileInputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public Sheet createSheet(Workbook workbook, String sheetName){
        return workbook.createSheet(sheetName);
    }

    @Override
    public void copyCell(Cell orgCell, Cell targetCell) {
        CellType orgCellType = orgCell.getCellType();
        switch(orgCellType){
            case STRING:
                targetCell.setCellValue(orgCell.getStringCellValue());
                break;
            case NUMERIC:
                targetCell.setCellValue(orgCell.getNumericCellValue());
                break;
            default:
                break;
        }
    }

    @Override
    public void formateNumCell(Cell cell,Cell targetCell, String typeStr) {
        DecimalFormat df = new DecimalFormat(typeStr);
        String formatedStr = df.format(cell.getNumericCellValue());
        targetCell.setCellValue(formatedStr);
    }


}
