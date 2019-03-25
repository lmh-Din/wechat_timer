package com.wechat.timer.util;

import com.wechat.timer.exception.AesException;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @Description
 * @ClassName SHA1
 * @Author minghangliu
 * @Date 2019/3/25 8:35 PM
 * @Version v1.0
 */
@Slf4j
public class SHA1 {

    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param encrypt 密文
     * @return 安全签名
     * @throws AesException
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException
    {
        try {
            String[] array = new String[] { token, timestamp, nonce };
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            log.error("SHA1 Exception", e);
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
