package df.zafu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoryBean {

	/**
	 * ��ȡȫ��
	 * @return
	 */
	public  List<StoryInfo > getAll(){
		List<StoryInfo> data = 
				new ArrayList<StoryInfo>();
		Connection conn = a.getConnection();// ȡ������ 
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
			ResultSet rs = st.executeQuery(  );// ִ�в�ѯ���
			System.out.println( "��ѯ���Ϊ��" );
			
			while( rs.next() ){
				
				// hid
				int storyid = rs.getInt( "storyid" );
				// ����
				String storyplace = rs.getString( "storyplace" );
				// ����
				String storytitle = 
						rs.getString("storytitle");
				// ʡ
				String storycontent = rs.getString("storycontent");
				// ��
				String storyimg = rs.getString("storyimg");
				// ����
				int authorid= rs.getInt("authorid");
				// γ��
				String authorimg = rs.getString("authorimg");
				//��ס����
				String authorname = rs.getString("authorname");
				// ����			
				StoryInfo storyinfo = new StoryInfo(storyid, storytitle, storyplace, storycontent, storyimg,
						 authorid, authorimg, authorname);
				
				data.add(storyinfo);
			
				System.out.printf( storyinfo.toString() );
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
