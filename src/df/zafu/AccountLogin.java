package df.zafu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountLogin {
	public int login(String account_email, String account_password) {
		Connection conn = a.getConnection();
		int flag = 0;
		try {
			String sql = "select account_password from accounts where account_email = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, account_email);
			ResultSet rs = st.executeQuery();
			String pass;
			System.out.println("account_email=" + account_email +", account_password="+account_password);
			if(rs.next()) {
				pass = rs.getString("account_password");
				if(pass.equals(account_password)) {
					flag = 1;
				}
				System.out.println("account_email=" + account_email + ",account_password=" + pass);
			}
		}catch(SQLException e) {
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
