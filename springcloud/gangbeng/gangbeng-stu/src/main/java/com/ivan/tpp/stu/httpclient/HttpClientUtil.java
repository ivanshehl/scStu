package com.ivan.tpp.stu.httpclient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static PoolingHttpClientConnectionManager connMgr;  
    private static RequestConfig DEFAULT_REQUEST_CONFIG = null;
	private static final int MAX_READ_TIMEOUT = 30000;		//读取等待时间
	private static final int MAX_CONNECTION_TIMEOUT = 1000;		//链接超时时间
	private static final int MAX_FROM_POOL_TIMEOUT = 1000;		//链接在线程池等待时间
	private static final int MAX_SIZE = 500;				//最大链接数
	private static final int MAX_PER_ROUTE_SIZE = 160;		//访问具体一个url最大链接数  比如访问www.baidu.com 最多只有40个链接可以访问www.baidu.com
	private static HttpClientBuilder HttpClientBuilder = null;
	private static CloseableHttpClient CLIENT = null;
	
	private static final String mx_api_apikey = null;
	private static final String mx_api_token = null;
	
	static{
		
		buildClientPool();
		
	}
	
	public static CloseableHttpClient getCloseableHttpClient(){
		return CLIENT;
	}
	
	public static RequestConfig getDefaultRequestConfig(){
		return DEFAULT_REQUEST_CONFIG;
	}
	
    private static void buildClientPool(){

		
		HttpClientBuilder b = HttpClientBuilder.create();
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			    public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			        return true;
			    }
			}).build();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.setSslcontext(sslContext);

        // don't check Hostnames, either.
        //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

        // here's the special part:
        //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
        //      -- and create a Registry, to register it.
        //
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();
		
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {  
			  
		    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {  
		        // Honor 'keep-alive' header  
		        HeaderElementIterator it = new BasicHeaderElementIterator(  
		                response.headerIterator(HTTP.CONN_KEEP_ALIVE));  
		        while (it.hasNext()) {  
		            HeaderElement he = it.nextElement();  
		            String param = he.getName();  
		            String value = he.getValue();  
		            if (value != null && param.equalsIgnoreCase("timeout")) {  
		                try {  
		                    return Long.parseLong(value) * 1000;  
		                } catch(NumberFormatException ignore) {  
		                }  
		            }  
		        }  
		        HttpHost target = (HttpHost) context.getAttribute(  
		                HttpClientContext.HTTP_TARGET_HOST);
		        return 6 * 1000;  
		    }  
		  
		};
		
		// 设置连接池  
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);  
        // 设置连接池大小  
        connMgr.setMaxTotal(MAX_SIZE);  
        connMgr.setDefaultMaxPerRoute(MAX_PER_ROUTE_SIZE);  
  
        RequestConfig.Builder configBuilder = RequestConfig.custom();  
        // 设置连接超时  
        configBuilder.setConnectTimeout(MAX_CONNECTION_TIMEOUT);  
        // 设置读取超时  
        configBuilder.setSocketTimeout(MAX_READ_TIMEOUT);  
        // 设置从连接池获取连接实例的超时  
        configBuilder.setConnectionRequestTimeout(MAX_FROM_POOL_TIMEOUT);  
        
        configBuilder.setExpectContinueEnabled(true);
        configBuilder.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST));
        configBuilder.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC));
        
        DEFAULT_REQUEST_CONFIG = configBuilder.build();
        HttpClientBuilder = HttpClients.custom()
        .setKeepAliveStrategy(myStrategy)
        .setConnectionManager(connMgr).setConnectionManagerShared(true);
        CLIENT = HttpClientBuilder.build();
        Thread connectionMonitorThread = new Thread(new IdleConnectionMonitorThread(connMgr));
        connectionMonitorThread.start();
		
	
    }
}
