package cn.anteater;

import cn.anteater.controller.AppController;
import cn.anteater.service.ExcelService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        AppController appController = (AppController) context.getBean("appController");
        appController.HandleExcel();
    }
}
