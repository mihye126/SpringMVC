package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.vo.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	DataSource ds;
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}


	@Override
	public boolean check(User user) throws SQLException{
		boolean flag = false;
	
			Connection con = getConnection();//pool에서 한개 빌려옴
			String q = "select id from user where id = ? and pw = ?";
			PreparedStatement stat = con.prepareStatement(q);
			stat.setString(1, user.getId());
			stat.setString(2, user.getPw());
			
			ResultSet rs = stat.executeQuery();			
			
			if(rs.next()) flag = true;
			con.close();//pool에 반납
		
		return flag;
	}

}






