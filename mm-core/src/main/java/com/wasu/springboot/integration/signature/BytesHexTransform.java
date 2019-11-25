package com.wasu.springboot.integration.signature;

public class BytesHexTransform {
    private BytesHexTransform() {
    }

    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);

        for(int i = 0; i < bArray.length; ++i) {
            String sTemp = Integer.toHexString(255 & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }

            sb.append(sTemp.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] hexToByte(String hex) {
        byte[] ret = new byte[8];
        byte[] tmp = hex.getBytes();

        for(int i = 0; i < 8; ++i) {
            ret[i] = unitBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }

        return ret;
    }

    public static byte unitBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte)(_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        byte ret = (byte)(_b0 ^ _b1);
        return ret;
    }
}
