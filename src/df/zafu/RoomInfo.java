package df.zafu;


public class RoomInfo {
	
	public RoomInfo(int account_id, String account_name, String account_email, String account_phone,
			String account_password, String account_image) {
		super();
		this.account_id = account_id;
		this.account_name = account_name;
		this.account_email = account_email;
		this.account_phone = account_phone;
		this.account_password = account_password;
		this.account_image = account_image; 
	}

	
	
	
	
	private int account_id;
	private String account_name;
	private String account_email;
	private String account_phone;
	private String account_password;
	private String account_image;
	
	@Override
	public String toString() {
		return "RoomInfo[_id=" + account_id + ",_name" +account_name
				+",_email"+account_email+",_phone"+account_phone+
				",_password"+account_password+",_image" + account_image +"]";
	}
	public RoomInfo() {
		
	}
	
	public String getAccount_image() {
		return account_image;
	}
	
	public void setAccount_image(String account_image) {
		this.account_image = account_image;
	}
	
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	public String getAccount_phone() {
		return account_phone;
	}
	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	
	

	
}
