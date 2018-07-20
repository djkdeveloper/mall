package com.djk.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 */
public class MD5Utils {

    private static final MD5Utils INSTANCE = new MD5Utils();

    public static MD5Utils getInstance() {
        return INSTANCE;
    }


    private MD5Utils() {

    }

    /**
     * 创建md5值
     *
     * @param string 待加密的字符串
     * @return 返回加密后的值
     */
    public String createMd5(String string) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成md5值
     *
     * @param is 输入流
     * @return 返回md5加密后的字符串
     * @throws IOException
     */
    public String createMd5ForImageUpload(InputStream is) throws IOException {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] j = new byte[1024];
            while (true) {
                int var14 = is.read(j);
                if (var14 > 0) {
                    md5.update(j, 0, var14);
                }
                if (var14 == -1) {
                    is.skip(0L);
                    break;
                }
            }
        } catch (NoSuchAlgorithmException var12) {
            throw new RuntimeException(var12.getMessage());
        } finally {
            is.close();
        }
        byte[] var15 = md5.digest();
        int var16 = var15.length;
        char[] finalValue = new char[var16 * 2];
        int k = 0;
        for (byte encoded : var15) {
            finalValue[k++] = hexDigits[encoded >> 4 & 15];
            finalValue[k++] = hexDigits[encoded & 15];
        }
        return new String(finalValue);
    }
}