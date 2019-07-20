package cn.anteater.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public interface ExcelUtils {
    public Workbook getWorkbok(FileInputStream in, File file) throws IOException;
}
