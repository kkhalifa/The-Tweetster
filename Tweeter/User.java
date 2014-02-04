
public class User {
	private String dateOfBirth;
	private String password;
	private String email;
	private String username;
	private String information;
	
	public String[] subTo = new String[150];
	
	User(){
		//do nothing
	}
	
	User(String dob, String pw, String em, String user){
		//set variable to values passed
		dateOfBirth=dob;
		password=pw;
		email = em;
		username=user;
		subTo[0]="@"+user;
		//information=info;
	}
	
	User(String dob, String pw, String em, String user,String info,String subbed){
		//set variable to values passed
		dateOfBirth=dob;
		password=pw;
		email = em;
		username=user;
		information=info;
		subTo[0]="@"+user;
		subTo[1]=subbed;
	}
	
	
	//method to return the number of users that this user is subscribed to
	public int getNumberOfSubscribers(){
		
		int counter=0;
		String temp=subTo[counter];
		
		while(!(temp==null)){
			counter++;
			temp=subTo[counter];
		}
		
		return counter;
	}
	
	
	//Getters
	public String getName(){
		return username;
	}
	
	

	
	public String[] getSub(){
		return subTo;
	}
	
	public String getPassword(){
		return password;
	}
	public String getEmail(){
		return email;
	}
	public String getDateOfBirth(){
		return dateOfBirth;
	}
	public String getInformation(){
		return information;
	}
	
	//Setters
	public void setName(String n){
		username =n;
	}
	
	public void setSub(String[] s){
		subTo=s;
	}
	
	
	
	
	public void setPassword(String p){
		password=p;
	}
	public void setEmail(String e){
		email=e;
	}
	public void setDateOfBirth(String d){
		dateOfBirth=d;
	}
	public void setInformation(String i){
		information=i;
	}
	
	
	
}
