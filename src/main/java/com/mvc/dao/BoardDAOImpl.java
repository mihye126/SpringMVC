package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.vo.Board;

//BoardDAO의 메소드를 구현한 객체.
//Service에서 들어온 요청을 최종적으로 처리하는 객체
//DB 작업 수행(CRUD)
//@Repository: 컨테이너가 생성하는 객체 중에 db 작업하는 객체

@Repository
public class BoardDAOImpl implements BoardDAO {
	
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
	public ArrayList<Board> selectAll() throws SQLException{
		ArrayList<Board> list = new ArrayList<Board>();
	
			Connection con = getConnection();// pool에서 한개 빌려옴
			Statement stat = con.createStatement();
			String q = "select num, name, wdate, title, count from board order by num desc";
			ResultSet rs = stat.executeQuery(q);

			while (rs.next()) {
				String num = rs.getString(1);
				String name = rs.getString(2);
				String wdate = rs.getString(3);
				String title = rs.getString(4);
				String count = rs.getString(5);

				Board b = new Board(num, null, name, wdate, title, null, count);
				list.add(b);
			}
			con.close();// pool에 반납
		
		return list;// ->service로 감
	}

	@Override
	public Board selectOne(String num)throws SQLException {
		Board b = null;
			String q = "select * from board where num = ?";
			Connection con = getConnection();// pool에서 한개 빌려옴
			PreparedStatement stat = con.prepareStatement(q);
			stat.setString(1, num);// ?에 setting

			ResultSet rs = stat.executeQuery();// 실행
			rs.next();
			
			String pass = rs.getString(2);
			String name = rs.getString(3);
			String wdate = rs.getString(4);
			String title = rs.getString(5);
			String content = rs.getString(6);
			String count = rs.getString(7);

			b = new Board(num, pass, name, wdate, title, content, count);
			con.close();
		
		return b;
	}

	@Override
	public void insert(Board b) throws SQLException{
		
			Connection con = getConnection();
			String q = "insert into BOARD(PASS,NAME,WDATE,TITLE,CONTENT,COUNT) values(?,?,sysdate(),?,?,0)";

			PreparedStatement stat = con.prepareStatement(q);
			stat.setString(1, b.getPass());
			stat.setString(2, b.getName());
			stat.setString(3, b.getTitle());
			stat.setString(4, b.getContent());

			stat.executeUpdate();
			con.close();		
	}

	@Override
	public void delete(String num) throws SQLException{
		
			String q = "delete from board where num = ?";
			Connection con = getConnection();// pool에서 한개 빌려옴
			PreparedStatement stat = con.prepareStatement(q);
			stat.setString(1, num);// ?에 setting

			stat.executeUpdate();
			con.close();		
	}

	@Override
	public void countUp(String num) throws SQLException{
		
			String q = "update board set count = count + 1 where num = ?";
			Connection con = getConnection();// pool에서 한개 빌려옴
			PreparedStatement stat = con.prepareStatement(q);
			stat.setString(1, num);// ?에 setting

			stat.executeUpdate();// 실행
			con.close();	
	}

	@Override
	public void modify(Board b) throws Exception {
		Connection con = getConnection();
		String q = "update BOARD set title =? , pass = ?, content = ? where num = ?";
		//System.out.println(b);
		PreparedStatement stat = con.prepareStatement(q);
		stat.setString(1, b.getTitle());
		stat.setString(2, b.getPass());
		stat.setString(3, b.getContent());
		stat.setString(4, b.getNum());

		stat.executeUpdate();
		con.close();	
		
	}

}
