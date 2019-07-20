package cn.anteater.controller.impl;

import cn.anteater.controller.AppController;
import cn.anteater.service.ExcelService;
import cn.anteater.utils.FileUtils;
import lombok.Setter;

import java.io.File;

@Setter
public class AppControllerImpl implements AppController {
    private FileUtils fileUtils;
    private File file;
    private ExcelService excelService;

    @Override
    public void HandleExcel() {
        try {
            if (fileUtils.fileCheck(file).equals("File")){
                excelService.formatExcel(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void HandleExcels() {

    }
}
