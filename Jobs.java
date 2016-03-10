
public class Jobs {
	//Private variables
	private int jobID = 0;
	private String custID;
	private int containerAmt;
	private String containerType;
	
	//Constructor
	public Jobs(String custID, int containerAmt, String containerType) {
		this.custID = custID;
		this.containerAmt = containerAmt;
		this.containerType = containerType;
		this.jobID += 1;
	}

	//Getters & Setters
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getCustID() {
		return custID;
	}
	public void setCustID(String custID) {
		this.custID = custID;
	}

	public int getContainerAmt() {
		return containerAmt;
	}
	public void setContainerAmt(int containerAmt) {
		this.containerAmt = containerAmt;
	}

	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
		
}
