package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class jdbc {
	private static BasicDataSource dataSource;
	static {
		// ��ȡ��ȡ�����ļ��Ķ���
		Properties pro = new Properties();
		InputStream is = jdbc.class.getClassLoader().getResourceAsStream("jdbc.properties");// Ĭ��·��src/main/resources
		try {
			// ����properties�����ļ�
			pro.load(is);
			// ��ȡ�����ļ�������
			String URL = pro.getProperty("url");
			String Driver = pro.getProperty("Driver");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			int InitialSize = Integer.parseInt(pro.getProperty("InitialSize"));
			int MaxActive = Integer.parseInt(pro.getProperty("MaxActive"));
			// �������ݿ����ӳ�Դ����
			dataSource = new BasicDataSource();
			// �������ݿ����ӳ������Ϣ
			dataSource.setDriverClassName(Driver);
			dataSource.setUrl(URL);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(InitialSize);// �������ݿ����ӳس�ʼ��������
			dataSource.setMaxActive(MaxActive);// �������ݿ����ӳ������������
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//�����ȡ����
	public static Connection getconnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//�ͷ���Դ
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stat!=null) {
				stat.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*
		 * 1��ע������ 2����ȡ���Ӷ��� 3������sqlִ�ж��� 4��ִ��sql 5����Դ�ͷ�
		 * 
		 * try { //ע������ Class.forName("com.mysql.cj.jdbc.Driver"); //��ȡ���Ӷ���
		 * //jdbc:mysql://localhost:3306/newdb3���ݿ�����ӵ�ַ�Ͷ�Ӧ�����ݿ���newdb3 Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb3", "root",
		 * "123456"); //����sqlִ�ж��� Statement stat = conn.createStatement(); String sql =
		 * ""; //ִ��sql stat.execute(sql);//��ɾ�Ĳ鶼���� stat.executeQuery(sql);//��ѯ
		 * stat.executeUpdate(sql);//��ɾ�� //���ղ�ѯ������ ResultSet rs =
		 * stat.executeQuery(sql); while(rs.next()) { //ʹ���ֶ����������ȡ���� String id =
		 * rs.getString(1); String name = rs.getString(2); �ֶ�����ȡ String name =
		 * rs.getString("ename"); double sal = rs.getDouble("sal");
		 * 
		 * } } catch (Exception e) { e.printStackTrace(); }
		 */
	}
}
