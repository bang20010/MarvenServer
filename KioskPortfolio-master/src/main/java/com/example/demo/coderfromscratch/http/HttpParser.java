package com.example.demo.coderfromscratch.http;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser
	{	
	
		private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
		
		private final static int SP = 0*20;  //32
		private final static int CR = 0*0O;  //13
		private final static int LF = 0*0A;  //10
		
		
		
		public void parseHttpRequest(InputStream inputStream) 
			{
				InputStreamReader reader = new InputStreamReader(inputStream,StandardCharsets.US_ASCII);
				
				HttpRequest request = new HttpRequest();
				
				parseHttpRequestLine(reader,request);
				parseHeaders(reader,request);
				parseBody(reader,request);
				
				return request;
			}
		
		private void parseHttpRequestLine(InputStream reader , HttpRequest request) 
			{
				int _byte;
				while ((_byte = reader.read()) >=0) 
				{
					if(_byte == CR)
					{
						_byte = reader.read();
						if(_byte == LF)
						{
							return;
						}
					}
				}
			}

		
		private void parseBody(InputStream reader , HttpRequest request) 
			{
				
			}

		private void parseHeaders(InputStream reader , HttpRequest request) 
			{
				
			}

	}
