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
 * @author ����
 * @see jdbc���������ࣨ�汾��֧��mysql��
 * @version 0.9 
 * @date time 2020-4-5 -  ����06:31:03
 *
 */
public class DbUtils {
	
	//1.��ʼ�����ӵ�
	
	/*���Ӷ���*/
	private static Connection conn = null;
	/*��̬������*/
	private Statement stmt = null;
	/*��̬������*/
	private PreparedStatement pstmt = null;
	/*���������*/
	private ResultSet rs = null;
	/*mysql���ݿ�����*/
	private static String mysqlName = "hp_manage";
	/*mysql���ӵ�ַ*/
	private static String url = "jdbc:mysql://localhost:3308/hp_manage";//ʹ��ǰ���ȸ������ݿ�����
	/*mysql �û���*/
	private static String user = "root";
	/*mysql ����*/
	private static String password = "mysql";
	
	
	
	//���췽����ʼ��
	public DbUtils(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("�Բ��������������Ƿ������������ַ�Ƿ�д��!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�Բ�������url���û����������Ƿ��д�!");
		}
		
	}
	
//	//��̬������ʼ��
//	static{
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(url, user, password);
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//			System.out.println("�Բ��������������Ƿ������������ַ�Ƿ�д��!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("�Բ�������url���û����������Ƿ��д�!");
//		}
//		System.out.println("���ӳɹ�!");
//	}
	
	
	/**
	 * @see  ��̬�ĸ��·���ֻ֧�� insert update delete
	 * @time ����06:52:33
	 * @param sql insert update delete
	 * @return Ӱ������ ֻҪ����0��ʾ�ɹ�
	 * int
	 */
	public int update(String sql){
		int result = 0;
		try {
			if(!sql.startsWith("select")){
				stmt = conn.createStatement();//��̬��������
				result = stmt.executeUpdate(sql);
			}else{
				System.out.println("�Բ��𱾷�����֧�ֲ�ѯ���");
			}
		} catch (SQLException e) {
			System.out.println("�Բ�������sql�����﷨�������Ƿ��ʼ��");
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @see  ��̬�Ĳ�ѯ���ݵķ���
	 * @time ����06:58:42
	 * @param sql select
	 * @return rs �����
	 * ResultSet
	 */
	public ResultSet query(String sql){
		try {
			stmt = conn.createStatement();//��̬��������
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("�Բ�������sql�����﷨�������Ƿ��ʼ��");
			e.printStackTrace();
		}
		return rs;
		
	}
	
	/**
	 * @see  ��̬�ĸ������ݵķ���
	 * @time ����07:06:36
	 * @param sql insert update delete
	 * @param obj ��?�Ÿ�ֵ�Ĳ���
	 * @return Ӱ������
	 * int
	 */
	public int updatePre(String sql,Object... obj){
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql); //Ԥ����
			for(int i = 0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);   //��ֵ
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultSet queryPre(String sql,Object... obj){
		try {
			pstmt = conn.prepareStatement(sql); //Ԥ����
			for(int i = 0; i<obj.length; i++){
				pstmt.setObject(i+1, obj[i]);   //��ֵ
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * @see  ��̬���Ĳ�ѯ���淽��
	 * @time ����08:18:38
	 * @param sql select
	 * @return rsת����ɵ��ڴ洦����
	 * ArrayList<HashMap<String,Object>> java�����ݿ��в�ѯ���������ݾͿ������ڴ��л�ȡ��
	 */
	public ArrayList<HashMap<String,Object>> queryForList(String sql){
		//list ��װ����rs
		//hashMap ��װrs�е�һ��   key == columnName  value == columnValue
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		try {
			stmt = conn.createStatement();//��̬��������
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			//rs�����й���
			//rs�е�һ���ж�����
			//rs�е�ÿ�е�����
			//rs�е�ÿ�е���������
			while(rs.next()){
				HashMap<String,Object> map = new HashMap<String,Object>();
				for(int i = 1; i<=rsmd.getColumnCount(); i++){
					String columnName = rsmd.getColumnLabel(i);
					Object columnValue = rs.getObject(columnName);
					map.put(columnName, columnValue);  //columnValue = map.get(columnName)
				}
				//�û�����
				list.add(map);
			}
		} catch (SQLException e) {
			System.out.println("�Բ�������sql�����﷨�������Ƿ��ʼ��");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * @see  �ر�jdbc�����ж����ͷ�jdbc����Դ�黹�ڴ�
	 * @time ����07:11:48
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
