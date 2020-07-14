package newclientserver;
import javax.swing.*;  
	import java.awt.*;  
	import java.awt.event.*; 
@SuppressWarnings("serial")
public class server_gui extends Frame implements ActionListener{  //Creating a GUI for the server
	    JTextField tf; JLabel l; JButton b; JTextArea ta; 
	    server_gui(){  
	      
	    	setLayout(new GridLayout());   
	        l=new JLabel("Server has started");  
	        l.setBounds(50,100, 250,20);      
	        b=new JButton("Exit");  
	        b.setBounds(200,200,200,200);  
	        ta = new JTextArea(40,70);
	        ta.setBounds(150,150,200,200);
	        b.addActionListener(this);    
	        add(ta);
	        add(b);
	        add(l);    
	        setSize(900,900);  
	        setLayout(null);  
	        setVisible(true);  
	        new server(ta);
	        
	      
	    }  
	    public void actionPerformed(ActionEvent e) {  
	       System.exit(0);
	    }  
	    public static void main(String[] args) {  
	        new server_gui();  
	    } }  

	

