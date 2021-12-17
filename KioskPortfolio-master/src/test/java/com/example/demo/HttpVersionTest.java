package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.coderfromscratch.http.BadHttpVersionException;
import com.example.demo.coderfromscratch.http.HttpVersion;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;




public class HttpVersionTest 
	{
		@Test
		void getBestCompatibleVersionExactMatch()
			{
				HttpVersion version = null;
				
				try 
					{
						version = HttpVersion.getBestCompatilbleVersion("HTTP/1.1");
					} 
				catch (BadHttpVersionException e) 
					{
						fail();
					}
				
				assertNotNull(version);
				assertEquals(version, HttpVersion.HTTP_1_1);
			}
		
		@Test
		void getBestCompatibleVersionBadFormat()
			{
				HttpVersion version = null;
				
				try 
					{
						version = HttpVersion.getBestCompatilbleVersion("HTTP/1.1");
						fail();
					} 
				catch (BadHttpVersionException e) 
					{
						
					}
				
				}
		
		@Test
		void getBestCompatibleVersionHigherVersion()
			{
				HttpVersion version = null;
				
				try 
					{
						version = HttpVersion.getBestCompatilbleVersion("HTTP/1.2");
						assertNotNull(version);
						assertEquals(version, HttpVersion.HTTP_1_1);
					} 
				catch (BadHttpVersionException e) 
					{
						fail();
					}
				
				}
	}
