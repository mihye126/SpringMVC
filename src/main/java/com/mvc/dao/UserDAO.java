package com.mvc.dao;

import java.sql.SQLException;

import com.mvc.vo.User;

public interface UserDAO {

	boolean check(User user)throws SQLException;

}
