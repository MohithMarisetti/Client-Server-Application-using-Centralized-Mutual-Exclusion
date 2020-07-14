package newclientserver;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*; 
import java.net.*;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField; 

//Client1 class 
public class client1
{ 
  public client1(JTextField tf1,JTextField tf2,JTextField tf3,JButton b1) throws IOException  
  { 
    
            
          // getting localhost ip 
          InetAddress ip = InetAddress.getByName("localhost"); 
    
          // establish the connection with server port 5056 
          Socket s = new Socket(ip, 5056); 
    
          // obtaining input and out streams 
          DataInputStream dis = new DataInputStream(s.getInputStream()); 
          DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
    
          // the following loop performs the exchange of 
          // information between client and client handler 
          
          try
      {   
     
          
          
          tf1.setText(dis.readUTF());  //Says Give me your name on Client GUI
          b1.addActionListener(new ActionListener(){  //ACTION LISTENER TO LISTEN TO THE BUTTON SEND
          	@Override
          	public void actionPerformed(ActionEvent e){  
                 String name = tf2.getText();  
                  try {
						dos.writeUTF(name);  //When button is pressed send the username written in TextField(TF2)  to the server
						dos.flush();
						tf2.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

          	        }  
          	    });

          
          
          
          
          
          while (true)  //PROCESS THE INCOMING CLIENT REQUESTS UNTIL THE CLIENT REPLIES "no"
          { 	
              
        	  String server_text = dis.readUTF(); 
              tf1.setText(server_text);
              b1.addActionListener(new ActionListener(){  
                	@Override
                	public void actionPerformed(ActionEvent e){   //ACTION LISTENER TO LISTEN TO THE BUTTON PRESS  
                		
                		int number;
                		String give_number = tf2.getText();
                        System.out.println("what you replied for 'give number?' is "+give_number);

              if(give_number.equals("yes")) //IF CLIENT SAYS "yes" generate a random integer between 5 to 15 to pass it to server
              {
            	  tf2.setText("");  //Clear the previous data in the field
              	Random r = new Random();
              	number = r.nextInt(10)+5;
              	Integer int_number =  new Integer(number);
              	
              	
              	try {
              		
              		dos.writeUTF("yes");  //SAY "yes" to indicate that we want to send the data to the server
                    String HTTPREQ = (new message()).createHttpRequest(int_number.toString());   //CREATE A HTTP REQUEST TO SEND IT TO THE SERVER
              		dos.writeUTF(HTTPREQ); 		//passing the number to the server
      
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
              
              
              } 
              // If client sends exit OR no, close this connection  
              // and then break from the while loop 
              else if(give_number.equals("no"))  // if user gives "no" then we close the connection
              { 
            	  
              	try {
					dos.writeUTF("no");
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
              	System.out.println("Closing this connection : " + s); 
                
              	try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
              	System.out.println("Connection closed"); 
              }
              else
              {	            	  /*Dont do anything */             }
                    }  
        	    }); // Action listener closed
              String received = dis.readUTF(); 

              String parseText[] =null;  
             parseText = received.split("Response-Content:"); //Parsing the received text
             
             if(parseText.length>1)
             received = parseText[1];
             tf3.setText(received);  //Display the received text on the application
          
              dos.flush();
              String last_text = dis.readUTF();
              while(true)
              {
            	  if(last_text.equals("done"))
            	  {	
            		  dos.writeUTF("done");  //Say the server that its done with the processing 
            		  break;}
              }
          dos.flush();
          } //while close
            
        
      }catch(Exception e){ 
          e.printStackTrace(); 
      } 
      finally {
    	  dos.writeUTF("no");
			System.exit(0);
			
      }
  } 
} 