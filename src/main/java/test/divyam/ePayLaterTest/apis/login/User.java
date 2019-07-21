package test.divyam.ePayLaterTest.apis.login;

public class User {
	public User(int id, String name, String phoneNo) {
		super();
		this.id = id;
		Name = name;
		this.phoneNo = phoneNo;
	}
	private int id;
	private String Name;
	private String phoneNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
