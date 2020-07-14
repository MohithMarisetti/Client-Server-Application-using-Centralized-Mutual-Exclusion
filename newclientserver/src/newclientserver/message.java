package newclientserver;

import java.util.Date;

public class message {

	public String createHttpRequest(String time)   //Creating a HTTP REQUEST and adding the Time to sleep
	{
		StringBuilder HttpData = new StringBuilder();
		HttpData.append(new Date()).append("\nPOST/HTTP/1.1\ntext/plain\nUser-Agent:Client Socket\nSleep-Time:").append(time);
		return HttpData.toString();
	}
	
	public String createHttpResponse(String data)   // Creating HTTP RESPONSE and adding the message data
	{

		StringBuilder HttpData = new StringBuilder();
		HttpData.append(new Date()).append("\nPOST/HTTP/1.1\ntext/plain\nResponse-Content:").append(data);
		return HttpData.toString();
	}
	
}
