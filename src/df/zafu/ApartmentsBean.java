package df.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsBean {

	/**
	 * ��ȡȫ��
	 * @return
	 */
	public  List< ApartmentsInfo > getAll(){
		
		List< ApartmentsInfo > data = 
				new ArrayList< ApartmentsInfo >();
		
		
		Connection conn = a.getConnection();// ȡ������ 
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
			
			
			
			ResultSet rs = st.executeQuery(  );// ִ�в�ѯ���
			System.out.println( "��ѯ���Ϊ��" );
			
			while( rs.next() ){
				
				// hid
				int hid = rs.getInt( "hid" );
				// ����
				String title = rs.getString( "title" );
				// ����
				String country = 
						rs.getString("country");
				// ʡ
				String province = rs.getString("province");
				// ��
				String city = rs.getString("city");
				// ����
				double latitude = rs.getDouble("latitude");
				// γ��
				double longtitude = rs.getDouble("longtitude");
				//��ס����
				int guests_count = rs.getInt("guests_count");
				// ����
				String type = rs.getString("type");
				//������Ϣ
				String info = rs.getString("info");
				//�۸�
				int refer_price = rs.getInt("refer_price");
				//ͼƬ
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
			System.out.printf( "���ݿ��ѯʧ��\n" + e.getMessage()  );
		}
		finally{
			if( conn != null ){
				try{
					conn.close();
				}
				catch( SQLException e ){
					System.out.printf( "�ر�����ʧ��\n" + e.getMessage()  );
				}// try
			}// if
			
		}// finally

		return data;
	}
}
