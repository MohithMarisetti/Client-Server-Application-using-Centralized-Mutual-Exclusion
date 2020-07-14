package newclientserver;


	import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;    
	public class client3_gui {  
	public static void main(String[] args) throws IOException {  
	    JFrame f=new JFrame("Client3 GUI");  
	    JTextField tf1=new JTextField();  
	    tf1.setBounds(50,50, 150,24);  
	    JTextField tf2=new JTextField();  
	    tf2.setBounds(90,80, 150,24);  
	    JTextField tf3=new JTextField();  
	    tf3.setBounds(130,110, 150,24);  

	    JButton b1=new JButton("submit");  
	   // b1.setBounds(70,100,95,30);
	    b1.setBounds(110,140,95,30);

	    JButton b2=new JButton("exit");  
	    b2.setBounds(150,170,145,30);
	    b2.addActionListener(new ActionListener(){  
	public void actionPerformed(ActionEvent e){ 
		
		System.exit(0); 
	        }  
	    });  
	    f.add(b1);f.add(tf1);f.add(tf2);f.add(tf3); f.add(b2);  
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);   
	 new client1(tf1,tf2,tf3,b1);

	
	}  
}
	