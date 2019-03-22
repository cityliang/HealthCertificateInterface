package com.huntto.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/** 
     * 读取文件 
     *  
     * @param filePath 
     *            文件完整名 
     * @return 文件内容 
     * @throws IOException 
     */  
    public static List readFile(String filePath) {  
  
        List list = new ArrayList();  
  
        FileInputStream fs = null;  
        InputStreamReader isr = null;  
        BufferedReader br = null;  
  
        try {  
            fs = new FileInputStream(filePath);  
  
            isr = new InputStreamReader(fs);  
  
            br = new BufferedReader(isr);  
  
            String data = "";  
            while ((data = br.readLine()) != null) {  
                list.add(data.trim());  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (br != null)  
                    br.close();  
                if (isr != null)  
                    isr.close();  
  
                if (fs != null)  
                    fs.close();  
  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        return list;  
    }  
}
