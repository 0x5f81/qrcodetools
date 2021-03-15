package cn.x5f81.qrcodetools.gui;

import cn.x5f81.qrcodetools.qrcode.QRCodeUtils;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class SwingArea extends JFrame {
    private static SwingArea instance = null;

    //    private JProgressBar progressBar;
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
        this.setBounds(100, 100, 700, 540);
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
        qRCodeImage.setBounds(220, 190, 200, 200);

        JButton contentToQRCode = new JButton("文本转码");
        contentToQRCode.setUI(new BEButtonUI());
        panel.add(contentToQRCode);
        contentToQRCode.setBounds(20, 410, 100, 30);
        contentToQRCode.addActionListener(e -> {
            String text = qRCodeContent.getText();
            try {
                qRCodeImage.setIcon(new ImageIcon(QRCodeUtils.Encode(text)));
            } catch (WriterException writerException) {
                writerException.printStackTrace();
            }
        });
        JLabel fileLabel = new JLabel("已选择文件：");
        fileLabel.setBounds(20, 450, 100, 30);
        panel.add(fileLabel);
        JLabel filePath = new JLabel("");
        filePath.setBounds(130, 450, 400, 30);
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
        openBtn.setBounds(200, 410, 100, 30);
        openBtn.setUI(new BEButtonUI());
        panel.add(openBtn);

//        AtomicReference<JFileChooser> file = new AtomicReference<>(new JFileChooser());

//        JButton openBtn = new JButton("选择文件");
//        openBtn.addActionListener(e -> file.set(showFileOpenDialog(this, fileFild)));
//        openBtn.setBounds(160,100,100,30);
//        openBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
//        openBtn.setFont(new Font("宋体", Font.BOLD,15));
//        openBtn.setForeground(Color.white);//字体颜色
//        panel.add(openBtn);
//
//        JButton action = new JButton("执行计算");
//        action.setBounds(370,100,100,30);
//        action.addActionListener(e -> action(file.get()));
//        action.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
//        action.setFont(new Font("宋体", Font.BOLD,15));
//        action.setForeground(Color.white);
//        panel.add(action);
//
//
//        JLabel fileFildTitle = new JLabel("已选文件：");
//        fileFildTitle.setBounds(130, 150, 150, 30);
//        panel.add(fileFildTitle);

//        progressBar = new JProgressBar();
//        progressBar.setBounds(80,300,500,30);
//        progressBar.setValue(0);
//        progressBar.setStringPainted(true);

//        panel.add(progressBar);
        this.setVisible(true);

        this.setVisible(true);

    }

    /**
     * 进度条模拟程序
     *
     * @param progressBar
     */
    private void progressBar(JProgressBar progressBar) {
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(i);
            }
        }).start();
    }

//    private void action(JFileChooser fileChooser) {
//        if (null == fileChooser || null == fileChooser.getSelectedFile()) {
//            JOptionPane.showMessageDialog(null, "请先选择要处理的文件！╮(╯▽╰)╭", "警告！",JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//        System.out.println("执行" + fileChooser.getSelectedFile().getAbsolutePath());
//        progressBar(progressBar);
//
//    }

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
