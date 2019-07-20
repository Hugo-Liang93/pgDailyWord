package cn.anteater.service;

import java.io.File;
import java.io.IOException;

public interface ExcelService {
    public void formatExcel(File file) throws IOException;
    public void HandleExcels();
}
