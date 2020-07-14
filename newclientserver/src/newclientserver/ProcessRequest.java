package newclientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class ProcessRequest {

	public static void processRequest(Socket s, DataInputStream dis, DataOutputStream dos, JTextArea ta, String name) {
		long start_time=0,end_time=0;
		try {  

			
	  		String received="";
	        // Ask user if he wants to give a number or not
	        dos.writeUTF("give me a number? yes/no\n"); 
	      
	        // receive the answer from client 
				received = dis.readUTF(); 
		        start_time = System.nanoTime();   

				//stop the thread execution and remove the client name from server  GUI, since client has disconnected
	        if(received.contains("no")||received == ""||received==null) 
	        {
	        	String text =  ta.getText();
	        	String delText = name+" has connected!";
	        	 String newText = text.replace(delText, "");
	        	 ta.setText(newText);  //REMOVING THE CLIENT NAME FROM THE CONNECTED USERS LIST IN THE SERVER GUI
	        	 
	        	 
	            System.out.println("Client " + s + " sends exit..."); 
	            System.out.println("Closing this connection."); 
	            s.close(); 
	            System.out.println("Connection closed"); 
	            
	        } 
	        else if(received.contains("yes"))
	        {
	        	
	        	synchronized(ProcessRequest.class) {  //Make the processing logic synchronized so that one thread(i.e; client) doesn't step on another
	        received = dis.readUTF();
	         
	        System.out.println("I'm server: The number you gave is? "+received);
	         String parseText[] = null;
	         parseText = received.split("Sleep-Time:");
	         if(parseText.length>1)
	        received = parseText[1];
			
	        
	        
	        int sleepTime = Integer.parseInt(received);
				int sleepTimeinSeconds = sleepTime*1000;
	        try {
	      	  //sleep for the time that client has specified
					Thread.sleep(sleepTimeinSeconds);
					//display the output to the client 
					end_time = System.nanoTime();
					String HTTPRES = new message().createHttpResponse("server waited for "+((end_time-start_time)/1000000000)+" seconds");  //Calcuate the total time spent and create a HTTP RESPONSE 
					
					
					dos.writeUTF(HTTPRES);   //WRITE THE HTTP RESPONSE BACK TO THE CLIENT
	              System.out.println("I'm server: sent the reply stating 'server waited for #secs");
	              
	              
	              dos.flush();
	              dos.writeUTF("done");
	              dos.flush();
	              
	              while(true)
	              {
	              	if(dis.readUTF().equals("done"))
	              	{break;	 }
	              }

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	}//synchronized block close
	        }
	        
	    }//try close
	    catch (Exception e) { 
			
	    }

	}//processRequest Function Close

}//CLASS CLOSE
