package cn.x5f81.qrcodetools;

import cn.x5f81.qrcodetools.gui.SwingArea;
import com.google.zxing.WriterException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class QRCodeToolsApplication {
    static ApplicationContext ctx;
    public QRCodeToolsApplication() throws WriterException {
        SwingArea.getInstance().initUI();
    }
    public static void main(String[] args) {
        ctx = new SpringApplicationBuilder(QRCodeToolsApplication.class)
                .headless(false).run(args);
    }
}
