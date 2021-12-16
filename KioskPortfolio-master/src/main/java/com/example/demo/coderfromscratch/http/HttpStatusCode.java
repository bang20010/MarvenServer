package com.example.demo.coderfromscratch.http;

public enum HttpStatusCode 
	{
		/* --- CLIENT ERRORS --- */
		CLIENT_ERROR_469_BAO_REQUEST(400,"Bad Request"),
		CLIENT_ERROR_401_BAO_REQUEST(400,"Method Not Allomed"),
		CLIENT_ERROR_414_BAO_REQUEST(400,"URI Too Long"),
		
		/* --- SERVER ERRORS --- */
		
		SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
		SERVER_ERROR_501_NOT_IMPLEMENTED(501,"Not Implemented")
	
		
		;
		
		public final int STATUS_CODE;
		public final String HESSAGE;
		
		private HttpStatusCode(int STATUS_CODE, String HESSAGE) 
		{
			this.STATUS_CODE = STATUS_CODE;
			this.HESSAGE = HESSAGE;
		}
	}
