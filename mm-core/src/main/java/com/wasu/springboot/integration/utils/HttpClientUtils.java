package com.wasu.springboot.integration.utils;

import com.wasu.springboot.integration.exceptions.BaseCoreException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
    private static final Integer SOCKET_TIMEOUT = 30000;
    private static final Integer CONNECT_TIMEOUT = 30000;

    public static CloseableHttpClient createSSLHttpClient(String pkscPath, String pkscPassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //加载客户端证书
        keyStore.load(new FileInputStream(new File(pkscPath)), pkscPassword.toCharArray());
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).loadKeyMaterial(keyStore, pkscPassword.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    private static CloseableHttpClient createIgnoreSSLHttpClient() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        keyStore.load(null, null);
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }


    private static void close(CloseableHttpClient client, CloseableHttpResponse response) {
        try {
            if (client != null) {
                client.close();
            }
        } catch (IOException e) {
            LOGGER.error("HttpClient close error!", e);
        }

        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            LOGGER.error("HttpResponse close error!", e);
        }
    }

    private static void abort(HttpRequestBase request) {
        try {
            if (request != null && !request.isAborted()) {
                request.abort();
            }
        } catch (Exception e) {
            LOGGER.error("HttpRequestBase abort error ", e);
        }
    }

    private static HttpEntity execute(CloseableHttpClient client, CloseableHttpResponse response, HttpRequestBase base) {
        setTimeout(base);
        try {
            response = client.execute(base);
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                return response.getEntity();
            } else {
//                error
            }
        } catch (Exception e) {
//            error
        }
        return null;
    }

    private static void setTimeout(HttpRequestBase base) {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CONNECT_TIMEOUT).build();
        base.setConfig(config);
    }

    private static CloseableHttpClient getHttpClient(String url) throws Exception {
        if (StringUtils.isEmpty(url)) return null;

        if (url.startsWith("https")) {
            return createIgnoreSSLHttpClient();
        } else {
            return HttpClients.createDefault();
        }
    }

    /**
     * get方式请求 返回xml字符串
     *
     * @param uri
     * @return
     */
    public static String getForXml(String uri) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(uri);
        String result = null;
        try {
            httpClient = getHttpClient(uri);
            HttpEntity entity = execute(httpClient, response, get);
            if (entity != null) {
                try {
                    String gbk = EntityUtils.toString(entity, "gbk");
                    result = UTF2GBK.gbk2utf8(gbk);
                    result = result.replaceFirst("encoding=\"GBK\"", "encoding=\"UTF-8\"").replaceFirst("encoding=\"GB2312\"", "encoding=\"UTF-8\"");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abort(get);
            close(httpClient, response);
        }
        return result;
    }

    /**
     * get方式请求 返回json字符串
     *
     * @param uri
     * @return
     */
    public static String getForJson(String uri) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(uri);
        String result = null;
        try {
            httpClient = getHttpClient(uri);
            HttpEntity entity = execute(httpClient, response, get);
            if (entity != null) {
                try {
                    String gbk = EntityUtils.toString(entity, "gbk");
                    result = UTF2GBK.gbk2utf8(gbk);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abort(get);
            close(httpClient, response);
        }
        return result;

    }

    public static String postForXml(String uri, Map<String, Object> params) {
        String gbk = postForJson(uri, params, "gbk");
        String result = UTF2GBK.gbk2utf8(gbk);
        result = result.replaceFirst("encoding=\"GBK\"", "encoding=\"UTF-8\"").replaceFirst("encoding=\"GB2312\"", "encoding=\"UTF-8\"");
        return result;
    }

    private static String postForJson(String uri, Map<String, Object> params, String charset) {
        return postForJson(uri, params, charset, null);
    }

    private static String postObjectForJson(String uri, Object param, String charset, Map<String, String> headers) {
        Map<String, Object> paramMap = MapUtils.populateMap(param);
        return postForJson(uri, paramMap, charset, headers);
    }

    public static String postForJson(String url, Map<String, Object> params, String charset,
                                     Map<String, String> headers) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> nvp = new ArrayList<>();

        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, Object> current : params.entrySet()) {
                String value = null;

                if (current.getValue() != null && current.getValue() instanceof Date) {
                    value = DateUtils.formatDateTime((Date) current.getValue(), "yyyy-MM-dd HH:ss:SSS");
                } else {
                    value = current.getValue() == null ? "" : current.getValue().toString();
                }

                if (null == current.getValue()) {
                    nvp.add(new BasicNameValuePair(current.getKey(), value));
                } else {
                    nvp.add(new BasicNameValuePair(current.getKey(), value));
                }
            }
        }
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> current : headers.entrySet()) {
                post.addHeader(current.getKey(), current.getValue());
            }
        }
        try {
            httpClient = getHttpClient(url);
            post.setEntity(new UrlEncodedFormEntity(nvp, "UTF-8"));
            HttpEntity entity = execute(httpClient, response, post);
            if (entity != null) {
                try {
                    String result = EntityUtils.toString(entity, charset);
                    return result;
                } catch (Exception e) {
                    LOGGER.error("error");
                }
            }
        } catch (Exception e) {

        } finally {
            abort(post);
            close(httpClient, response);
        }
        return null;
    }

    public static String postForJson(String uri, Map<String, Object> params) {
        return postForJson(uri, params, "UTF-8");
    }

    public static String postForoneFile(String uri, Map<String, Object> params, String fileParamName,
                                        InputStream fileInputStream, String fileName, String charset,
                                        Map<String, String> headers) {
        boolean isFileNull;
        if (null == fileParamName && null == fileInputStream && null == fileName) {
            isFileNull = true;
        } else if (null != fileParamName && null != fileInputStream && null != fileName) {
            isFileNull = false;
        } else {
            throw new BaseCoreException("listFileParamName listFileInputStram listFileName must all null or all not null");
        }

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost post = new HttpPost(uri);

        MultipartEntityBuilder muli = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(Consts.UTF_8);
        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, Object> current : params.entrySet()) {
                if (null == current.getValue()) {
                    muli.addTextBody(current.getKey(), "", ContentType.create("text/plain", Consts.UTF_8));
                } else {
                    muli.addTextBody(current.getKey(), current.getValue().toString(), ContentType.create("text/plain", Consts.UTF_8));
                }
            }
        }
        if (isFileNull) {
            muli.addPart(fileParamName, new InputStreamBody(fileInputStream, fileName));
        }

        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> current : headers.entrySet()) {
                post.addHeader(current.getKey(), current.getValue());
            }
        }

        HttpEntity request = muli.build();
        try {
            httpClient = getHttpClient(uri);
            post.setEntity(request);
            HttpEntity entity = execute(httpClient, response, post);
            if (entity != null) {
                try {
                    String result = EntityUtils.toString(entity, charset);

                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("request url =" + MapUtils.transMapToString(params));
                    }
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abort(post);
            close(httpClient, response);
        }
        return null;
    }
}
