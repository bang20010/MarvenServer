package com.example.demo.coderfromscratch.http;

public enum HttpStatusCode 
	{
		/* --- CLIENT ERRORS --- */
		CLIENT_ERROR_400_BAO_REQUEST(400,"Bad Request"),
		CLIENT_ERROR_401_BAO_REQUEST(401,"Method Not Allomed"),
		CLIENT_ERROR_414_BAO_REQUEST(414,"URI Too Long"),
		
		/* --- SERVER ERRORS --- */
		
		SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
		SERVER_ERROR_501_NOT_IMPLEMENTED(501,"Not Implemented"),
		SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED(505,"Http Version Not Support")
		;
		
		public final int STATUS_CODE;
		public final String HESSAGE;
		
		private HttpStatusCode(int STATUS_CODE, String HESSAGE) 
		{
			this.STATUS_CODE = STATUS_CODE;
			this.HESSAGE = HESSAGE;
		}
	}
