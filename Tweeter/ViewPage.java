import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class ViewPage extends JFrame implements ActionListener{

	JPanel panel;
	JButton btnBackProf,btnSubscribe;

	String message, u;
	JTextArea messages;
	DataBase data;
	boolean subbed = true;

	public ViewPage(DataBase d,String user){

		super("Profile Page of "+user);
		data=d;
		u = user;
		panel = new JPanel();

		btnSubscribe = new JButton("Subscribe to this user");
		btnBackProf = new JButton("Back to profile");

		messages = new JTextArea();


		panel.setLayout(null);

		//setting bounds
		messages.setBounds(10, 100, 450, 250);
		btnBackProf.setBounds(10, 55, 150, 25);
		btnSubscribe.setBounds(200, 55, 150, 25);


		//actionlistners
		btnBackProf.addActionListener(this);
		btnSubscribe.addActionListener(this);



		panel.add(messages);

		if(subbed){
			panel.add(btnSubscribe);
		}

		panel.add(btnBackProf);




		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);

		/*for(int i=0;i<3;i++){
			messages.append(data.displayLineByLine(u+".txt",i)+"\n");
		}*/


		//display the 3 pieces of information from the users profile
		messages.append("Profile of user: "+data.displayLineByLine(u+".txt",0)+"\n");

		messages.append("Birthday: "+data.displayLineByLine(u+".txt",1)+"\n");

		messages.append("Email: "+data.displayLineByLine(u+".txt",2)+"\n");
		
		


	}

	public ViewPage(DataBase d,String user, boolean b){

		super("Profile Page of "+user+".txt");
		data=d;
		u = user;
		subbed=b;
		panel = new JPanel();

		btnSubscribe = new JButton("Subscribe to this user");
		btnBackProf = new JButton("Back to profile");

		messages = new JTextArea();


		panel.setLayout(null);

		messages.setBounds(10, 100, 450, 250);
		btnBackProf.setBounds(10, 55, 150, 25);
		btnSubscribe.setBounds(200, 55, 150, 25);





		btnBackProf.addActionListener(this);
		btnSubscribe.addActionListener(this);
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());



		panel.add(messages);

		if(subbed){
			panel.add(btnSubscribe);
		}

		panel.add(btnBackProf);




		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);


		/*for(int i=0;i<3;i++){
			messages.append(data.displayLineByLine(u+".txt",i)+"\n");
		}*/

		//display the 3 pieces of information from the users profile
		messages.append("Profile of user: "+data.displayLineByLine(u+".txt",0)+"\n");

		messages.append("Birthday: "+data.displayLineByLine(u+".txt",1)+"\n");

		messages.append("Email: "+data.displayLineByLine(u+".txt",2)+"\n");
		
		






	}

	public void actionPerformed(ActionEvent evt){

		if(evt.getSource()==btnSubscribe){	//subscribed button hit

			data.subscribedUpdate("@"+u);
			ViewPage mm = new ViewPage(data,u,false);
			mm.setVisible(true);
			this.setVisible(false);

		}else if(evt.getSource() == btnBackProf){
			MainMenu mm = new MainMenu(data);
			mm.setVisible(true);
			this.setVisible(false);
		}
	}
}