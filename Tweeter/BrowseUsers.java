import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class BrowseUsers extends JFrame implements ActionListener{

	JPanel panel;
	JButton btnSubscribe, btnViewProf,btnBackProf;
	JTextField subscribeTo, viewProf;
	String message;
	JTextArea messages;
	DataBase data;

	public BrowseUsers(DataBase d){

		super("Browse Users");
		data=d;
		panel = new JPanel();

		//making objects
		btnViewProf = new JButton("View Profile");
		btnSubscribe = new JButton("Subscribe to user");
		btnBackProf = new JButton("Back to profile");

		messages = new JTextArea();
		subscribeTo = new JTextField("Enter user to subscribe");
		viewProf = new JTextField("Enter user to view");

		panel.setLayout(null);

		//setting bounds
		messages.setBounds(10, 100, 450, 250);
		btnSubscribe.setBounds(325, 75, 150, 25);
		btnViewProf.setBounds(325, 25, 150, 25);
		btnBackProf.setBounds(10, 55, 150, 25);


		subscribeTo.setBounds(175, 75, 150, 25);
		viewProf.setBounds(175, 25, 150, 25);



		btnSubscribe.addActionListener(this);
		btnBackProf.addActionListener(this);
		btnViewProf.addActionListener(this);
		
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());


		panel.add(messages);
		panel.add(subscribeTo);
		panel.add(viewProf);
		panel.add(btnSubscribe);
		panel.add(btnViewProf);
		panel.add(btnBackProf);


		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);

		//displays the users
		for(int i = 0;i<data.countLines("Users.txt");i++){
			messages.append(data.displayLineByLine("Users.txt",i)+"\n");
		}



	}
	public void actionPerformed(ActionEvent evt){

		if(evt.getSource() == btnSubscribe){	//subscribe button hit
			File f=new File(subscribeTo.getText()+".txt");
			if(!f.exists()){
				subscribeTo.setText("This user does not exist.");

			}else if(f.exists()){

				data.subscribedUpdate("@"+subscribeTo.getText());
				subscribeTo.setText("");
				MainMenu mm = new MainMenu(data);
				mm.setVisible(true);
				this.setVisible(false);

			}
		}else if(evt.getSource() == btnViewProf){	//subscribe button hit
			File f=new File(viewProf.getText()+".txt");
			if(!f.exists()){
				viewProf.setText("You cannot subscribe to this person.");

			}else if(f.exists()){

				ViewPage mm = new ViewPage(data,viewProf.getText());
				mm.setVisible(true);
				this.setVisible(false);

			}
		}else if(evt.getSource() == btnBackProf){
			MainMenu mm = new MainMenu(data);
			mm.setVisible(true);
			this.setVisible(false);
		}

	}

}