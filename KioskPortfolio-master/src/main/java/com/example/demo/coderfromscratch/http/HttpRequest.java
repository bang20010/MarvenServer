package com.example.demo.coderfromscratch.http;

public class HttpRequest extends HttpMessage
	{
		private HttpRequest method;
		private String requsetTarget;
		private String httpVersion;
		
		public HttpRequest() 
			{
				
			}
	
		public HttpRequest(HttpRequest method) 
			{
				this.method = method;
			}

	}
