package df.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoryBean {

	/**
	 * 读取全部
	 * @return
	 */
	public  List<StoryInfo > getAll(){
		List<StoryInfo> data = 
				new ArrayList<StoryInfo>();
		Connection conn = a.getConnection();// 取得连接 
		try{
			
			String sql = "select storyid," + 
					"  storyplace, "+ 
					"  storytitle," + 
					"  storycontent," + 
					"  storyimg," + 
					"  authorid, " + 
					"  authorimg, " + 
					"  authorname"
					+ " from storys";
			java.sql.PreparedStatement st = 
					conn.prepareStatement( sql );
			ResultSet rs = st.executeQuery(  );// 执行查询语句
			System.out.println( "查询结果为：" );
			
			while( rs.next() ){
				
				// hid
				int storyid = rs.getInt( "storyid" );
				// 标题
				String storyplace = rs.getString( "storyplace" );
				// 国家
				String storytitle = 
						rs.getString("storytitle");
				// 省
				String storycontent = rs.getString("storycontent");
				// 市
				String storyimg = rs.getString("storyimg");
				// 经度
				int authorid= rs.getInt("authorid");
				// 纬度
				String authorimg = rs.getString("authorimg");
				//可住容量
				String authorname = rs.getString("authorname");
				// 类型			
				StoryInfo storyinfo = new StoryInfo(storyid, storytitle, storyplace, storycontent, storyimg,
						 authorid, authorimg, authorname);
				
				data.add(storyinfo);
			
				System.out.printf( storyinfo.toString() );
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
