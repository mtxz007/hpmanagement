package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author 黄议
 * @see jdbc交互工具类（版本仅支持mysql）
 * @version 0.9 
 * @date time 2020-4-5 -  下午06:31:03
 *
 */
public class DbUtils {
	
	//1.初始化连接的
	
	/*连接对象*/
	private static Connection conn = null;
	/*静态语句对象*/
	private Statement stmt = null;
	/*动态语句对象*/
	private PreparedStatement pstmt = null;
	/*结果集对象*/
	private ResultSet rs = null;
	/*mysql数据库名称*/
	private static String mysqlName = "hp_manage";
	/*mysql连接地址*/
	private static String url = "jdbc:mysql://localhost:3308/hp_manage";//使用前请先更改数据库名称
	/*mysql 用户名*/
	private static String user = "root";
	/*mysql 密码*/
	private static String password = "mysql";
	
	
	
	//构造方法初始化
	public DbUtils(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("对不起请检查驱动类是否引入或驱动地址是否写错!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("对不起请检查url和用户名和密码是否有错!");
		}
		
	}
	
//	//静态代码块初始化
//	static{
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, user, password);
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//			System.out.println("对不起请检查驱动类是否引入或驱动地址是否写错!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("对不起请检查url和用户名和密码是否有错!");
//		}
//		System.out.println("连接成功!");
//	}
	
	
	/**
	 * @see  静态的更新方法只支持 insert update delete
	 * @time 下午06:52:33
	 * @param sql insert update delete
	 * @return 影响行数 只要不是0表示成功
	 * int
	 */
	public int update(String sql){
		int result = 0;
		try {
			if(!sql.startsWith("select")){
				stmt = conn.createStatement();//静态创建方法
				result = stmt.executeUpdate(sql);
			}else{
				System.out.println("对不起本方法不支持查询语句");
			}
		} catch (SQLException e) {
			System.out.println("对不起请检查sql语句的语法和连接是否初始化");
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @see  静态的查询数据的方法
	 * @time 下午06:58:42
	 * @param sql select
	 * @return rs 结果集
	 * ResultSet
	 */
	public ResultSet query(String sql){
		try {
			stmt = conn.createStatement();//静态创建方法
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("对不起请检查sql语句的语法和连接是否初始化");
			e.printStackTrace();
		}
		return rs;
		
	}
	
	/**
	 * @see  动态的更新数据的方法
	 * @time 下午07:06:36
	 * @param sql insert update delete
	 * @param obj 给?号赋值的参数
	 * @return 影响行数
	 * int
	 */
	public int updatePre(String sql,Object... obj){
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql); //预编译
			for(int i = 0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);   //赋值
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet queryPre(String sql,Object... obj){
		try {
			pstmt = conn.prepareStatement(sql); //预编译
			for(int i = 0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);   //赋值
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * @see  静态语句的查询缓存方法
	 * @time 下午08:18:38
	 * @param sql select
	 * @return rs转换完成的内存处理结果
	 * ArrayList<HashMap<String,Object>> java从数据库中查询出来的数据就可以在内存中获取了
	 */
	public ArrayList<HashMap<String,Object>> queryForList(String sql){
		//list 封装整个rs
		//hashMap 封装rs中的一行   key == columnName  value == columnValue
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		try {
			stmt = conn.createStatement();//静态创建方法
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			//rs能逐行滚动
			//rs中的一共有多少列
			//rs中的每列的名字
			//rs中的每列的数据类型
			while(rs.next()){
				HashMap<String,Object> map = new HashMap<String,Object>();
				for(int i = 1; i<=rsmd.getColumnCount(); i++){
					String columnName = rsmd.getColumnLabel(i);
					Object columnValue = rs.getObject(columnName);
					map.put(columnName, columnValue);  //columnValue = map.get(columnName)
				}
				//该换行了
				list.add(map);
			}
		} catch (SQLException e) {
			System.out.println("对不起请检查sql语句的语法和连接是否初始化");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * @see  关闭jdbc的所有对象释放jdbc的资源归还内存
	 * @time 下午07:11:48
	 * void
	 */
	public void close(){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
