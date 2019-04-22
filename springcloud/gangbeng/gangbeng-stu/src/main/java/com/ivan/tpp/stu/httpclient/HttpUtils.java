package com.ivan.tpp.stu.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	public static String post(String url, Map<String, String> params){
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;");
		 httpPost.setConfig(HttpClientUtil.getDefaultRequestConfig());
		CloseableHttpResponse response = null;
		try {
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			for(String name: params.keySet()){
				urlParameters.add(new BasicNameValuePair("sn", params.get(name)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			response = HttpClientUtil.getCloseableHttpClient().execute(httpPost);
		    int statusCode = response.getStatusLine().getStatusCode();
		    if(statusCode == 200){
		    	HttpEntity respEntity = response.getEntity();
		    	String str = EntityUtils.toString(respEntity);
		    	return str;
		    }
		}catch(Exception e){
			logger.error("uri="+url+"\t"+JSON. toJSONString(params),e);
		} finally {
			if(response != null){
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					logger.error("url="+url+"\t"+JSON. toJSONString(params),e);
				}
			}
		}
		return null;
	}
}
