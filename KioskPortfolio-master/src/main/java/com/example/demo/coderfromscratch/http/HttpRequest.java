package com.example.demo.coderfromscratch.http;

public class HttpRequest extends HttpMessage
	{
		private HttpMethod method;
		private String requsetTarget;
		private String orginalHttpVersion; // literal from the request
		private HttpVersion bestCompatibleHttpVersion;
		
		public String getOrginalhttpVersion() 
			{
				return orginalHttpVersion;
			}

		

		public HttpRequest() {}
	
		public HttpMethod getMethod() 
			{
				return method;
			}
		public String getRequsetTarget() 
			{
				return requsetTarget;
			}
		
		public HttpVersion getBestCompatibleHttpVersion() 
			{
				return bestCompatibleHttpVersion;
			}
		public String getOrginalHttpVersion() 
			{
				return orginalHttpVersion;
			}
		
		void setMetod(String metodName) throws HttpParseException
			{
				for(HttpMethod method : HttpMethod.values())
					{
						if(metodName.equals(method.name()))
						{
							this.method = method;
							return;
						}
					}
				throw new HttpParseException(
						HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
						);

			}

		void setRequsetTarget(String requsetTarget) throws HttpParseException
			{
				if(requsetTarget == null || requsetTarget.length() == 0)
					{
						throw new HttpParseException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
					}
			
				this.requsetTarget = requsetTarget;	
			}
		
		void setHttpVersion(String orginalHttpVersion) throws BadHttpVersionException, HttpParseException 
			{
				this.orginalHttpVersion = orginalHttpVersion;
				this.bestCompatibleHttpVersion = HttpVersion.getBestCompatilbleVersion(orginalHttpVersion);
				
				if (this.bestCompatibleHttpVersion == null) 
					{
						throw new HttpParseException(
								HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED
							);
						
					}
			}
	}
