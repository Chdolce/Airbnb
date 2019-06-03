package df.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRegister {
	public int register(String account_id, String account_name, String account_phone, String account_email, String account_password, String account_image) {
		Connection conn = a.getConnection();
		int flag =0;
		try {
			String sql = "select * from accounts where account_id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, account_id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {//账号id已存在
				flag = 2;
			}
			else {
				String sql1 = "Insert into accounts values('" + account_id +"','" + 
					account_name+"','" + account_phone + "','" +
						account_email + "','" + account_password +"','" + account_image + "')";
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
