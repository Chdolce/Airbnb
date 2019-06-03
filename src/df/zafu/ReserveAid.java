package df.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReserveAid {
	public int reserveaid(String account_name) {
		Connection conn = a.getConnection();
		int flag = 0;
		try {
			String sql = "select account_id from account where account_name = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, account_name);
			ResultSet rs = st.executeQuery();
			String id;
			if(rs.next()) {
				id = rs.getString("account_id");
				flag = 1;
				System.out.println("account_id="+ id);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("数据库查询失败");
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					System.out.println("数据库关闭失败！");
				}
			}
		}
		if(flag == 1)
			return 1;
		else
			return 0;
	}
}
