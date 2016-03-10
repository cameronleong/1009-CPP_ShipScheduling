
public class Customer {
	//Private variables
	private String custID;
	private String name;
	private int age;
	private String company;
	
	//Constructor
	public Customer(String custID, String name, int age, String company){
		this.custID = custID;
		this.name = name;
		this.age = age;
		this.company = company;
	}
	
	
	//Getter & Setter
	public String getID() {
		return custID;
	}
	public void setID(String custID) {
		this.custID = custID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void updateThis(){
		this.age = 1;
	}
	
	
}
