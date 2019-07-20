package cn.anteater.utils;

import java.io.File;

public interface FileUtils {
    public String fileCheck(File file) throws Exception;
    public Boolean folderExist(String folderPath);
    public Boolean fileCreate(String filePath);
    public Boolean fileDelete(String filePath);
    public Boolean fileCreate(File file);
}
