<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.row {
     margin: 0px auto;
     width : 900px;
}
h2 {
        text-align: center;
}
</style>
</head>
<body>
     <div class="container">
       <div class="row">
          <h2>내용보기</h2>
          <table class="table table-hover">
            <tr>
              <td class="text-center success" width=20%>번호</td>
              <td class="text-center" width=30%>${vo.no }</td>
              <td class="text-center success" width=20%>작성일</td>
              <td class="text-center" width=30%>${vo.regdate }</td>
            </tr>
            <tr>
              <td class="text-center success" width=20%>이름</td>
              <td class="text-center" width=30%>${vo.name }</td>
              <td class="text-center success" width=20%>조회수</td>
              <td class="text-center" width=30%>${vo.hit}</td>
            </tr>
            <tr>
              <td class="text-center success" width=20%>제목</td>
              <td class="text-left" colspan="3">${vo.subject }</td>
            </tr>
            <tr>
              <td class="text-left" colspan="4" height="200" valign="top">${vo.content }</td>
            </tr>
          </table>
          <table class="table">
            <tr>
              <td class="text-right">
                <a href="reply.do?no=${vo.no }" class="btn btn-sm btn-danger">답변</a>
                <a href="update.do?no=${vo.no }" class="btn btn-sm btn-warning">수정</a>
                <a href="delete.do?no=${vo.no }" class="btn btn-sm btn-primary">삭제</a>
                <a href="list.do" class="btn btn-sm btn-info">목록</a>
              </td>
            </tr>
          </table>
       </div>
     </div>
</body>
</html>










