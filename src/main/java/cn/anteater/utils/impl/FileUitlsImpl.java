package cn.anteater.utils.impl;

import cn.anteater.utils.FileUtils;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.io.File;


@Setter
public class FileUitlsImpl implements FileUtils {
    private static Logger logger = Logger.getLogger(FileUitlsImpl.class);
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断文件类型 Dir or file
     * 判断文件是否是excel
     *
     * @throws Exception
     */
    @Override
    public String fileCheck(File file) throws Exception {
        if (file.exists()){
            if (file.isFile()){
                if (!(file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX))) {
                    logger.error("File not excel! pls check");
                    throw new Exception("文件不是Excel");
                }
                return "File";
            }else return "Dir";
        }else {
            logger.error("File or Dir not exists! pls check");
            throw new Exception("文件不存在");
        }
    }

    @Override
    public Boolean folderExist(String folderPath) {
        return null;
    }

    @Override
    public Boolean fileCreate(String filePath) {
        return null;
    }

    @Override
    public Boolean fileDelete(String filePath) {
        return null;
    }

    @Override
    public Boolean fileCreate(File file) {
        return null;
    }
}
