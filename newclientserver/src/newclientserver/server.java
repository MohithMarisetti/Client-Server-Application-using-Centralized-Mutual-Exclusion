package newclientserver;


import java.io.*; 
import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.*;

public class server
{ 
	ServerSocket ss = null; 
  Socket s = null; 
  JTextArea ta;
	  
@SuppressWarnings("resource")
public server(JTextArea ta)
	{
  this.serverRun(ta);     //Call the server run method to turn on the server 
	
  } 

public void serverRun(JTextArea ta)
{
	 ServerSocket ss=this.ss;
      Socket s=this.s;
       this.ta= ta;
      
		try {
			ss = new ServerSocket(5056);   //create a new server socket
		} catch (IOException e1) {
			e1.printStackTrace();
		} 

      while (true)  	
      { 
            
          try  {
          
              // socket object to receive incoming client requests 
              s = ss.accept(); 
                
         
              // obtaining input and out streams 
              DataInputStream dis = new DataInputStream(s.getInputStream()); 
              DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                
              System.out.println("Assigning new thread for this client"); 
              // create a new thread i.e; clientHandler object 
              Thread t = new Thread(new ClientHandler(s, dis, dos, ta)); 
              
             
              
			
              //Invoking the start() method 
              t.start(); 
             
                
          } 
          catch (Exception e){ 
              try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
              e.printStackTrace(); 
          } 
         
      } 
}

} 

//ClientHandler class 
class ClientHandler implements Runnable  
{ 
   DataInputStream dis; 
   DataOutputStream dos; 
   Socket s; 
  String name="";
  JTextArea ta;
  boolean first = true;
  boolean keep_going = true;


// Constructor 
public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, JTextArea ta)  
{ 
    this.s = s; 
    this.dis = dis; 
    this.dos = dos; 
    this.ta = ta;
} 

  @Override
  public void run()  
  { 
      String received=null; 
      String toreturn; 
      String name;

	    try {
	    	//take the username from the user
      	dos.writeUTF("Give me your name");
			name = dis.readUTF();
			  if(this.first=true)
		      {
				//Display the username in real time in the server GUI
		      	ta.append("\n"+name+" has connected!");
		      	this.first = false;
		      }
			  
		      //store username
			this.name = name; 
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
      while (keep_going == true)  //keep accepting the requests of the client till he is available
      { 
    	  ProcessRequest.processRequest(s,dis,dos,ta, this.name);  //Call the processRequest method to perform the task
       }//while close 
        
      try
      { 
          // closing resources 
          this.dis.close(); 
          this.dos.close(); 
            
      }catch(IOException e){ 
          e.printStackTrace(); 
      } 
  } 
} 


