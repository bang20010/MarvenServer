package com.example.demo.coderfromscratch.http;

public class HttpParseException extends Exception
	{
		private final HttpStatusCode errorCode;
		
		
		public HttpParseException(HttpStatusCode errorCode) 
			{
				super(errorCode.HESSAGE);
				this.errorCode = errorCode;
			}
		
		public HttpStatusCode getErrorCode() 
			{
				return errorCode;
			}
	}
