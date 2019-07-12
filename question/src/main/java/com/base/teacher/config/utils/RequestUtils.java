package com.base.teacher.config.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RequestUtils {

	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendPostRequest(String url, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }
	/**
	 * 发送get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendGetRequest(String url, MultiValueMap<String, String> params){
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpMethod method = HttpMethod.GET;
		// 以表单的方式提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		//执行HTTP请求，将返回的结构使用ResultVO类格式化
		ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
		return response.getBody();
	}
}
