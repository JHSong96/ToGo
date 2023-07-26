<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
	<title>Q&A 게시판</title>
</head>
<body>
<h3><a href= "/ToGo/board/qnaList">Q&A</a></h3>
<form method="post" action="qnaList" id="list">
	<input type="hidden" name="curPage" value="1" />
	<c:if test="${memId != null}">
		<a href= "/ToGo/board/qnaWriteForm">글쓰기</a>
	</c:if>
	<div>
	<div >검색창</div>
		<div>
			<select name="option">
				<option value="all">전체</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="id">작성자</option>
			</select>
			<input name="keyword" type="text" placeholder="검색어를 입력하세요..." aria-describedby="button-search" />
			<button type="submit">
		 		<i>검색</i>
			</button>
		</div>
	</div>
</form>
<h3>총 게시글 수 ${pr.total}</h3>
<table>
	<tr>
		<th class="w-px60">번호</th>
		<th>제목</th>
		<th class="w-px100">작성자</th>
		<th class="w-px120">작성일자</th>
		<th class="w-px60">조회수</th>
	</tr>
	<c:forEach items="${boardList }" var="dto">
		<tr>
			<td>${dto.num }</td>
			<td class="left">
				<c:forEach var="i" begin="1" end="${dto.indent }">
					${i eq dto.indent ? "<img src='/ToGo/resources/static/img/re.png' class='image'/>" : "&nbsp;&nbsp;" }
				</c:forEach>
				<a href="/ToGo/board/qnaDetail?num=${dto.num }" >${dto.title }</a>
			</td>
			<td>${dto.writer }</td>
			<td><fmt:formatDate value="${dto.writedate}" pattern="yyyy/MM/dd" /></td>
			<td>${dto.readcnt }</td>
		</tr>
	</c:forEach>
	</table>
	<!-- Pagination-->
				<nav aria-label="Pagination">
					<hr class="my-0" />
					<ul class="pagination justify-content-center my-4">
						<c:if test="${pr.startPage > pr.pagePerBlock}">
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/qnaList?pageNum=1">
									<i class="fs-3 bi bi-caret-left-fill"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/qnaList?pageNum=${pr.startPage - 1}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-left"></i>
								</a>
							</li>
						</c:if>
						<c:forEach begin="${pr.startPage}" end="${pr.endPage}" var="pNum">
							<li class="page-item ${pr.page == pNum ? 'active-btn' : 'non-active-btn'}">
								<a class="page-link" href="/ToGo/board/qnaList?pageNum=${pNum}&option=${option}&keyword=${keyword}" name="pageNum">${pNum}</a>
							</li>
						</c:forEach>
						<c:if test="${pr.endPage < pr.totalPage}">
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/qnaList?pageNum=${pr.endPage + 1}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-right"></i>
								</a>
							</li>
							<li class="page-item">
								<a class="page-link" href="/ToGo/board/qnaList?pageNum=${pr.totalPage}&option=${option}&keyword=${keyword}">
									<i class="fs-3 bi bi-caret-right-fill"></i>
								</a>
							</li>
						</c:if>
					</ul>
				</nav>
				
</body>
<style>
  .image {
    width: 12px;
    height: 12px;
  }
</style>
</html>