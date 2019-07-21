package cn.anteater.service.impl;

import cn.anteater.service.ExcelService;
import cn.anteater.utils.ExcelUtils;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


@Setter
public class ExcelServiceImpl implements ExcelService {
    private ExcelUtils excelUtils;
    private FileInputStream fileInputStream;

    @Override
    public void formatExcel(File file) throws IOException {
        XSSFWorkbook workbok = (XSSFWorkbook) excelUtils.getWorkbok(fileInputStream, file);
        Sheet sheet =workbok.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Sheet sheet1 = excelUtils.createSheet(workbok,"new");
        int rownum=0;
        // 用于判断是否需要记录行
        while (iterator.hasNext()){
            Row row = iterator.next();
            Iterator<Cell> iterator1 = row.iterator();
            Row newRow = sheet1.createRow(rownum++);
            int column = 0;
            while (iterator1.hasNext()){
                Cell cell = iterator1.next();
                CellType cellType = cell.getCellType();
                switch(cellType){
                    case STRING:
                        if (cell.getStringCellValue().equals("Client")) {
                            rownum=0;
                            newRow = sheet1.createRow(rownum++);
                        }
                        newRow.createCell(column++).setCellValue(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        newRow.createCell(column++).setCellValue(cell.getNumericCellValue());
                        break;
                        default:
                            break;
                }
            }
        }
        FileOutputStream os = new FileOutputStream("C://Users//liang.wh.1//Desktop//Files//LalaFiles//ZTXXPV0104//FPC//xs//test.xlsx");
        workbok.write(os);
        os.close();
    }

    @Override
    public void HandleExcels() {

    }
}
