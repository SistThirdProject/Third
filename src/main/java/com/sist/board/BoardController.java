package com.sist.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.FileWriter;
import java.util.*;
@Controller
public class BoardController {
   @Autowired
   private BoardDAO dao;
   @RequestMapping("main/list.do")
   public String main_board_list(String page,Model model)
   {
	    if(page==null)
	    	page="1";
	    int curpage=Integer.parseInt(page);
	    List<BoardVO> list=dao.boardListData(curpage);
	    model.addAttribute("list", list);
	    return "board/list";
   }
   @RequestMapping("main/insert.do")
   public String main_insert()
   {
	   return "board/insert";
   }
   @RequestMapping("main/insert_ok.do")
   public String main_insert_ok(BoardVO vo)
   {
	     dao.boardInsert(vo);
	     return "redirect:board.do";//list.do로 이동
   }
   @RequestMapping("main/content.do")
   public String main_content(int no,Model model)
   {
	   BoardVO vo=dao.boardContentData(no);
	   model.addAttribute("vo", vo);
	   String data=dao.boardContent(vo.getName());
	   try
	   {
		   FileWriter fw=new FileWriter("/home/sist/data/board.txt");
		   fw.write(data);
		   fw.close();
		   
	   }catch(Exception ex){}
	   return "board/content";
   }
   //  JSP (reply.do) ==> @Controller (@RequestMapping("main/reply.do")) ==> 메소드를 수행 
   //   Model을 통해서 JSP로 값을 전송 
   @RequestMapping("main/reply.do")
   public String main_reply(int no,Model model)
   {
	   model.addAttribute("no", no);
	   return "board/reply";
   }
   @RequestMapping("main/reply_ok.do")
   public String main_reply_ok(int pno,BoardVO vo)
   {
	   dao.boardReply(pno, vo);
	   return "redirect:board.do";
   }
   @RequestMapping("main/update.do")
   public String main_update(int no,Model model)
   {
	   BoardVO vo=dao.boardUpdateData(no);
	   model.addAttribute("vo", vo);
	   return "board/update";
   }
   // @RestController  => 문자열 
   /*
    *   @Controller : forward,redirect
    *         jsp파일명, .do
    *     @RestController  @ResponseBody => HTML,JSON...  Ajax
    */
   @RequestMapping("main/update_ok.do")
   @ResponseBody
   public String main_update_ok(BoardVO vo)
   {
	   String data="<script>alert(\"Password Fail!!\");history.back();</script>";
	   boolean bCheck=dao.boardUpdateOk(vo);
	   if(bCheck==true)
	   {
		   data="<script>location.href=\"content.do?no="+vo.getNo()+"\";</script>";
	   }
	   return data;
   }
   @RequestMapping("main/delete.do")
   public String board_delete(int no,Model model)
   {
	   model.addAttribute("no", no);
	   return "board/delete";
   }
   @RequestMapping("main/delete_ok.do")
   @ResponseBody
   // JSON,XML,일반문자를 전송 
   public String board_delete_ok(int no,String pwd)
   {
	   
	   String data="<script>alert(\"Password Fail!!\");history.back();</script>";
	   boolean bCheck=dao.boardDelete(no, pwd);
	   if(bCheck==true)
	   {
		   data="<script>location.href=\"list.do\";</script>";
	   }
	   return data;
   }
}












