package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.demo.coderfromscratch.http.HttpMethod;
import com.example.demo.coderfromscratch.http.HttpParseException;
import com.example.demo.coderfromscratch.http.HttpParser;
import com.example.demo.coderfromscratch.http.HttpRequest;
import com.example.demo.coderfromscratch.http.HttpStatusCode;
import com.example.demo.coderfromscratch.http.HttpVersion;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest 
	{
		
		private HttpParser httpParser;
		
		@BeforeAll
		public void beforeClass()
			{
				httpParser = new HttpParser();
				
			}
		
		
		// @TEST 메소드
		@Test
		void parseHttpRequest() 
		{	
			HttpRequest request = null;
			try 
				{
					request = httpParser.parseHttpRequest(generateValidGetTestCase());
				} 
			catch (HttpParseException e) 
				{
					fail(e);
				}
			
			assertNotNull(request);
			assertEquals(request.getMethod(), HttpMethod.GET);
			assertEquals(request.getRequsetTarget(), "/");
			assertEquals(request.getOrginalhttpVersion(), "HTTP/1.1");
			assertEquals(request.getBestCompatibleHttpVersion(), HttpVersion.HTTP_1_1);
		}
		
		@Test
		void parseHttpRequestBadMethod1() 
		{	
			try {
				HttpRequest request = httpParser.parseHttpRequest(
						generateBadTestCaseName1()
								);
				fail();
			} catch (HttpParseException e) {
				assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);			}
			
		}
		
		@Test
		void parseHttpRequestBadMethod2() 
		{	
			try {
				HttpRequest request = httpParser.parseHttpRequest(
						generateBadTestCaseName2()
								);
				fail();
			} catch (HttpParseException e) {
				assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);			}
			
		}
		
		@Test
		void  parseHttpRequestInvNumItems1() 
		{	
			try 
				{
					HttpRequest request = httpParser.parseHttpRequest(
							generateValidBCaseLineInvNumItems1()
									);
					fail();
				} 
			catch (HttpParseException e) 
					{
						assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
					}
		}
		
		@Test
		void  parseHttpEmptyRequestLine() 
		{	
			try 
				{
					HttpRequest request = httpParser.parseHttpRequest(
							generateBadTestCaseEmptyRequsetLine()
									);
					fail();
				} 
			catch (HttpParseException e) 
					{
						assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
					}
		}

		void  parseHttpRequestLineCRnoLF() 
		{	
			try 
				{
					HttpRequest request = httpParser.parseHttpRequest(
							generateBadTestCaseEmptyRequsetLine()
									);
					fail();
				} 
			catch (HttpParseException e) 
					{
						assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);
					}
		}

		@Test
		void parseHttpRequestBadHttpVersion() 
		{	
			try {
				HttpRequest request = httpParser.parseHttpRequest(
						generateBadHttpVersionTestCase()
								);
				fail();
			} catch (HttpParseException e) {
				assertEquals(e.getErrorCode(),HttpStatusCode.CLIENT_ERROR_400_BAO_REQUEST);			}
			
		}
		
		@Test
		void parseHttpRequestUnsurpportHttpVersion() 
		{	
			try {
				HttpRequest request = httpParser.parseHttpRequest(
						generateUnsurportHttpVersionTestCase()
								);
				fail();
			} catch (HttpParseException e) {
				assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);			}
			
		}
		
		@Test
		void parseHttpRequestsurpportHttpVersion() 
		{	
			try {
				HttpRequest request = httpParser.parseHttpRequest(
						generateSurportHttpVersionTestCase1()
								);
			
				assertNull(request);
				assertEquals(request.getBestCompatibleHttpVersion(), HttpVersion.HTTP_1_1 );
				assertEquals(request.getOrginalhttpVersion(), "HTTP/1.2");
				
			} catch (HttpParseException e) 
				{
					fail();
				}
		}
		
		// TEST 시험 메소드
		private InputStream generateValidGetTestCase()
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

		private InputStream generateBadTestCaseName1()
			{
				String rawData = "GET1 / HTTP/1.1\r\n"
						+ "Host: localhost:8083\r\n"
						+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
						+ "\r\n";
				InputStream inputStream = new ByteArrayInputStream(
							rawData.getBytes(
								StandardCharsets.US_ASCII	)
							);
				
				return inputStream;
			}
		
		private InputStream generateBadTestCaseName2()
			{
				String rawData = "GET2 / HTTP/1.1\r\n"
						+ "Host: localhost:8083\r\n"
						
						+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
						+ "\r\n";
				InputStream inputStream = new ByteArrayInputStream(
							rawData.getBytes(
								StandardCharsets.US_ASCII	)
							);
				
				return inputStream;
			}
		
		private InputStream generateValidBCaseLineInvNumItems1()
			{
				String rawData = "GET / AAAAA HTTP/1.1\r\n"
						+ "Host: localhost:8083\r\n"
						+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
						+ "\r\n";
				InputStream inputStream = new ByteArrayInputStream(
							rawData.getBytes(
								StandardCharsets.US_ASCII	)
							);
				
				return inputStream;
			}

		private InputStream generateBadTestCaseEmptyRequsetLine()
			{
				String rawData = "\r\n"
						+ "Host: localhost:8083\r\n"
						+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
						+ "\r\n";
				InputStream inputStream = new ByteArrayInputStream(
							rawData.getBytes(
								StandardCharsets.US_ASCII	)
							);
				
				return inputStream;
			}
		
		private InputStream generateValidBCaseLineOnlyCRnoLF()
		{
			String rawData = "GET / AAAAA HTTP/1.1\r" // <----- no LF
					+ "Host: localhost:8083\r\n"
					+ "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,ja;q=0.6\r\n"
					+ "\r\n";
			InputStream inputStream = new ByteArrayInputStream(
						rawData.getBytes(
							StandardCharsets.US_ASCII	)
						);
			
			return inputStream;
		}
		
		private InputStream generateBadHttpVersionTestCase()
		{
			String rawData = "GET / HTP/1.1\r" // <----- no LF
					+ "Host: localhost:8083\r\n"
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
		
		private InputStream generateUnsurportHttpVersionTestCase()
		{
			String rawData = "GET / HTTP/2.1\r" // <----- no LF
					+ "Host: localhost:8083\r\n"
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
		
		private InputStream generateSurportHttpVersionTestCase1()
		{
			String rawData = "GET / HTTP/1.2\r" // <----- no LF
					+ "Host: localhost:8083\r\n"
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
