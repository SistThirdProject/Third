package com.sist.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class BoardDAO {
    @Autowired
    private MongoTemplate mt;
    // 목록 출력 
    public List<BoardVO> boardListData(int page)
    {
    	  List<BoardVO> list=new ArrayList<BoardVO>();
    	  try
    	  {
    		  // 페이지 나누기 => 정렬 => Query
    		  Query query=new Query();
    		  // skip => limit => MS-SQL,MySQL  => 인라인 뷰(rownum) autoincrement
    		  query.with(new Sort(Sort.Direction.DESC,"group_id").and(new Sort(Sort.Direction.ASC,"group_step")));
    		  // ORDER BY group_id DESC,group_step ASC
    		  int rowSize=10;
    		  int skip=(page*rowSize)-rowSize;
    		  query.skip(skip).limit(rowSize);
    		  
    		  list=mt.find(query, BoardVO.class,"board");
    		  // find => (SELECT *~~)
    		  // findOne=> (SELECT * ~~ WHERE no=?)
    		  // 컬럼별로 사용 할 수 없다 
    	  }catch(Exception ex)
    	  {
    		  System.out.println(ex.getMessage());
    	  }
    	  return list;
    }
    public int boardTotalPage()
    {
    	int total=0;
    	Query query = new Query();
    	int count = (int)mt.count(query, "board");
    	total=(int)(Math.ceil(count/10.0));
    	return total;
    }
    public void boardInsert(BoardVO vo)
     {
         Query query=new Query();
         List<BoardVO> list=mt.find(query, BoardVO.class,"board");
         int max=0;
         int gi=0;
         for(BoardVO pvo:list)
           {
        	   if(max<pvo.getNo())
        		   max=pvo.getNo();
        	   if(gi<pvo.getGroup_id())
        		   gi=pvo.getGroup_id();
           }
           vo.setNo(max+1);
           vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
           vo.setHit(0);
           vo.setGroup_id(gi+1);
           vo.setGroup_step(0);
           vo.setGroup_tab(0);
           vo.setRoot(0);
           vo.setDepth(0);
           
           mt.insert(vo,"board");
         
     }
    public BoardVO boardContentData(int no)
    {
    	BoardVO vo=new BoardVO();
    	BasicQuery query=new BasicQuery("{no:"+no+"}"); // {no:1}  WHERE no=1
    	// {no:{"$lt":1}}  ===>  WHERE no<1  {no:{"$gt":1}} no>1
    	//  {no:{"$lte":1}}  no<=1  {no:{"$gte":1}} no>=1
    	vo=mt.findOne(query, BoardVO.class,"board");
    	Update update=new Update();
    	update.set("hit", vo.getHit()+1);
    	mt.updateFirst(query, update, "board");
    	vo=mt.findOne(query, BoardVO.class,"board");
    	return vo;
    }
    public void boardReply(int pno,BoardVO vo)
    {
    	BasicQuery query1=new BasicQuery("{no:"+pno+"}");
    	BoardVO pvo=mt.findOne(query1, BoardVO.class,"board");
    	// 상위 게시물 번호 
    	BasicQuery query2=new BasicQuery("{$and:[{group_id:"+pvo.getGroup_id()+"},{group_step:{$gt:"+pvo.getGroup_step()+"}}]}");
    	List<BoardVO> list2=mt.find(query2, BoardVO.class,"board");
    	for(BoardVO avo:list2)
    	{
    		BasicQuery query3=new BasicQuery("{no:"+avo.getNo()+"}");
    		Update update=new Update();
    		update.set("group_step", avo.getGroup_step()+1);
    		mt.updateFirst(query3, update, "board");
    	}
    	// gi,gt,gs
    	//  WHERE group_id=10 AND group_step>3
    	//  ============== 데이터 추가 ==============================================
    	  Query query=new Query();
        List<BoardVO> list=mt.find(query, BoardVO.class,"board");
        int max=0;
        // 번호 ==> Sequence
        for(BoardVO tvo:list)
          {
       	   if(max<tvo.getNo())
       		   max=tvo.getNo();
       	  
          }
          vo.setNo(max+1);
          vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
          vo.setHit(0);
          vo.setGroup_id(pvo.getGroup_id());
          vo.setGroup_step(pvo.getGroup_step()+1);
          vo.setGroup_tab(pvo.getGroup_tab()+1);
          vo.setRoot(pvo.getNo());
          vo.setDepth(0);
          
          mt.insert(vo,"board");
          // =========================================================================
          BasicQuery query4=new BasicQuery("{no:"+pvo.getNo()+"}");
          Update update=new Update();
          update.set("depth", pvo.getDepth()+1);
          mt.updateFirst(query4, update, "board");
    }
    public BoardVO boardUpdateData(int no)
    {
    	BoardVO vo=new BoardVO();
    	BasicQuery query=new BasicQuery("{no:"+no+"}"); // {no:1}  WHERE no=1
    	vo=mt.findOne(query, BoardVO.class,"board");
    	return vo;
    }
    public boolean boardUpdateOk(BoardVO vo)
    {
    	boolean bCheck=false;
    	BasicQuery query=new BasicQuery("{no:"+vo.getNo()+"}");
    	BoardVO dvo=mt.findOne(query, BoardVO.class,"board");
    	if(dvo.getPwd().equals(vo.getPwd()))
    	{
    		bCheck=true;
    		Update update=new Update();
    		update.set("name", vo.getName());
    		update.set("subject", vo.getSubject());
    		update.set("content", vo.getContent());
    		mt.updateFirst(query, update, "board");
    	}
    	
    	return bCheck;
    }
    //============================ 추가 =======================================
    public String boardContent(String name)
    {
    	String data="";
    	BasicQuery query=new BasicQuery("{name:'"+name+"'}");
    	List<BoardVO> list=mt.find(query, BoardVO.class,"board");
    	for(BoardVO vo:list)
    	{
    		data+=vo.getContent();
    	}
    	return data;
    }
    
    public boolean boardDelete(int no,String pwd)
    {
    	  boolean bCheck=false;
    	  // 1. 데이터 읽기
    	  BasicQuery query=new BasicQuery("{no:"+no+"}");
    	  BoardVO vo=mt.findOne(query, BoardVO.class,"board");
    	  if(pwd.equals(vo.getPwd()))
    	  {
    		  bCheck=true;
    		  // 답변이 없다면 
    		  if(vo.getDepth()==0)
    		  {
    			  mt.remove(query, "board");
    		  }
    		  else
    		  {
    			  String msg="관리자가 삭제한 게시물입니다";
    			  Update update=new Update();
    			  update.set("subject", msg);
    			  update.set("content", msg);
    			  mt.updateFirst(query,update, "board");
    		  }
    		  
    		  // depth 감소
    		  if(vo.getRoot()!=0)
    		  {
	    		  BasicQuery query1=new BasicQuery("{no:"+vo.getRoot()+"}");
	    		  System.out.println("root:"+vo.getRoot());
	    		  BoardVO pvo=mt.findOne(query1, BoardVO.class,"board");
	    		  System.out.println("pvo:"+pvo);
	    		  Update update=new Update();
	    		  update.set("depth", pvo.getDepth()-1);
	    		  mt.updateFirst(query1, update, "board");
    		  }
    	  }
    	  return bCheck;
    }
}














