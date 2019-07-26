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
        Set<String> materialFormatSet = new HashSet<>();
        Set<Integer> materialFormatIndex = new HashSet<>();
        // 确定需要的列
        Set<Integer> indexs = new HashSet<>();
        materialFormatSet.addAll(Arrays.asList(formateFields.split(",")));
        // flag 判断是已经
        Boolean flag = false;
        int rownum=0;
        // 用于判断是否需要记录行
        while (iterator.hasNext()){
            Row row = iterator.next();
            Iterator<Cell> iterator1 = row.cellIterator();
            Row newRow = sheet1.createRow(rownum++);
            // 用于判断新
            int column = 0;
            for (int i=0 ; i<row.getLastCellNum(); i++){
                Cell orgCell = row.getCell(i,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if(orgCell.getCellType().equals(CellType.STRING) &&
                        orgCell.getStringCellValue().equals("Client")){
                    rownum=0;
                    newRow = sheet1.createRow(rownum++);
                    flag = true;
                }
                // 添加要处理得列以及需要格式化的列
                if (flag){
                    if (rownum==1 &&orgCell.getCellType().equals(CellType.STRING)){
                        indexs.add(i);
                        if (materialFormatSet.contains(orgCell.getStringCellValue())){
                            materialFormatIndex.add(i);
                        }
                        excelUtils.copyCell(orgCell,newRow.createCell(column++));
                    }if (rownum>1 && indexs.contains(i)){
                        if (materialFormatIndex.contains(i)){
                            excelUtils.formateNumCell(orgCell,newRow.createCell(column++),"0");
                        }else excelUtils.copyCell(orgCell,newRow.createCell(column++));
                    }
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
