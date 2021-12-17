package com.example.demo.coderfromscratch.http;

public enum HttpMethod 
	{
		GET,HEAD;
		public static final int MAX_LENGTH;
		
		static
			{	int tempMaxLenth = -1;
				
				for (HttpMethod method : values())
					{
						if(method.name().length() > tempMaxLenth)
							{
								tempMaxLenth = method.name().length();
							}
					}
				MAX_LENGTH = tempMaxLenth; 
			}
	}
