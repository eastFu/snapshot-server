package cn.gyyx.snapshot.util;/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/11/30 19:56
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @Author : east.Fu
 * @Description : 拍照工具类
 * @Date : Created in  2017/12/1 10:17
 */
public class CameraUtil {

    public static File takePictureOrPdf(int type,String url,String imgSuffix){
        File file =pdf(Constant.getPdfServerHost()+"/api/render?pdf.width=1250&pdf.height=950&url="+url);
        if(type==0){
            return file;
        }else{
            return pdfToImg(file,imgSuffix);
        }
    }


    public static File pdf(String url){
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        String fileName=System.currentTimeMillis()+".pdf";
        File pdf= new File(Constant.getPdfFilePath()+fileName);
        try {
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.connect();
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(pdf);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        System.out.println("snapshot server success to take a pdf file :"+fileName);
        return pdf;
    }


    public static File pdfToImg(File pdf,String imgSuffix){
        String fileName=System.currentTimeMillis()+"."+imgSuffix;
        File img =new File(Constant.getImgFilePath()+fileName);
        try {
            PDDocument doc = PDDocument.load(pdf);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0; i<pageCount; i++){
                BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image,imgSuffix,img);
            }
            System.out.println("snapshot server success to take a img file :"+fileName);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
