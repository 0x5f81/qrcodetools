package cn.x5f81.qrcodetools.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QRCodeUtils {
    public static Image Encode(String content) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCodeUtilConstant.Q_R_CODE_WIDTH,
                QRCodeUtilConstant.Q_R_CODE_HEIGHT,
                QRCodeUtilConstant.Q_R_CODE_ENCODE_HINTS);// 生成矩阵
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    public static String Decode(BufferedImage qRCodeImage) throws NotFoundException {
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(qRCodeImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(binaryBitmap, QRCodeUtilConstant.Q_R_CODE_DECODE_HINTS);
        return result.getText();
    }
}
