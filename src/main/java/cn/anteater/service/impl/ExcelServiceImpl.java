package cn.anteater.service.impl;

import cn.anteater.service.ExcelService;
import cn.anteater.utils.ExcelUtils;
import cn.anteater.utils.FileUtils;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Setter
public class ExcelServiceImpl implements ExcelService {
    private ExcelUtils excelUtils;
    private FileInputStream fileInputStream;

    @Override
    public void formatExcel(File file) throws IOException {
        Workbook workbok = excelUtils.getWorkbok(fileInputStream, file);
        System.out.println("=====");
        Sheet sheet =workbok.getSheetAt(0);
        sheet.shiftRows(4,sheet.getLastRowNum(),-3);
        FileOutputStream os = new FileOutputStream("C://Users//liang.wh.1//Desktop//Files//LalaFiles//ZTXXPV0104//FPC//xs//test.xls");
        workbok.write(os);
        os.close();
    }

    @Override
    public void HandleExcels() {

    }
}
