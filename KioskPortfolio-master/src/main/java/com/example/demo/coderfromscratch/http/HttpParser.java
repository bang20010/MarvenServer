package com.example.demo.coderfromscratch.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser
	{	
	
		private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
		
		private final static int SP = 0x20;  //32
		private final static int CR = 0x0D;  //13
		private final static int LF = 0x0A;  //10
		
		
		
		public HttpRequest parseHttpRequest(InputStream inputStream)  throws HttpParseException
			{
				InputStreamReader reader = new InputStreamReader(inputStream,StandardCharsets.US_ASCII);
				
				HttpRequest request = new HttpRequest();
				
				try 
				{
					parseRequestLine(reader, request);
					
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				parseHeaders(reader, request);
				parseHeaders(reader,request);
				parseBody(reader,request);
				
				return request;
			}
		
		private void parseRequestLine(InputStreamReader reader , HttpRequest request) throws IOException, HttpParseException 
			{
			
			StringBuilder processingDataBuffer = new StringBuilder();

			boolean methodParsed = false;
			boolean requsetTargetParsed = false;
			
			int _byte;
		
			while ((_byte = reader.read()) >=0) 
				{
					if(_byte == CR)
						{
							_byte = reader.read();
						
							if(_byte == LF)
							{
								LOGGER.debug("Requset Line VERSION to Process : {}", processingDataBuffer.toString());
								
								if(!methodParsed || !requsetTargetParsed)
									{
										throw new HttpParseException(HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
									}
								
								try 
									{
										request.setHttpVersion(processingDataBuffer.toString());
									} 
								catch (BadHttpVersionException e) 
									{
										throw new HttpParseException(HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
									}
								
								return;
							}
						
						else 
							{
								throw new HttpParseException(HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
							}
					}
					
				if(_byte == SP)
					{
						if(!methodParsed)
							{
								LOGGER.debug("Requset Line METHOD to Process : {}", processingDataBuffer.toString());
								request.setMetod(processingDataBuffer.toString());
								methodParsed = true;
							} 
						else if(!requsetTargetParsed)
							{
								LOGGER.debug("Requset Line REQ TARGET to Process : {}", processingDataBuffer.toString());
							
								request.setRequsetTarget(processingDataBuffer.toString());
								requsetTargetParsed = true;
							}
						else 
							{
								throw new HttpParseException(HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
							}
						processingDataBuffer.delete(0, processingDataBuffer.length());
					}
				else 
					{
						processingDataBuffer.append((char)_byte);
						if(!methodParsed)
						{
							if(processingDataBuffer.length() > HttpMethod.MAX_LENGTH )
							{
								throw new HttpParseException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
							}
						}

					}
							
				}
			}


		private void parseHeaders(InputStreamReader reader , HttpRequest request) 
			{
				
			}

		private void parseBody(InputStreamReader reader , HttpRequest request) 
			{
				
			}

	}
