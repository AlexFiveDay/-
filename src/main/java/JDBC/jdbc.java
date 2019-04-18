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
		// 获取读取配置文件的对象
		Properties pro = new Properties();
		InputStream is = jdbc.class.getClassLoader().getResourceAsStream("jdbc.properties");// 默认路径src/main/resources
		try {
			// 加载properties配置文件
			pro.load(is);
			// 读取配置文件的数据
			String URL = pro.getProperty("url");
			String Driver = pro.getProperty("Driver");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			int InitialSize = Integer.parseInt(pro.getProperty("InitialSize"));
			int MaxActive = Integer.parseInt(pro.getProperty("MaxActive"));
			// 创建数据库连接池源对象
			dataSource = new BasicDataSource();
			// 设置数据库连接池相关信息
			dataSource.setDriverClassName(Driver);
			dataSource.setUrl(URL);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(InitialSize);// 设置数据库连接池初始连接数量
			dataSource.setMaxActive(MaxActive);// 设置数据库连接池最大连接数量
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//申请获取连接
	public static Connection getconnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//释放资源
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
		 * 1、注册驱动 2、获取连接对象 3、创建sql执行对象 4、执行sql 5、资源释放
		 * 
		 * try { //注册驱动 Class.forName("com.mysql.cj.jdbc.Driver"); //获取连接对象
		 * //jdbc:mysql://localhost:3306/newdb3数据库的连接地址和对应的数据库名newdb3 Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb3", "root",
		 * "123456"); //创建sql执行对象 Statement stat = conn.createStatement(); String sql =
		 * ""; //执行sql stat.execute(sql);//增删改查都可以 stat.executeQuery(sql);//查询
		 * stat.executeUpdate(sql);//增删改 //接收查询的数据 ResultSet rs =
		 * stat.executeQuery(sql); while(rs.next()) { //使用字段名的坐标获取数据 String id =
		 * rs.getString(1); String name = rs.getString(2); 字段名获取 String name =
		 * rs.getString("ename"); double sal = rs.getDouble("sal");
		 * 
		 * } } catch (Exception e) { e.printStackTrace(); }
		 */
	}
}
