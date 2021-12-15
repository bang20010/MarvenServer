package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.demo.coderfromscratch.http.HttpParser;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest 
	{
		
		private HttpParser httpParser;
		
		@BeforeAll
		public void beforeClass()
			{
				httpParser = new HttpParser();
				
			}
		
		@Test
		void parseHttpRequest() 
		{
				httpParser.parseHttpRequest(
							generateValidTestCase()
						);
		}
	
		private InputStream generateValidTestCase()
			{
				String rawData = "GET / HTTP/1.1\r\n"
						+ "Host: localhost:8083\r\n"
						+ "Connection: keep-alive\r\n"
						+ "Cache-Control: max-age=0\r\n"
						+ "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"\r\n"
						+ "sec-ch-ua-mobile: ?0\r\n"
						+ "sec-ch-ua-platform: \"Windows\"\r\n"
						+ "Upgrade-Insecure-Requests: 1\r\n"
						+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36\r\n"
						+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n"
						+ "Sec-Fetch-Site: none\r\n"
						+ "Sec-Fetch-Mode: navigate\r\n"
						+ "Sec-Fetch-User: ?1\r\n"
						+ "Sec-Fetch-Dest: document\r\n"
						+ "Accept-Encoding: gzip, deflate, br\r\n"
						+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
						+ "\r\n";
				InputStream inputStream = new ByteArrayInputStream(
							rawData.getBytes(
								StandardCharsets.US_ASCII	)
							);
				
				return inputStream;
			}
	}
