package com.mvc.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.service.BoardService;
import com.mvc.vo.Board;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

@Controller
public class BoardController {

    @Autowired
    BoardService service;
    private final Logger logger = LoggerFactory.getLogger(BoardController.class);

    // HandlerMapping(요청을 처리할 컨트롤러 결정하는 객체)-->@RequestMapping로 대신함
    @RequestMapping(value = {"/list", "/"}, method = RequestMethod.GET)
    public String selectAll(Model model) throws Exception {// Map(key, value)
        System.out.println("list...");
        List<Board> list = service.selectAll();

        model.addAttribute("list", list);
        return "list";// view name
    }

    @GetMapping(value = "/read")
    public String read(Model model, @RequestParam("num") String num) throws Exception {
        //int a = 2/0;
        Board b = service.selectOne(num);

        model.addAttribute("b", b);
        return "read";// view name
    }

    @GetMapping(value = "/modify")
    public String modify(Model model, String num) throws Exception {
        Board b = service.selectOne(num);
        model.addAttribute("b", b);
        return "modify";// view name
    }

    @PostMapping(value = "/modifyProcess")
    public String modifyProcess(Board b) throws Exception {
        service.modify(b);
        return "redirect:/list";// view name
    }

    @GetMapping(value = "/insertForm") // 입력화면
    public String insertForm() {
        return "insertForm";// view name
    }

    // @ModelAttribute: 클라이언트가 입력한 값을 객체(vo)로 받아오기 위한 annotation
    @PostMapping(value = "/insertProcess") // db에 입력
    public String insertProcess(@ModelAttribute Board b)
        throws Exception {// vo. 사용자가 입력한 값 4개를 받아옴.입력화면의 칸이름과 vo의 필드명이 일치해야 함.

        service.insert(b);
        return "redirect:/list";// redirect
    }

    @GetMapping(value = "/delete")
    public String delete(Model model, String num) throws Exception {

        service.delete(num);
        return "redirect:/list";// view name
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView mnv = new ModelAndView();
        System.out.println(ex.getClass().getName());
        // if (e instanceof ArithmeticException) { }
        mnv.addObject("msg", "do well!!!");
        mnv.setViewName("error/error");
        return mnv;
    }
	
	/*
	 *
	 * @ExceptionHandler 는 @Controller , @RestController 가 적용된 Bean 에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능이다.
	 * @ExceptionHandler 에 설정한 예외가 발생하면 handler가 실행된다.
	 * @Controller, @RestController가 아닌 @Service 나 @Repository 가 적용된 Bean에서는 사용할 수 없다.
	 *
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mnv = new ModelAndView();
		// 해커 공격의 빌미를 제공할 수 있음으로 서버에서 발생한 구체적인 오류를 표시하지는 않는다.
		System.out.println(e.getClass().getName());
		// if (e instanceof ArithmeticException) { }
		mnv.addObject("errmsg", "do well!!!");
		// 예외가 발생되면 /error/err을 호출한다.
		mnv.setViewName("error/error");
		return mnv;
	}

	 */

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(Board board, @RequestParam("file") MultipartFile[] files)
	{
        String realPath = servletContext.getRealPath("/upload");
        String today = new SimpleDateFormat("yyMMdd").format(new Date()) ;
		String saveFolder = realPath + File.separator + today;
        File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
        List<FileInfoDto> fileInfos = new ArrayList‹FileInfoDto > ();
        for (MultipartFile mfile : files) {
            FileInfoDto fileInfoDto = new FileInfoDto();
            String originalFileName = mfile.getOriginalFilename();
            if (!originalfileName.isEmpty()) {
                String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(
                    originalFileName.lastIndexof('."));
                        fileInfoDto.setSaveFolder(today);
                fileInfoDto.setOriginFile(originalFileName);
                fileInfoDto.setSaveFile(saveFileName);
                " System. out.println(mfile.getOriginalFilename() + mfile.transferTo(newFile(folder,saveFileName));
                fileInfos.add
                    (fileInfoDto);
            } boardDto.setFileInfos(fileInfos);
            boardDto.setUserid(memberDto.getUserid());
            try {
                boardService.writeArticle(guestBookDto);
                return "board/writesuccess";
            } catch (Exception e) {
                " + saveFileName);
                e.printStackTrace();
                model.addAttribute("msg", "글작성중문제가발생했습니다.");
                return "error/error";
            }
        }else{
            model.addAttribute("msg", "로그인후사용가능한페이지입니다.");
            return "error/error"
            y
        }
