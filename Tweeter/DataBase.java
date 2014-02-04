import java.io.*;


public class DataBase {


	//Current user in the database
	private User currentUser;
	private int linesToSkip=1;
	//private User tempUser;

	//constructors
	DataBase(User cur){
		String filename="Tweets.txt";
		currentUser = cur;
		File f=new File(filename);
		try{
			if(!f.exists()){
				f.createNewFile();
				//creates writer NOT in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,false));
				//inits first line
				output.write("THIS IS THE TWEETER");
				output.newLine();

				//close writer
				output.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	DataBase(){
		String filename="Tweets.txt";
		currentUser=new User();
		File f=new File(filename);
		try{
			if(!f.exists()){
				f.createNewFile();
				//creates writer NOT in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,false));
				//inits first line
				output.write("THIS IS THE TWEETER");
				output.newLine();

				//close writer
				output.close();

			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	//sets the current user with a user parameter
	public void setUser(User us){
		currentUser = us;
	}

	//sets the current user with a string parameter.~~~~~~~~~~~~~~~~~this will be used when an existing user logs in
	public void setUser(String username){

		String filename = username+".txt";
		File f = new File(filename);

		//System.out.println("file name is set to- "+filename);

		if(!f.exists()){
			//System.out.println("this phony is trying to log in and doesn't have an account, or my database sucks.");
			return;
		}
		try{
			//Reader to get info
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

			//sets the current user name to first line of user file
			currentUser.setName(br.readLine());

			//sets the current DOB to the second line of user file
			currentUser.setDateOfBirth(br.readLine());

			//sets the current email to third line of user file
			currentUser.setEmail(br.readLine());

			//sets the current password to fourth line of user file
			currentUser.setPassword(br.readLine());

			//sets the current information to fifth line of user file
			currentUser.setInformation(br.readLine());

			//PARSE UP 6TH LINE TO STORE IN SUBBED
			//String to store the 5th line (who the user is subscribed to)
			String temp;
			temp=br.readLine();
			if(!(temp==null)){
				String[] tempStringArray = new String[150];
				int tempIntForLocation;
				int prevLocation=0;
				int counter=0;
				tempIntForLocation = temp.indexOf(',');
				if(tempIntForLocation==-1){	//this means there is only one subscriber, because no comma was found
					tempStringArray[0]=temp;
				}else{
					while(tempIntForLocation!=-1){

						tempStringArray[counter]=temp.substring(prevLocation,tempIntForLocation);
						prevLocation = tempIntForLocation+1;
						tempIntForLocation = temp.indexOf(',',tempIntForLocation+1);
						counter++;
					}
					tempStringArray[counter]=temp.substring(prevLocation);
				}

				//set the pointer to the string array that contains who the user is subscribed to
				currentUser.setSub(tempStringArray);


			}else{
				//System.out.println("this user is not currently subscribed to anyone");
			}



			//closes reader
			br.close();



		}catch(IOException e){
			e.printStackTrace();
		}


	}


	public void printSubbed(){ //used mainly for testing the setUser(String s) function
		int counter=0;
		String temp = currentUser.getSub()[counter];
		while(temp!=null){
			System.out.println(temp);
			counter++;
			temp = currentUser.getSub()[counter];
		}
	}


	//function to be called when a user tries to log in.
	//returns true if inputted pw matches account file pw, otherwise returns false
	//also returns false if something with file reading goes wrong
	/*
	 * @param n - username entered
	 * @param pw - password entered
	 * 
	 */
	public boolean checkPassword(String n, String pw){ 
		String temp=null;

		//set up filename in format "username.txt"
		String filename = n+".txt";
		File f = new File(filename);
		if(!f.exists()){ //file does not exist
			//System.out.println("this phony is trying to log in and doesn't have an account, or my database sucks.");
			return false;
		}
		try{
			//Reader to get info
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));


			//reads down to 4th line, temp will be set to the password at the end of this
			for(int i=0;i<4;i++){
				temp = br.readLine();
			}

			//System.out.println("It's about to check the password using the entered one of: "+pw+" against the one from the file: "+temp);
			if(temp.equals(pw)){
				br.close();
				return true;
			}else{
				br.close();
				return false;
			}




		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}








	//returns the current user
	public User getUser(){
		return currentUser;
	}




	//returns the current user
	public String displayLineByLine(String filename,int counter){

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			//Value that will eventually hold the tweet string to be returned
			String returnVal;
			for(int i=0;i<counter;i++){
				returnVal=br.readLine();
			}
			//This is the username
			returnVal = br.readLine();

			br.close();
			return returnVal;

		}catch(IOException e){
			e.printStackTrace();
			return "Error------";
		}	



	}


	//Method to create a new user file, this will add all of the information that is gathered
	//during the registration process to one file.  The file will later be updated with
	//who the user subscribes to, on the fifth line.
	public void createUserFile() {

		String filename = currentUser.getName()+".txt";
		updateUserFile(currentUser.getName());
		File f=new File(filename);
		try{
			if(!f.exists()){
				f.createNewFile();
			}

			//creates writer NOT in append mode
			BufferedWriter output = new BufferedWriter(new FileWriter(filename,false));
			//write username on first line
			output.write(currentUser.getName());
			//write a new line
			output.newLine();
			//write date of birth on second line
			output.write(currentUser.getDateOfBirth());
			//write a new line
			output.newLine();
			//write email on third line
			output.write(currentUser.getEmail());
			//write a new line
			output.newLine();

			//write password on fourth line
			output.write(currentUser.getPassword());

			//write a new line
			output.newLine();

			//write a 0 for a number of tweets tracker
			output.newLine();


			//write subbed
			int counter=0;

			String[] i = currentUser.getSub();
			while(i[counter]!=null){
				output.write(i[counter]);
				counter++;

			}
			if(i[0]!=null){
				//write a new line
				output.newLine();

			}

			//close writer
			output.close();


		}catch(IOException e){
			e.printStackTrace();

		}
	}


	public void subscribedUpdate(String newSub){
		try{
			//int lineCount=1;
			String filename = currentUser.getName()+".txt";

			//FileInputStream fs= new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String temp[]=new String[6];
			//System.out.println("about to read down to line we want.");
			for(int i = 0; i < 6; ++i){
				//System.out.print("line number ");
				//System.out.println(lineCount);
				temp[i] = br.readLine();
				//System.out.println(temp);
				//lineCount++;
			}






			if(temp[5]==null){
				//This is the first account that the user has subscribed to

				//open writer in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));
				//System.out.println("about to append because line is empty");

				//Add the new subscription to a new line at the end of the file
				output.append(newSub);
				//close the writer
				output.close();
			}else{
				//The user has subscribed to other users before this

				//open writer with a new folder
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,false));

				//System.out.println("about to update because line is NOT empty");
				//System.out.println("temp[5] before is: "+temp[5]);
				temp[5]=temp[5]+","+newSub;
				//System.out.println("temp[5] after is: "+temp[5]);
				for(int i = 0; i < 6; ++i){
					//writes the saved line i
					output.write(temp[i]);
					output.newLine();

				}

				//System.out.println(temp+newSub);
				//output.write(temp+","+newSub);

				//closes the writer
				output.close();

			}


			br.close();

		}catch(IOException e){
			e.printStackTrace();
		}


	}




	public void tweet(String tweet){
		String filename = "Tweets.txt";
		//File f=new File(filename);

		try{
			//open writer in append mode
			BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));

			//Add the new subscription to a new line at the end of the file


			output.append("@"+getUser().getName());
			output.newLine();
			output.append(tweet);
			output.newLine();

			//close writer
			output.close();

		}catch(IOException e){
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param tweet - tweet to be checked for hashtags
	 * @return returns int showing the number of hashtags in the tweet
	 */
	public int hashTagChecker(String tweet){

		int hashLoc = tweet.indexOf('#');	//set to -1 if there are none
		//System.out.println("checking for hashtag");
		int counter = 0;
		while (hashLoc != -1){
			//System.out.println("there is a hashtag in this!!!!");
			hashLoc = tweet.indexOf('#',hashLoc+1);
			counter++;

		}

		return counter;
	}

	/**
	 * 
	 * @param tweet input tweet to be sent to 
	 * @param howManyDawg how many hashtags are in the tweet
	 */
	public void storeThisHash(String tweet){

		String tempHashHolder="hey";
		String filename;



		String[] tweetArray = tweet.split("\\s+");
		int counter2 = 0;
		boolean out = false;
		for(int i = 0; i<hashTagChecker(tweet);i++){


			out = false;
			while(!out){
				tempHashHolder = tweetArray[counter2];
				if (tempHashHolder.charAt(0)=='#'){
					out = true;
				}
				counter2++;
			}



			//System.out.println("tempHashHolder is set to <"+tempHashHolder+">");
			filename = tempHashHolder+".txt";
			File f=new File(filename);
			try{

				//If file doesn't exist, make it
				if(!f.exists()){
					//System.out.println("About to create for the file named <"+tempHashHolder+".txt>");
					f.createNewFile();
				}
				//open writer in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));

				//append the tweet to the text file
				output.append(tweet);
				//new line
				output.newLine();

				//close writer
				output.close();

			}catch(IOException e){
				e.printStackTrace();
			}



		}

	}


	public void directMessage(String toUser, User fromUser, String tweet){
		try{
			//int lineCount=1;
			String filename = "@" + toUser + ".txt";
			File f=new File(filename);
			if(!f.exists()){
				f.createNewFile();
				//open writer in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));
				output.append("THIS IS THE FIRST LINE");
				output.newLine();
				output.close();
			}
			//open writer in append mode
			BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));

			//Add the new subscription to a new line at the end of the file

			output.append("@"+fromUser.getName());
			output.newLine();
			output.append(tweet);
			output.newLine();

			//close writer
			output.close();




		}catch(IOException e){
			e.printStackTrace();
		}


	}


	public void updateUserFile(String user){
		try{
			//int lineCount=1;
			String filename = "Users.txt";
			File f=new File(filename);
			if(!f.exists()){
				f.createNewFile();
			}
			//open writer in append mode
			BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));

			output.append(user);
			output.newLine();

			//close writer
			output.close();




		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public String displayPrivateTweets(){
		String filename = "@" + currentUser.getName() + ".txt";
		File f=new File(filename);
		//System.out.println(filename);
		try{
			if(!f.exists()){
				f.createNewFile();
				//open writer in append mode
				BufferedWriter output = new BufferedWriter(new FileWriter(filename,true));
				output.append("THIS IS THE FIRST LINE");
				output.newLine();
				output.close();
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			//Value that will eventually hold the tweet string to be returned
			String returnVal;
			String temp;
			for(int i=0;i<linesToSkip;i++){
				returnVal=br.readLine();
			}
			//This gets the first line that we want, the username of the person tweeting.
			temp = br.readLine();
			if(temp==null){
				br.close();
				//System.out.println("about to return 'That's all the tweets for now!' and the temp is equal to "+temp);	
				return "That's all the tweets for now!";
			}
			//This gets the second line we want, the actual tweet from the user.
			returnVal=br.readLine();

			//Formatting to return as tweet "@user - this is my tweet sample #coolright"
			returnVal=temp+" - "+returnVal;

			linesToSkip=linesToSkip+2;
			br.close();
			return returnVal;

		}catch(IOException e){
			e.printStackTrace();
			return "Error------";
		}	
	}


	public int countLines(String filename){
		File f=new File(filename);
		int counter=0;
		try{

			//If file doesn't exist, make it
			if(!f.exists()){
				//Do nothing
				return 0;
			}

			//reader
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String temp = br.readLine();

			while(temp!=null){
				temp = br.readLine();
				counter++;
			}

			br.close();
			return counter;

		}catch(IOException e){

			e.printStackTrace();
			return -1;			
		}
	}


	/**
	 * 
	 * @param hashName the thing that is being searched
	 * @param startLine line to start reading from
	 */
	public String displayHash(String hashName, int startLine){
		String returnVal = "";
		//System.out.println("About to search for the file named <"+hashName+".txt>");
		try{
			//reader
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(hashName+".txt")));
			for(int i=0;i<startLine;i++){  //skips lines we have already read
				returnVal = br.readLine();
			}
			//the line we want
			returnVal = br.readLine();

			//close reader
			br.close();

			return returnVal;

		}catch(IOException e){
			e.printStackTrace();
			return "This did not work";
		}

	}



	/*
	 * Returns a string that is the current tweet properly formatted, this function should be called in a while(stringForReturnOfThis!=null){};
	 */
	public String displayAllTweets(){
		String filename = "Tweets.txt";
		File f=new File(filename);
		if(!f.exists()){
			System.out.println("THE FILE WAS NOT FOUND!!!!! THIS SUCKS.");
		}

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			//Value that will eventually hold the tweet string to be returned
			String returnVal;
			String temp;
			for(int i=0;i<linesToSkip;i++){
				returnVal=br.readLine();
			}
			//This gets the first line that we want, the username of the person tweeting.
			temp = br.readLine();
			if(temp==null){
				br.close();
				//System.out.println("about to return 'That's all the tweets for now!' and the temp is equal to "+temp);	
				return "That's all the tweets for now!";
			}
			//This gets the second line we want, the actual tweet from the user.
			returnVal=br.readLine();

			//Formatting to return as tweet "@user - this is my tweet sample #coolright"
			returnVal=temp+" - "+returnVal;

			linesToSkip=linesToSkip+2;
			br.close();
			return returnVal;

		}catch(IOException e){
			e.printStackTrace();
			return "Error------";
		}	
	}


	/*
	 * Returns a string that is the current tweet properly formatted, this function should be called in a while(stringForReturnOfThis!=null){};
	 */
	public String displaySubbedTweets(){
		String filename = "Tweets.txt";
		File f=new File(filename);
		if(!f.exists()){
			System.out.println("THE FILE WAS NOT FOUND!!!!! THIS SUCKS.");
		}

		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			//Value that will eventually hold the tweet string to be returned
			String returnVal;
			//boolean for knowing whether or not we have a valid return
			boolean gotReturn=false;
			String temp;

			for(int i=0;i<linesToSkip;i++){
				returnVal=br.readLine();
			}
			//This gets the first line that we want, the username of the person tweeting.
			temp =br.readLine();

			if(temp==null){
				br.close();
				return "That's all the tweets for now!";
			}

			String temp2;

			while(!gotReturn){
				//System.out.println("temp is equal to "+temp);
				//System.out.println("This is how many people the current user is subscribed to---> "+currentUser.getNumberOfSubscribers());

				for(int i=0;i<currentUser.getNumberOfSubscribers();i++){	//when there's a username/tweet
					temp2=currentUser.getSub()[i];
					//System.out.println("The current user is subscribed to "+temp2);
					//System.out.println("temp is equal to "+temp);

					if(temp.equals(temp2)){	//if the username of the person who tweeted is the same as the current username on the subscribed list
						//System.out.println("It says that they are subscribed to: "+ temp);
						gotReturn=true;
					}
				}

				if(!gotReturn){
					linesToSkip=linesToSkip+2;
					//this reads the tweet from a user that we dont care about
					temp=br.readLine();
					//System.out.println("temp is equal to "+temp);

					if(temp==null){
						br.close();
						return "That's all the tweets for now!";
					}

					//reads the next user, possibly one we are subscribed to
					temp=br.readLine();
					if(temp==null){
						br.close();
						return "That's all the tweets for now!";
					}
				}

			}

			//This gets the second line we want, the actual tweet from the user.
			returnVal=br.readLine();

			//Formatting to return as tweet "@user - this is my tweet sample #coolright"
			returnVal=temp+" - "+returnVal;

			linesToSkip=linesToSkip+2;
			br.close();
			return returnVal;

		}catch(IOException e){
			e.printStackTrace();
			return "Error------";
		}	
	}



	public void resetTweetCounter(){ //call after you have displayed all tweets
		linesToSkip=1;
	}

	public int countTweets(){
		return 1;
	}




}
