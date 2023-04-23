package com.mvc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.vo.Board;


public interface BoardDAO {
	public ArrayList<Board> selectAll()throws SQLException;
	public Board selectOne(String num)throws SQLException;
	public void insert(Board b)throws SQLException;
	public void delete(String num)throws SQLException;
	public void countUp(String num)throws SQLException;
	public void modify(Board b)throws Exception;
}
