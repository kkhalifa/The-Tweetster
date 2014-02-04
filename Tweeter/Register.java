import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class Register extends JFrame implements ActionListener{

	JPanel panel;
	JButton regButton,btnBackLogin;
	JTextField name, password, email, dob, info;
	JLabel nameLabel, passLabel, emailLabel, dobLabel, infoLabel;
	String uName = "kyle";
	String pass = "k";
	String nameCheck, passCheck;
	DataBase data;
	User tempUser;

	public Register(DataBase d){

		super("Registration");
		//making objects
		data = d;
		panel = new JPanel();
		regButton = new JButton("Register");
		name = new JTextField();
		password = new JTextField();
		email = new JTextField();
		dob = new JTextField();
		info = new JTextField();

		nameLabel = new JLabel("Enter your desired username");
		btnBackLogin = new JButton("Back to Login Screen");
		passLabel = new JLabel("Enter your desired password");
		emailLabel = new JLabel("Enter your email adress");
		dobLabel = new JLabel("Enter your birthday");
		infoLabel = new JLabel("Enter some user info");

		panel.setLayout(null);

		//btnNextFrame.setBounds(10,10, 150,25);
		nameLabel.setBounds(10, 10, 175, 25);
		btnBackLogin.setBounds(10,255, 175,25);
		name.setBounds(180, 10, 155, 25);
		passLabel.setBounds(10, 45, 175, 25);
		password.setBounds(180, 45, 155, 25);
		emailLabel.setBounds(10, 75, 175, 25);
		email.setBounds(180, 75, 155, 25);
		dobLabel.setBounds(10, 105, 175, 25);
		dob.setBounds(180, 105, 155, 25);
		regButton.setBounds(180, 135, 125, 25);
		panel.add(regButton);
		panel.add(name);
		panel.add(password);
		panel.add(nameLabel);
		panel.add(passLabel);
		panel.add(email);
		panel.add(emailLabel);
		panel.add(dob);
		panel.add(dobLabel);
		panel.add(btnBackLogin);
		
		regButton.addActionListener(this);
		btnBackLogin.addActionListener(this);
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evt){

		if(evt.getSource()== regButton){
			File f=new File(name.getText()+".txt");
			if(!dob.getText().isEmpty()&& !password.getText().isEmpty()&& !email.getText().isEmpty()&& !name.getText().isEmpty() && !f.exists()){
				tempUser = new User(dob.getText(), password.getText(), email.getText(), name.getText());
				data.setUser(tempUser);
				data.createUserFile();
				//data.createUserMessageFile();
				MainFrame mm = new MainFrame(data);
				mm.setVisible(true);
				this.setVisible(false);
				
			}else if(f.exists()){
				name.setText("UserName Already Taken.");
			}			
		}else if(evt.getSource()==btnBackLogin){


			MainFrame mm= new MainFrame(data);
			mm.setVisible(true);
			this.setVisible(false);
		}
	}

}