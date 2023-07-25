<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>community</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page content-->
    <div class="container mt-5">
        <div class="row">
            <div class="editable offset-3 col-6">
                <form name="addBoard" class="form-horizontal" action="/ToGo/board/cmWritePro" method="post">
                    <article>
                        <div class="mb-4">
                            <!-- 제목 -->
                            <div class="mx-3 mb-2">제목</div>
                            <input id="cm_title" name="cm_title" type="text" class="form-control mb-3" placeholder="제목을 입력해 주세요." required />
                            <!-- 작성자 -->
                            <div class="mx-3 mb-2">작성자</div>
                            <div class="form-control mb-3">${memId}</div>
                            <!-- 내용 -->
                            <div class="mx-3 mb-2">내용</div>
                            <textarea id="cm_content" name="cm_content" class="form-control mb-3" placeholder="내용을 입력해 주세요." required style="width: 100%; height: 400px;"></textarea>
                            <div class="btn_wrap text-end mb-5">
                                <button class="btn btn-success" type="submit" id="write" value="등록">등록</button>
                                <a class="btn btn-danger waves-effect waves-light" href="/ToGo/board/cmMain" style="color: white;">취소</a>
                            </div>
                        </div>
                    </article>
                </form>
            </div>
        </div>
    </div>
    <footer></footer>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        
</body>
</html>
