package cn.gyyx.snapshot;/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/11/30 19:50
 */

import cn.gyyx.snapshot.util.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Controller;

/**
 * @Author : eastFu
 * @Description : 程序入口
 * @Date : Created in  2017/12/1 10:17
 */
@SpringBootApplication
@Controller
public class Application implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(Constant.getServerPort());
    }

    public static void main(String[] args) {
        //测试模式
        /*if(args==null||args.length==0){
            args=new String[1];
            //项目目录
            args[0]="E:\\snapshot";
        }*/

        if(null!=args&&args.length>=1){
            Constant.init(args[0]);
            SpringApplication.run(Application.class, args);
        }else{
            System.out.println("Params missing , program exit...");
            System.exit(1);
        }
    }
}
