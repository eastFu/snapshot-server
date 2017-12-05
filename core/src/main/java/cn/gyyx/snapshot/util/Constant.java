package cn.gyyx.snapshot.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * @Author : eastFu
 * @Description : 常量
 * @Date : Created in  2017/12/1 10:17
 */
public class Constant {

    /**
     * 配置文件的目录
     */
    private static String CONF_PATH = "";

    /**
     * PDF文件存放目录
     */
    private static String PDF_FILE_PATH = "";

    /**
     * img文件存放目录
     */
    private static String IMG_FILE_PATH = "";

    /**
     * conf.properties文件目录
     */
    private static String CONFIG_FILE_PATH = "";


    /**
     * htttp服务端口
     */
    private static int SERVER_PORT = 9527;

    /**
     * url-to-pdf服务的接口host地址
     */
    private static String PDF_SERVER_HOST = "";

    /**
     * 常量初始化
     * @param path
     */
    public static void init(String path) {
        Constant.CONF_PATH=path;
        Constant.CONFIG_FILE_PATH=Constant.getConfPath() + File.separator+"conf.properties";
        Constant.PDF_FILE_PATH=Constant.getConfPath()+File.separator+"cache"+File.separator+"pdf"+File.separator;
        Constant.IMG_FILE_PATH=Constant.getConfPath()+File.separator+"cache"+File.separator+"img"+File.separator;
        try {
            InputStream is = new FileInputStream(Constant.getConfigFilePath());
            Properties properties =new Properties();
            properties.load(is);

            String port =properties.getProperty("http.server.port");
            String host =properties.getProperty("pdf.server.host");

            if(StringUtils.isBlank(port)||StringUtils.isBlank(host)){
                System.out.println("config error and exit...");
                System.exit(1);
            }

            SERVER_PORT=Integer.parseInt(port);
            PDF_SERVER_HOST=host;

            //创建默认目录
            File pdfFile=new File(Constant.PDF_FILE_PATH);
            if (!pdfFile.exists()){
                pdfFile.mkdirs();
            }

            File imgFile=new File(Constant.IMG_FILE_PATH);
            if (!imgFile.exists()){
                imgFile.mkdirs();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("config init exception and exit...");
            System.exit(1);
        }
    }

    public static String getConfPath() {
        return CONF_PATH;
    }

    public static String getConfigFilePath() {
        return CONFIG_FILE_PATH;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static String getPdfServerHost() {
        return PDF_SERVER_HOST;
    }

    public static String getPdfFilePath(){
        return PDF_FILE_PATH;
    }

    public static String getImgFilePath(){
        return IMG_FILE_PATH;
    }
}
