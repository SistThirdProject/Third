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
     width : 350px;
}
h2 {
        text-align: center;
}
</style>
</head>
<body>
    <div class="container">
      <div class="row">
        <h2>삭제하기</h2>
        <form method=post action="delete_ok.do">
        <table class="table table-hover">
          <tr>
            <td class="text-left">
             비밀번호:<input type="password" size=15 name=pwd required>
             <input type="hidden" name=no value="${no }">
            </td>
          </tr>
          <tr>
            <td class="text-center">
               <input type="submit" value="삭제" class="btn btn-sm btn-success">
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










