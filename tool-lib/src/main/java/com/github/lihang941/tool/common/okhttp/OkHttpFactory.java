package com.github.lihang941.tool.common.okhttp;

import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author : lihang1329@gmail.com
 */
public class OkHttpFactory {

    /**
     * 获取忽略证书的HttpClent
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            };
            TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return new OkHttpClient
                    .Builder()
                    .sslSocketFactory(sslSocketFactory, x509TrustManager).hostnameVerifier((v1, v2) -> true)
                    .connectTimeout(1, TimeUnit.MINUTES).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
