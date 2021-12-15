package com.example.demo.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.httpserver.config.Configurration;

public class ServerListenerThread extends Thread
	{
		private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
	
		private int port;
		private String wevbroot;
		private ServerSocket serverSocket;

		
		public ServerListenerThread(int port, String wevbroot) throws IOException
			{
				this.port = port;
				this.wevbroot = wevbroot;
				this.serverSocket = new ServerSocket(this.port);
			}

		// run()메서드는 Httpserver에 메인메서드가 동작하기 전에 작동한다.
		@Override
		public void run()
			{
				try 
					{
						while(serverSocket.isBound() && !serverSocket.isClosed())
						{
							Socket socket = serverSocket.accept();
							
							LOGGER.info(" * Connection accepted : " + socket.getInetAddress());
							
							HttpConnetctionWorKerThread workerThread = new HttpConnetctionWorKerThread(socket);
							workerThread.start();
							
							try 
								{
									sleep(5000);
								}	 
							catch (Exception e) 
								{
									e.printStackTrace();
								}
							
						}
						//	serverSocket.close();
					} 
				catch (Exception e) 
					{
						LOGGER.error("Problem with setting socket",e);
						e.printStackTrace();
					}
				finally 
					{
						if(serverSocket!=null)
							{
								try 
								{
									serverSocket.close();
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
							}
					}
			}
	}
