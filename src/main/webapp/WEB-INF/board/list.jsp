<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
.row{
   margin: 0px auto;
   width: 900px;
}
h2{
   text-align: center;
}
</style>
</head>
<body>
   <div class="container">
      <h2>빅데이터 자료요청 게시판</h2>
     <div class="row">
        <table class="table">
           <tr>
               <td class="text-left">
                   <a href="insert.do" class="btn btn-sm btn-defalut">새글</a>
               </td>
           </tr>
        </table>
        <table class="table table-hover">
           <tr class="success">
               <th width=10% class="text-center">번호</th>
               <th width=45% class="text-center">제목</th>
               <th width=15% class="text-center">이름</th>
               <th width=20% class="text-center">작성일</th>
               <th width=10% class="text-center">조회수</th>
           </tr>
           <c:forEach var="vo" items="${list }">
                 <tr>
		               <td width=10% class="text-center">${vo.no }</td>
		               <td width=45% class="text-left">
		                     <c:if test="${vo.group_tab>0 }">
		                         <c:forEach var="i" begin="1" end="${vo.group_step }">
		                                   &nbsp;&nbsp;
		                         </c:forEach>
		                              <b>└</b>
		                     </c:if>
		                 <a href="content.do?no=${vo.no }">${vo.subject }</a>
		               </td>
		               <td width=15% class="text-center">${vo.name }</td>
		               <td width=20% class="text-center">${vo.regdate }</td>
		               <td width=10% class="text-center">${vo.hit }</td>
		           </tr>
           </c:forEach>
        </table>
        <table class="table">
           <tr>
               <td class="text-left">
                   Search:<select name=fs>
                        <option value="name">이름</option>
                        <option value="subject">제목</option>
                        <option value="content">내용</option>
                   </select>
                   <input type="text" name="ss" size=13>
                   <input type="submit" value="검색" class="btn btn-sm btn-defalut">
               </td>
               <td class="text-right">
                     <a href="#" class="btn btn-sm btn-defalut">이전</a>
                     <a href="#" class="btn btn-sm btn-defalut">다음</a>
                     &nbsp; &nbsp;
                     ${curpage } page / ${totalpage } pages
               </td>
           </tr>
        </table>
     </div>
   </div>
</body>
</html>






