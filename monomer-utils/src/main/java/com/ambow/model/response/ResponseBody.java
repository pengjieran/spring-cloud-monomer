package com.ambow.model.response;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
* @ClassName: com.mtoliv.model.response.ResponseBody
* @Description: 统一返回的数据结构
* @author Aaron
* @date 2017年11月4日 下午3:38:49 
*
 */
@Data
@Builder
public class ResponseBody implements Serializable {

	private static final long serialVersionUID = -1685486456063561196L;

	private Integer status;
    
	private String code;
	
	private String message;

	private Object data;
	
	public static ResponseBody ok() {
		
		return ResponseBody.succeed();
	}
	
	public static ResponseBody ok(Object object) {
		
		return ResponseBody.succeed(object);
	}

	public static ResponseBody succeed() {

		ResponseBody responseBody = new ResponseBodyBuilder().build();
		responseBody.code = "SUCCEED";
		responseBody.status = 200;
		responseBody.data = new HashMap<String, String>();
		return responseBody;
	}

	public static ResponseBody succeed(Object data) {

		ResponseBody responseBody = new ResponseBodyBuilder().build();
		responseBody.code = "SUCCEED";
		responseBody.status = 200;
		responseBody.data = data;
		return responseBody;
	}

	public static ResponseBody failed() {

		ResponseBody responseBody = new ResponseBodyBuilder().build();
		responseBody.code = "FAILED";
		responseBody.data = new HashMap<>();
		responseBody.status = 201;
		return responseBody;
	}

	public static ResponseBody failed(String code) {

		ResponseBody responseBody = new ResponseBodyBuilder().build();
		responseBody.code = code;
		responseBody.data = new HashMap<>();
		responseBody.status = 201;
		return responseBody;
	}
	
	public static ResponseBody failed(String code, String message) {
	    
	    ResponseBody responseBody = new ResponseBodyBuilder().build();
	    if(StringUtils.isBlank(code)){
			code = "FAILED";
		}
		responseBody.code = code;
	    responseBody.data = new HashMap<>();
	    responseBody.status = 201;
	    responseBody.message = message;
	    return responseBody;
	}

	public static ResponseBody failed(String code, int status) {

		ResponseBody responseBody = new ResponseBodyBuilder().build();
		responseBody.code = code;
		responseBody.data = new HashMap<>();
		responseBody.status = status;
		return responseBody;
	}
	
	public static ResponseBody failed(String code, int status, String message) {
	    
	    ResponseBody responseBody = new ResponseBodyBuilder().build();
	    responseBody.code = code;
	    responseBody.data = new HashMap<>();
	    responseBody.status = status;
	    responseBody.message = message;
	    return responseBody;
	}
}