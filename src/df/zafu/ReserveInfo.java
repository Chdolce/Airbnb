package df.zafu;

public class ReserveInfo {
	public String guest_id;
	public String house_name;
	public String start_time;
	public String finish_time;
	
	
	
	public ReserveInfo() {}
	
	public ReserveInfo(String guest_id, String house_name, String start_time, String finish_time) {
		super();
		this.guest_id = guest_id;
		this.house_name = house_name;
		this.start_time = start_time;
		this.finish_time = finish_time;
	}

	
	public String getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(String guest_id) {
		this.guest_id = guest_id;
	}

	public String getHouse_name() {
		return house_name;
	}

	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(String finish_time) {
		this.finish_time = finish_time;
	}
	
}
