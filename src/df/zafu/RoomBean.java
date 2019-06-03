package df.zafu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RoomBean {
	
	/**
	 * ��ȡȫ��
	 * @return 
	 */
	public  List< RoomInfo > getAll(){
		
		List< RoomInfo > data = 
				new ArrayList< RoomInfo >();
		
		
		Connection conn = a.getConnection();// ȡ������ 
		try{
			
			String sql = "select account_id, "
					+ "account_name, account_email, "
					+ "account_phone, account_password, account_image"
					+ " from accounts";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql );
			
			
			
			ResultSet rs = st.executeQuery(  );// ִ�в�ѯ���
			System.out.println( "��ѯ���Ϊ��" );
			
			while( rs.next() ){
				
				// id
				int id = rs.getInt( "account_id" );// id
				// ����
				String name = rs.getString( "account_name" );
				// �ʼ�
				String email = 
						rs.getString("account_email");
				// �ֻ���
				String phone = rs.getString("account_phone");
				// ����
				String password = rs.getString("account_password");
				//ͷ��
				String image = rs.getString("account_image");
				
				RoomInfo info = new RoomInfo(id, name, email, phone, password,image);
				data.add( info );
				
				System.out.printf( info.toString() );
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


	
	
	/**
	 * ����
	 * @param info
	 * @return
	 */
	public boolean add( RoomInfo info ){
		
		// ��ȡ����
		Connection conn = a.getConnection();
		int count = -1;
		
		try{
			String sql = " insert accounts "
					+ "  (account_id, account_name, account_email,account_phone, account_password, account_image) "
					+ "  values(?, ?, ?, ?, ?, ?) ";  
			PreparedStatement st = conn.prepareStatement(  sql );
			int i = 1;
			
			//  name
			st.setString( i, info.getAccount_name() );
			++i;
			//  id
			st.setInt(i, info.getAccount_id());
			++i;
			//  email 
			st.setString( i, info.getAccount_email());
			++i;
			//  phone
			st.setString( i, info.getAccount_phone());
			++i;
			//password
			st.setString(i, info.getAccount_password());
			++i;
			//image
			st.setString(i, info.getAccount_image());
			++i;
		
			
			System.out.printf("sql = %s\n", st.toString());
			
			count = st.executeUpdate(  );// ִ�����
			System.out.printf( "����%d����¼", count );
		}
		catch( SQLException e ){
			System.out.printf( "����ʧ��:" + e.getMessage() );
			return false;
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
		
		return true;
	}

}


