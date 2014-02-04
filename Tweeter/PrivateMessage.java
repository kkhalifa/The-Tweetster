import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class PrivateMessage extends JFrame implements ActionListener{

	JPanel panel;
	JButton btnBackProf, btnBackLogin, btnSubscribe;
	JTextField subscribeTo;
	String message;
	JTextArea messages;
	DataBase data;

	public PrivateMessage(DataBase d, boolean loggedIn){

		super("Private Messages");
		data=d;
		panel = new JPanel();
		btnBackLogin = new JButton("Back to Login Screen");
		btnBackProf = new JButton("Back to profile");
		messages = new JTextArea();
		subscribeTo = new JTextField("Enter user to subscribe");

		panel.setLayout(null);
		if(loggedIn){
			btnBackProf.setBounds(10, 55, 150, 25);
		}else{
			btnBackLogin.setBounds(10,55, 175,25);
		}
		messages.setBounds(10, 100, 450, 250);
		

		btnBackLogin.addActionListener(this);
		btnBackProf.addActionListener(this);
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());
		



		panel.add(btnBackLogin);
		panel.add(btnBackProf);
		panel.add(messages);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);




		//creates array to hold the tweets
		String privateTweets[] = new String[150];
		int numTweetCounter=0;
		String tweet=data.displayPrivateTweets();
		while(!tweet.equals("That's all the tweets for now!")){
			//gets a tweet to display

			privateTweets[numTweetCounter]= tweet;
			tweet=data.displayPrivateTweets();
			numTweetCounter++;

		}

		//reset the tweet counter for future calls to get all of the tweets
		data.resetTweetCounter();


		//display all of the tweets by starting with allTweets[numTweetCounter] so that you have the newest at the top of the textfield
		for(int i=numTweetCounter-1;i>-1;i--){
			messages.append(privateTweets[i]+"\n");
		}


	}
	public void actionPerformed(ActionEvent evt){

		if(evt.getSource()==btnBackLogin){


			MainFrame mm= new MainFrame(data);
			mm.setVisible(true);
			this.setVisible(false);
		}else if(evt.getSource() == btnBackProf){
			MainMenu mm = new MainMenu(data);
			mm.setVisible(true);
			this.setVisible(false);
		}
	}
}