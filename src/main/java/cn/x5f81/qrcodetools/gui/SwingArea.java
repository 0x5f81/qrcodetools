package cn.x5f81.qrcodetools.gui;

import cn.x5f81.qrcodetools.qrcode.QRCodeUtils;
import com.google.zxing.WriterException;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingArea extends JFrame {
    private static SwingArea instance = null;
    private SwingArea() {
    }

    public static SwingArea getInstance() {
        if (null == instance) {
            synchronized (SwingArea.class) {
                if (null == instance) {
                    instance = new SwingArea();
                }
            }
        }
        return instance;
    }

    public void initUI() throws WriterException {
        this.setTitle("QR二维码小工具(swing)");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 700, 740);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setContentPane(panel);
        //文本域标签
        JLabel qRCodeContentLabel = new JLabel("文本：");
        panel.add(qRCodeContentLabel);
        qRCodeContentLabel.setBounds(20, 20, 200, 30);
        JTextArea qRCodeContent = new JTextArea("{}");
        qRCodeContent.setLineWrap(true);
        qRCodeContent.setUI(new BETextAreaUI());
        qRCodeContent.setBounds(220, 20, 500, 150);
        panel.add(qRCodeContent);
        //二维码图片展示
        JLabel qRCodeImageLabel = new JLabel("码预览：");
        qRCodeImageLabel.setBounds(20, 190, 200, 30);
        panel.add(qRCodeImageLabel);
        JLabel qRCodeImage = new JLabel(new ImageIcon(QRCodeUtils.Encode("{}")));
        panel.add(qRCodeImage);
        qRCodeImage.setBounds(220, 190, 300, 300);

        JButton contentToQRCode = new JButton("文本转码");
        contentToQRCode.setUI(new BEButtonUI());
        panel.add(contentToQRCode);
        contentToQRCode.setBounds(20, 510, 100, 30);
        contentToQRCode.addActionListener(e -> {
            String text = qRCodeContent.getText();
            try {
                qRCodeImage.setIcon(new ImageIcon(QRCodeUtils.Encode(text)));
            } catch (WriterException writerException) {
                writerException.printStackTrace();
            }
        });
        JLabel fileLabel = new JLabel("已选择文件：");
        fileLabel.setBounds(20, 550, 100, 30);
        panel.add(fileLabel);
        JLabel filePath = new JLabel("");
        filePath.setBounds(130, 550, 400, 30);
        panel.add(filePath);
        JButton openBtn = new JButton("选择文件");
        openBtn.addActionListener(e -> {
            File file = showFileOpenDialog(this, filePath);
            BufferedImage bufferedImage = null;
            if(file!=null){
                try {
                    bufferedImage = ImageIO.read(file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(bufferedImage!=null){
                try {
                    qRCodeContent.setText(QRCodeUtils.Decode(bufferedImage));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                qRCodeImage.setIcon(new ImageIcon(bufferedImage));
            }
        });
        openBtn.setBounds(200, 510, 100, 30);
        openBtn.setUI(new BEButtonUI());
        panel.add(openBtn);
        this.setVisible(true);
        this.setVisible(true);
    }

    /*
     * 打开文件
     */
    private File showFileOpenDialog(Component parent, JLabel textField) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);
        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = jFileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = jFileChooser.getSelectedFile();

            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
            // File[] files = fileChooser.getSelectedFiles();
            textField.setText(file.getName());
            return file;
        }
        return null;
    }
}
