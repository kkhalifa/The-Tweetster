import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class MainMenu extends JFrame implements ActionListener{

	JPanel panel;
	JButton btnPost, btnView, btnBack, btnSubscribe, btnSearchHash, btnPrivMessage, btnBrowse;
	JTextField messageField, subscribeTo, hashSearch;
	JTextArea messages;
	JScrollPane scroll;
	DataBase data;
	public MainMenu(DataBase d){


		
		super("Hello "+d.getUser().getName()+", welcome to Tweetster!");
		//making objects
		data = d;
		panel = new JPanel();
		btnPost = new JButton("Post Message");
		btnBrowse = new JButton("Browse");
		btnView = new JButton("View All Messages");
		btnSubscribe = new JButton("Subscribe to user");
		btnPrivMessage = new JButton("View private messages");
		btnSearchHash = new JButton("Search");
		btnBack = new JButton("Logout");
		messageField = new JTextField("Enter message here");
		subscribeTo = new JTextField("Enter user to subscribe");
		hashSearch = new JTextField("Search #hashtags");
		messages = new JTextArea();
		scroll = new JScrollPane(messages);
		
		
		panel.setLayout(null);

		//setting bounds
		messageField.setBounds(10,10, 150,25);
		btnPost.setBounds(165,10, 120,25);
		btnBrowse.setBounds(300,10, 80,25);
		btnView.setBounds(10,35, 150,25);
		btnBack.setBounds(390,10, 80,25);
		btnPrivMessage.setBounds(10,65, 150,25);

		btnSearchHash.setBounds(325,45, 150,25);
		hashSearch.setBounds(175, 45, 150, 25);

		messages.setBounds(10, 100, 450, 250);

		btnSubscribe.setBounds(325, 75, 150, 25);
		subscribeTo.setBounds(175, 75, 150, 25);

		scroll.setPreferredSize(new Dimension(400,200));


		//actionlisteners
		btnBack.addActionListener(this);
		btnPost.addActionListener(this);
		btnBrowse.addActionListener(this);
		btnView.addActionListener(this);
		btnSubscribe.addActionListener(this);
		btnPrivMessage.addActionListener(this);
		btnSearchHash.addActionListener(this);
		
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());

		//making objects
		panel.add(btnPost);
		panel.add(btnBrowse);
		panel.add(btnSearchHash);
		panel.add(btnView);
		//panel.add(btnDelete);
		panel.add(btnBack);
		panel.add(messageField);
		panel.add(btnPrivMessage);
		panel.add(messages);
		panel.add(subscribeTo);
		panel.add(btnSubscribe);
		panel.add(scroll);
		panel.add(hashSearch);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);





		//creates array to hold the tweets
		String subTweets[] = new String[150];
		int numTweetCounter=0;
		String tweet=data.displaySubbedTweets();
		while(!tweet.equals("That's all the tweets for now!")){
			//gets a tweet to display
			subTweets[numTweetCounter]= tweet;
			tweet=data.displaySubbedTweets();
			numTweetCounter++;

		}

		//reset the tweet counter for future calls to get tweets
		data.resetTweetCounter();

		
		//display all of the tweets by starting with allTweets[numTweetCounter] so that you have the newest at the top of the textfield
		for(int i=numTweetCounter-1;i>-1;i--){
			messages.append(subTweets[i]+"\n");
		}
		




	}
	public void actionPerformed(ActionEvent evt){

		if(evt.getSource()==btnBack){ //user has hit log out button

			MainFrame mm= new MainFrame(data);
			mm.setVisible(true);
			this.setVisible(false);



		}else if(evt.getSource() == btnPost){ //adds a new tweet


			//data.tweet(messageField.getText(),data.getUser());	//adds new tweet to database, in file "Tweet.txt"

			char start = messageField.getText().charAt(0);
			String[] splitMessage = messageField.getText().split("\\s+");
			if(start == '@'){
				String toUser = splitMessage[0];
				data.directMessage( toUser.substring(1),data.getUser(),messageField.getText());
				//store hashtags if there are any
				//System.out.println("about to give storeThis Hash this string <"+"@"+data.getUser().getName()+" - "+messageField.getText()+">");
				data.storeThisHash("@"+data.getUser().getName()+" - "+messageField.getText());

				messageField.setText("");
			}else{
				data.tweet(messageField.getText());	//adds new tweet to database, in file "Tweet.txt"
				
				messages.append("@"+data.getUser().getName()+" - "+messageField.getText() + "\n"); //displays it on screen
				


				//store hashtags if there are any
				//System.out.println("about to give storeThis Hash this string <"+"@"+data.getUser().getName()+" - "+messageField.getText()+">");
				data.storeThisHash("@"+data.getUser().getName()+" - "+messageField.getText());


				messageField.setText("");
			}







		}else if(evt.getSource() == btnView){
			boolean loggedIn = true;
			AllMessages mm = new AllMessages(data, loggedIn);
			mm.setVisible(true);
			this.setVisible(false);
		}else if(evt.getSource() == btnSubscribe){	//subscribe button hit
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
		}else if(evt.getSource() == btnSearchHash){	//search hashtag button hit
			HashSearch mm = new HashSearch(data, hashSearch.getText());
			mm.setVisible(true);
			this.setVisible(false);
		}else if(evt.getSource() == btnPrivMessage){ //view private messages hit
			boolean loggedIn = true;
			PrivateMessage mm = new PrivateMessage(data, loggedIn);
			mm.setVisible(true);
			this.setVisible(false);
		}else if(evt.getSource() == btnBrowse){	//browse button hit
			
			BrowseUsers mm = new BrowseUsers(data);
			mm.setVisible(true);
			this.setVisible(false);
			
			
		}
	}
}