package com.ambow.gateway.filter.decorator;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;

import com.ambow.model.response.ResponseBody;

import reactor.core.publisher.Mono;

public class ResponseBodyServerHttpResponseDecorator extends ServerHttpResponseDecorator {
	
	private ResponseBody responseBody;
	
	public ResponseBodyServerHttpResponseDecorator(ServerHttpResponse delegate) {
		super(delegate);
	}
	
	@Override
	public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
		
		return super.writeWith(body);
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}