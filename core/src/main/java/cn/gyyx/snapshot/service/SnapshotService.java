package cn.gyyx.snapshot.service;/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/12/1 10:37
 */

import cn.gyyx.snapshot.util.CameraUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author eastFu
 * @Description : 快照服务接口
 * @create 2017-12-01 10:37
 **/
@Controller
public class SnapshotService {

    @RequestMapping("/kacha")
    public void test(HttpServletResponse response,String url) throws Exception{
        try {
            File f = CameraUtil.takePictureOrPdf(2,url,  "png");
            if(f!=null){
                response.setContentType("image/jpeg");
                response.setDateHeader("expries", -1);
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Pragma", "no-cache");
                OutputStream out =response.getOutputStream();

                InputStream in =new BufferedInputStream(new FileInputStream(f));
                byte[] b = new byte[1024];

                while(in.read(b) != -1) {
                    out.write(b);
                }
                in.close();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
