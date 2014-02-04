import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;

public class HashSearch extends JFrame implements ActionListener{

	//making objects
	JPanel panel;
	JButton btnBackProf;
	JTextArea messages;
	DataBase data;

	public HashSearch(DataBase d, String hashSearch){

		super("Search Results For: "+hashSearch);
		data=d;
		panel = new JPanel();
		btnBackProf = new JButton("Back to profile");
		messages = new JTextArea();

		panel.setLayout(null);

		messages.setBounds(10, 100, 450, 250);
		btnBackProf.setBounds(10, 55, 150, 25);	


		btnBackProf.addActionListener(this);
		ImageIcon img = new ImageIcon("C:/Users/splunket/Desktop/Tweeter/bin/ernieFace.jpg");
		this.setIconImage(img.getImage());





		panel.add(btnBackProf);
		panel.add(messages);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().add(panel);

		setSize(500,400);
		setLocation(250,350);
		setVisible(true);




		//appends all of the returned tweets with the given hash search
		for(int i=data.countLines(hashSearch+".txt")-1;i>-1;i--){
			messages.append(data.displayHash(hashSearch,i)+"\n");
			
		}
		if(data.countLines(hashSearch+".txt")==0){
			messages.append("No results were found.");
		}



	}
	public void actionPerformed(ActionEvent evt){

		if(evt.getSource() == btnBackProf){	//back to profile button hurt
			MainMenu mm = new MainMenu(data);
			mm.setVisible(true);
			this.setVisible(false);
		}
	}
}