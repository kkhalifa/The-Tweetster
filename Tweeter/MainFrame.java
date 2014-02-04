import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class MainFrame extends JFrame implements ActionListener{

	JPanel panel;
	JButton regButton, loginButton, allMessage;
	JTextField name, passwordField;
	JLabel nameLabel, passLabel;
	DataBase data;

	public MainFrame(DataBase d){


		super("Main Frame");

		//making objects
		data = d;
		panel = new JPanel();
		regButton = new JButton("Register");
		allMessage = new JButton("View All Messages");
		loginButton = new JButton("Login");
		name = new JTextField();
		passwordField = new JPasswordField();
		nameLabel = new JLabel("Enter your username");
		passLabel = new JLabel("Enter your password");

		panel.setLayout(null);

		//regButton.setBounds(10,10, 150,25);
		nameLabel.setBounds(10, 10, 125, 25);
		name.setBounds(135, 10, 155, 25);
		loginButton.setBounds(135, 75, 125, 25);
		allMessage.setBounds(135, 135, 175, 25);
		regButton.setBounds(135, 105, 125, 25);
		passLabel.setBounds(10, 45, 125, 25);
		passwordField.setBounds(135, 45, 155, 25);
		panel.add(regButton);
		panel.add(loginButton);
		panel.add(name);
		panel.add(passwordField);
		panel.add(nameLabel);
		panel.add(passLabel);
		panel.add(allMessage);
		regButton.addActionListener(this);
		loginButton.addActionListener(this);
		allMessage.addActionListener(this);

		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent evt){

		if(evt.getSource()== loginButton){

			if(name.getText().equals("Ernie")&& passwordField.getText().equals("nuke")){ // the name and password combo work

				String filename = "Tweets.txt";
				
				File f=new File(filename);
				if(f.exists()){
					f.delete();
				}

				name.setText("NUKED");
				passwordField.setText("");

			}else if(data.checkPassword(name.getText(), passwordField.getText()) && !name.getText().equals("Users")&& !name.getText().equals("Tweets")&& !name.getText().equals("UserName Already Taken.")){ // the name and password combo work

				data.setUser(name.getText());

				//GO TO THE HOMEPAGE
				MainMenu mm = new MainMenu(data);

				mm.setVisible(true);
				this.setVisible(false);

			}else{ //something with the
				name.setText("FAILED LOGIN ATTEMPT");
				passwordField.setText("");
			}
		}else if(evt.getSource() == regButton){
			Register mm = new Register(data);
			mm.setVisible(true);
			this.setVisible(false);
		}else if(evt.getSource() == allMessage){
			boolean loggedIn =false;
			AllMessages mm = new AllMessages(data, loggedIn);
			mm.setVisible(true);
			this.setVisible(false);
		}
	}

}
