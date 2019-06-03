package df.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsBean {

	/**
	 * 读取全部
	 * @return
	 */
	public  List< ApartmentsInfo > getAll(){
		
		List< ApartmentsInfo > data = 
				new ArrayList< ApartmentsInfo >();
		
		
		Connection conn = a.getConnection();// 取得连接 
		try{
			
			String sql = "select hid, "
					+ "title, country, "
					+ "province, city,"
					+ "latitude, longtitude,"
					+ "type, guests_count,"
					+ "info, refer_price,"
					+ "picture"
					+ " from apartmentss";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql );
			
			
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				
				// hid
				int hid = rs.getInt( "hid" );
				// 标题
				String title = rs.getString( "title" );
				// 国家
				String country = 
						rs.getString("country");
				// 省
				String province = rs.getString("province");
				// 市
				String city = rs.getString("city");
				// 经度
				double latitude = rs.getDouble("latitude");
				// 纬度
				double longtitude = rs.getDouble("longtitude");
				//可住容量
				int guests_count = rs.getInt("guests_count");
				// 类型
				String type = rs.getString("type");
				//详情信息
				String info = rs.getString("info");
				//价格
				int refer_price = rs.getInt("refer_price");
				//图片
				String picture = rs.getString("picture");
				
//				RoomInfo info = new RoomInfo(id, name, email, phone, password);
//				data.add( info );
				
				ApartmentsInfo aprtinfo = new ApartmentsInfo(hid, title,  country, 
						province, city, latitude,  longtitude,
						type,  guests_count,  info,  refer_price, picture);
				
				data.add(aprtinfo);
			
				System.out.printf( aprtinfo.toString() );
			}
			
		}
		catch( SQLException e ){
			e.printStackTrace();
			System.out.printf( "数据库查询失败\n" + e.getMessage()  );
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "关闭连接失败\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally

		return data;
	}
}
