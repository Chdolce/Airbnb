package df.zafu;

import java.sql.Connection;
import java.sql.DriverManager;


public class a {
	
    public static Connection getConnection(){
    	
    	Connection con = null;
    	try{
    		Class.forName( "com.mysql.jdbc.Driver" );
    	    con = (Connection) DriverManager.getConnection( 
    	    	"jdbc:mysql://localhost:3306/airbnb"
    	    	+ "?useUnicode=true&characterEncoding=UTF-8",
				"root", 
				"325800" ); 
    	}
    	catch( Exception e ){
    		System.out.printf( "数据库连接失败" );
    	}
    	
    	return con;
    }
    

    


}
