package cn.x5f81.qrcodetools.qrcode;

import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

public class QRCodeUtilConstant {
    public final static int Q_R_CODE_WIDTH = 200; // 图像宽度
    public final static int Q_R_CODE_HEIGHT = 200; // 图像高度
    public static Map<EncodeHintType, Object> Q_R_CODE_ENCODE_HINTS = new HashMap<>();
    public static Map<DecodeHintType, Object> Q_R_CODE_DECODE_HINTS = new HashMap<>();

    static {
        Q_R_CODE_ENCODE_HINTS.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        Q_R_CODE_ENCODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        Q_R_CODE_DECODE_HINTS.put(DecodeHintType.CHARACTER_SET, "UTF-8");

    }

}
