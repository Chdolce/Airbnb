package df.zafu;

public class ApartmentsInfo {

	private int hid;//house主键
	private String title;//house 标题
	private String country;//国家
	private String province;//省
	private String city;//城市
	private double latitude;//经度
	private double longtitude;//纬度
	private String type;//类型
	private int guests_count;//可住容量
	private String info;//详情信息
	private int refer_price;//价格
	private String picture;//图片
	
	
	
	
	public ApartmentsInfo(int hid, String title, String country, String province, String city, double latitude,
			double longtitude, String type, int guests_count, String info, int refer_price, String picture) {
		super();
		this.hid = hid;
		this.title = title;
		this.country = country;
		this.province = province;
		this.city = city;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.type = type;
		this.guests_count = guests_count;
		this.info = info;
		this.refer_price = refer_price;
		this.picture = picture;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ApartmentsInfo() {
		super();
	}

	
	public int getHouse_id() {
		return hid;
	}

	public void setHouse_id(int house_id) {
		this.hid = house_id;
	}

	public String getHouse_title() {
		return title;
	}

	public void setHouse_title(String house_title) {
		this.title = house_title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGuests_count() {
		return guests_count;
	}

	public void setGuests_count(int guests_count) {
		this.guests_count = guests_count;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getRefer_price() {
		return refer_price;
	}

	public void setRefer_price(int refer_price) {
		this.refer_price = refer_price;
	}
	
	
	
}
