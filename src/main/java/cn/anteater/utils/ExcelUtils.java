package cn.anteater.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface ExcelUtils {
    public Workbook getWorkbok(FileInputStream in, File file) throws IOException;
    public Sheet createSheet(Workbook workbook,String sheetName);
    public void copyCell(Cell orgCell,Cell targetCell);
    public void formateNumCell(Cell cell,Cell targetCell,String typeStr);
}
