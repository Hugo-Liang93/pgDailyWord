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
import java.util.*;


@Setter
public class ExcelServiceImpl implements ExcelService {
    private ExcelUtils excelUtils;
    private FileInputStream fileInputStream;
    private String formateFields;

    @Override
    public void formatExcel(File file) throws IOException {
        XSSFWorkbook workbok = (XSSFWorkbook) excelUtils.getWorkbok(fileInputStream, file);
        Sheet sheet =workbok.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Sheet sheet1 = excelUtils.createSheet(workbok,"new");
        Set<String> materialSet = new HashSet<>();
        Set<Integer> materialIndex = new HashSet<>();
        materialSet.addAll(Arrays.asList(formateFields.split(",")));
        // flag 判断是已经
        Boolean flag = false;
        int rownum=0;
        // 用于判断是否需要记录行
        while (iterator.hasNext()){
            Row row = iterator.next();
            Iterator<Cell> iterator1 = row.iterator();

            Row newRow = sheet1.createRow(rownum++);
            int column = 0;
            while (iterator1.hasNext()){
                Cell orgCell = iterator1.next();
                // 确定数据开始行
                if(orgCell.getCellType().equals(CellType.STRING) &&
                        orgCell.getStringCellValue().equals("Client")){
                    rownum=0;
                    newRow = sheet1.createRow(rownum++);
                    flag = true;
                }
                // 确定需要格式化数据行
                if(flag && rownum==0 && materialSet.contains(orgCell.getStringCellValue())){
                    materialIndex.add(column);
                }
                Cell targetCell = newRow.createCell(column++);
                // 当FLAG等于true means t
                if(flag && rownum>0) excelUtils.formateNumCell(orgCell,targetCell,"0");
                else excelUtils.copyCell(orgCell,targetCell);
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
