package df.zafu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RoomBean {
	
	/**
	 * 读取全部
	 * @return 
	 */
	public  List< RoomInfo > getAll(){
		
		List< RoomInfo > data = 
				new ArrayList< RoomInfo >();
		
		
		Connection conn = a.getConnection();// 取得连接 
		try{
			
			String sql = "select account_id, "
					+ "account_name, account_email, "
					+ "account_phone, account_password, account_image"
					+ " from accounts";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql );
			
			
			
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				
				// id
				int id = rs.getInt( "account_id" );// id
				// 名称
				String name = rs.getString( "account_name" );
				// 邮件
				String email = 
						rs.getString("account_email");
				// 手机号
				String phone = rs.getString("account_phone");
				// 密码
				String password = rs.getString("account_password");
				//头像
				String image = rs.getString("account_image");
				
				RoomInfo info = new RoomInfo(id, name, email, phone, password,image);
				data.add( info );
				
				System.out.printf( info.toString() );
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


	
	
	/**
	 * 新增
	 * @param info
	 * @return
	 */
	public boolean add( RoomInfo info ){
		
		// 获取连接
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
			
			count = st.executeUpdate(  );// 执行语句
			System.out.printf( "新增%d条记录", count );
		}
		catch( SQLException e ){
			System.out.printf( "新增失败:" + e.getMessage() );
			return false;
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
		
		return true;
	}

}


