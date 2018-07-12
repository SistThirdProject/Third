<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet prefetch'
	href='http://cdn.jsdelivr.net/instantsearch.js/1/instantsearch.min.css'>
<link rel='stylesheet prefetch'
	href='http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<script type="text/babel">
  class App extends React.Component
  {
	constructor(props){
		super(props);
		//this.state={time:[]};
	}
    render(){
		let timearray=this.props.time;
		let datearray=[];
		let hourarray=[];
		let minutearray=[];
		
		timearray.sort();
		for(var i=0;i<timearray.length;i++){
			let d=(new Date(timearray[i])).getDate();
			if(i!=0&&datearray[datearray.length-1] != d)
				datearray.push(d);
		}
         return (
          		<div>
					<div className="panel panel-info col-lg-3">
						<div className="panel-heading">날짜</div>
								<div className="panel-body" style={{padding:'0px'}}>{datearray}</div>				
						</div>
					<div className="panel panel-info col-lg-3">
						<div className="panel-heading">시간</div>
						<div className="panel-body"></div>
					</div>
					<div className="panel panel-info col-lg-3">
						<div className="panel-heading">분</div>
						<div className="panel-body" ></div>
					</div>
					<div className="panel panel-info col-lg-3">
						<div className="panel-heading">키워드</div>
						<c:forEach var="vo" items="${keywordlist}">
							<div className="panel-body" style={{padding:'0px'}} value="${vo.time}">
								<a href="month_search.do?time=${vo.time}&rank=${vo.rank}">
									${vo.keyword}
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
           );
       }
    }
 ReactDOM.render(<App time={${timelist}} />,document.getElementById('reactroot'));
</script>
<p></p>
<p></p>
<p></p>
	<div class="container" id="reactroot"></div>

	<div class="container">
	<h2>${title}</h2>
		<div class="row">
			
		</div>
		<div class=row>
			<div class="col-lg-6">
				<div class="row">
					<table class="table table-hover">
						<tr class="success">
							<th class="text-center">관련 뉴스 리스트</th>
						</tr>
						<c:forEach var="vo" items="${newslist}" varStatus="state">
							<c:if test="${state.index %2 eq 0 }">
								<tr>
									<td><a href="${vo.link }">${vo.title }</a></td>
								</tr>
							</c:if>
						</c:forEach>

					</table>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="row">
					<table class="table table-hover">
						<tr class="success">
							<th class="text-center">&nbsp</th>
						</tr>
						<c:forEach var="vo" items="${newslist}" varStatus="state">
							<c:if test="${state.index %2 ne 0 }">
								<tr>
									<td><a href="${vo.link }">${vo.title }</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>