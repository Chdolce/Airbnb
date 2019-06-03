package df.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReserveBean {
	public int reserve(String guest_id, String house_name, String start_time, String finish_time) {
		Connection conn = a.getConnection();
		int flag =0;
		try {
			String sql = "select * from accountorder where guest_id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, guest_id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {//账号id已存在
				flag = 1;
			}
			else {
				String sql1 = "Insert into accountorder values('" + guest_id +"','" + 
					house_name+"','" + start_time + "','" +
						finish_time + "')";
				PreparedStatement st1 = conn.prepareStatement(sql1);
				int rs1 = st1.executeUpdate();
				if(rs1 == 1)
					flag = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("数据库查询失败！");
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch (SQLException e) {
					System.out.println("数据库关闭失败！");
				}
			}
		}
		return flag;
	}
}
