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
        <h2>글쓰기</h2>
        <form method="post" action="insert_ok.do">
        <table class="table table-hover">
          <tr>
            <td width=20% class="text-right success">이름</td>
            <td width=80% class="text-left">
             <input type=text name=name size=15 required>
            </td>
          </tr>
          
          <tr>
            <td width=20% class="text-right success">제목</td>
            <td width=80% class="text-left">
             <input type=text name=subject size=50 required>
            </td>
          </tr>
          
          <tr>
            <td width=20% class="text-right success">내용</td>
            <td width=80% class="text-left">
             <textarea rows="10" cols="55" name=content required></textarea>
            </td>
          </tr>
          
          <tr>
            <td width=20% class="text-right success">비밀번호</td>
            <td width=80% class="text-left">
             <input type="password" size=10 name=pwd required>
            </td>
          </tr>
          
          <tr>
            <td colspan="2" class="text-center">
             <input type="submit" value="글쓰기" class="btn btn-sm btn-success">
             <input type="button" value="취소" class="btn btn-sm btn-warning"
              onclick="javascript:history.back()"
             >
            </td>
          </tr>
        </table>
        </form>
      </div>
    </div>
</body>
</html>






