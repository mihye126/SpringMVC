package com.mvc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.BoardDAO;
import com.mvc.dao.BoardDAOImpl;
import com.mvc.vo.Board;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

//container에 의해 생성 되어서 controller에 주입되어야 함
//@Component라고 써도 가능. @Service라고 붙여서 생성해야되는 컴포넌트 중에 서비스 객체임을 표시

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO dao;
	
	@Override
	public ArrayList<Board> selectAll() throws Exception{		
		return dao.selectAll();
	}

	@Override
	public Board selectOne(String num)throws Exception {
		dao.countUp(num);
		Board b = dao.selectOne(num);
		return b;
	}

	@Override
	public void insert(Board b) throws Exception{
		dao.insert(b);		
	}

	@Override
	public void delete(String num) throws Exception{
		dao.delete(num);
	}
	
	@ExceptionHandler(MysqlDataTruncation.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView mnv = new ModelAndView();
		System.out.println(ex.getClass().getName());
		// if (e instanceof ArithmeticException) { }
		mnv.addObject("msg", "do well!!!3333");
		mnv.setViewName("error/error");
		return mnv;
	}

	@Override
	public void modify(Board b) throws Exception {
		dao.modify(b);
		
	}

}
