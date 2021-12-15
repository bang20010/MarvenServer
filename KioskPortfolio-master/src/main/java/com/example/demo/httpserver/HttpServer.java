package com.example.demo.httpserver;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.httpserver.config.ConfigureationManager;
import com.example.demo.httpserver.config.Configurration;
import com.example.demo.httpserver.core.ServerListenerThread;

public class HttpServer 
	{
		private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
	
		public static void main(String[] args) 
			{
			
				LOGGER.info("Server 시작");
				ConfigureationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
				Configurration configurration = ConfigureationManager.getInstance().getCurrentConfiguration();
				
				LOGGER.info("Using Port : " + configurration.getPort());
				LOGGER.info("Using WebRoot : " + configurration.getWebroot());
				
				
				try 
					{
						ServerListenerThread serverListenerThread  = new ServerListenerThread(configurration.getPort(),
								configurration.getWebroot());
						serverListenerThread.start();
					}
				catch (IOException e) 
					{
						e.printStackTrace();
					}
				
			}
	}
