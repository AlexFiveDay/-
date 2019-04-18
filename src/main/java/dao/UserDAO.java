package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.jdbc;
import entity.User;
import entity.Award;
/**
 * DAO类：
 * @author 杨大龙
 *
 */
public class UserDAO {
	/**
	 * 依据用户名查询该用户的所有信息,
	 * 如果找不到，返回null。
	 * @param username
	 * @throws SQLException 
	 */
	public User find(String username) throws SQLException {
		User user = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("select * from t_user where name=?");
			stat.setString(1, username);
			rs = stat.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setName(username);
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPrize_id(rs.getInt("price_num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(null, stat, conn);
		}
		return user;
	}
	/**
	 * 删除用户数据
	 * @param id
	 */
	public void delete(int id) {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("delete from t_user where id=?");
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			jdbc.close(null, stat, conn);
		}
	}
	/**
	 * 将用户信息添加至数据库中(t_user)中
	 * @throws SQLException 
	 */
	public void save(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("insert into t_user(id,name,passWord,email) values(null,?,?,?);");
			stat.setString(1, user.getName());
			stat.setString(2, user.getPassword());
			stat.setString(3, user.getEmail());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(null, stat, conn);
		}
	}
	/**
	 * 将用户表（t_user）中所有的用户信息都查询出来
	 * @return
	 * @throws SQLException 
	 */
	public List<User> findAll() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("select * from t_user");
			rs = stat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				int prize_id = rs.getInt(5);
				
				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setPassword(password);
				user.setEmail(email); 
				user.setPrize_id(prize_id);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(rs, stat, conn);
		}
		return users;
	}
	/*
	 * 修改游客中奖信息
	 */
	public void updateUser(String username,int price_num) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int amount = 0;
		try {
			conn = jdbc.getconnection();
			//更新用户中奖信息 update 表名 set name='张三',age=13 where id=5;
			stat = conn.prepareStatement("update t_user set price_num=? where name=?");
			stat.setInt(1, price_num);
			stat.setString(2, username);
			stat.executeUpdate();
			System.out.println("游客中奖信息保存成功");
			//查询所中奖品库存
			stat = conn.prepareStatement("select prize_amount from award where id=?");
			stat.setInt(1, price_num);
			rs = stat.executeQuery();
			if (rs.next()) {
				amount = rs.getInt("prize_amount");
			}
			//更新奖品表中的奖品剩余数量
			stat = conn.prepareStatement("update award set prize_amount=? where id=?");
			stat.setInt(1, amount-1);
			stat.setInt(2, price_num);
			stat.executeUpdate();
			System.out.println("奖品表中库存信息保存成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(null, stat, conn);
		}
	}
	/*
	 * 查询所有奖品数据
	 */
	public List<Award> findAwards() throws SQLException{
		List<Award> awards = new ArrayList<Award>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("select * from award");
			rs = stat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String prize_name = rs.getString(2);
				int prize_amount = rs.getInt(3);
				
				Award award = new Award();
				award.setId(id);
				award.setPrize_name(prize_name);
				award.setPrize_amount(prize_amount);
				awards.add(award);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(rs, stat, conn);
		}
		return awards;
	}
	/*
	 * 依据奖品名查找奖品信息
	 */
	public Award findAward(String prize_name) throws SQLException {
		Award award = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = jdbc.getconnection();
			stat = conn.prepareStatement("select * from award where prize_name=?");
			stat.setString(1, prize_name);
			rs = stat.executeQuery();
			if (rs.next()) {
				award = new Award();
				award.setId(rs.getInt("id"));
				award.setPrize_name(prize_name);
				award.setPrize_amount(Integer.parseInt(rs.getString("prize_amount")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(null, stat, conn);
		}
		return award;
	}
	
	/*
	 * 修改奖品信息，同时将游客中奖信息清零
	 */
	public void changeAward(String prize_name,int prize_amount,int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = jdbc.getconnection();
			//更新用户中奖信息,全部清零  update 表名 set name='张三',age=13 where id=5;
			stat = conn.prepareStatement("update t_user set price_num=0 where price_num!=0");
			stat.executeUpdate();
			System.out.println("游客中奖信息清零成功");
			//更新奖品表中的奖品信息
			stat = conn.prepareStatement("update award set prize_name=?,prize_amount=? where id=?");
			stat.setString(1, prize_name);
			stat.setInt(2, prize_amount);
			stat.setInt(3, id);
			stat.executeUpdate();
			System.out.println("奖品表中库存信息更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			jdbc.close(null, stat, conn);
		}
	}
}
