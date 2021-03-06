package com.example.demo.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnetctionWorKerThread extends Thread
	{
		private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
		private Socket socket;
		
		public HttpConnetctionWorKerThread(Socket socket) 
			{
				this.socket = socket;
			}
	
		@Override
		public void run()
			{
				InputStream inputStream = null;
				OutputStream outputStream = null;
			
				try 
				{
					inputStream = socket.getInputStream();
					outputStream = socket.getOutputStream();
					/*
					 * int _byte;
					 * 
					 * while ((_byte = inputStream.read())>= 0) { System.out.print((char)_byte); }
					 */
					String html = "<html><head><title>Simply Java HTTP Server</title></head>"
							+ "<body><h1>This page was served using Simple Java HTTP</h1></body></html>"; 
					
					final String CRLF = "\n\r"; // 13 , 10
					
					String response = 
							"HTTP/1.1 200 OK"+CRLF+ // Status Line : HTTP Version Response_Code Response_Message
							"Content-Length : "+html.getBytes().length + CRLF +  //Header
								CRLF +
								html+
								CRLF+CRLF;
					
					outputStream.write(response.getBytes());
		
				
					
						try 
							{
								sleep(5000);
							}	 
						catch (InterruptedException e) 
							{
								e.printStackTrace();
							}

						LOGGER.info("Connection Processing Finished");
					} 
					catch (IOException e) 
						{
							LOGGER.error("Problem with communication",e);
							e.printStackTrace();
						}
					finally
						{
						if(inputStream != null) 
							{
								try 
								{
									inputStream.close();
								}
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							}
						if(outputStream != null) 
							{
								try 
								{
									outputStream.close();
								}
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							}
							
						if(socket != null) 
							{
								try 
								{
									socket.close();
								}
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							}	
							
						}
			}
		
	}
