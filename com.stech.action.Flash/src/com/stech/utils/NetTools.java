package com.stech.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetTools {
	private static String LOGTAG="NetTools";
	
	public static  InputStream getContent(String urlPath) throws IOException{
		InputStream is=null;
		URL url=new URL(urlPath);
		URLConnection conn=url.openConnection();
		conn.setConnectTimeout(5000);
		try{
			is=conn.getInputStream();
		}catch (SocketTimeoutException e) {
			Logger.e(LOGTAG, e.getMessage());
		}
		return is;
	}
	
	/**
	 * 好不容易可以用了。。我了个擦
	 * 参数要编码，namevaluepair要编码
	 * 服务端要urldecode解码
	 * @param urlPath
	 * @param dataMap
	 * @return
	 * @throws IOException
	 */
	public static  InputStream postData(String urlPath,Map<String,String> dataMap) throws IOException{
		InputStream is=null;
		
		HttpParams params=new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 7000);// Set the default socket timeout (SO_TIMEOUT) // in milliseconds which is the timeout for waiting for data.  
	    HttpConnectionParams.setSoTimeout(params, 7000);
	    HttpClient client=new DefaultHttpClient(params);
		HttpPost post=new HttpPost(urlPath);
		post.setEntity(new UrlEncodedFormEntity(generateNVP(dataMap)));
		try{
			HttpResponse httpResponse = client.execute(post);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				is=httpResponse.getEntity().getContent();
			}
		}catch (SocketTimeoutException e) {
			Logger.e(LOGTAG, "socket time out~");
		}
		return is;
	}
	
	public static List<NameValuePair> generateNVP(Map<String,String> dataMap){
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(dataMap.size()); 
		for(Entry<String, String> entry:dataMap.entrySet()){
			String key=entry.getKey();
			String value=entry.getValue();
			try {
				nameValuePairs.add(new BasicNameValuePair(key,URLEncoder.encode(value,"utf8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nameValuePairs;
	}
	
	public static String convertStreamToString(InputStream is) throws IOException{
		if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            }finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
	}

}
