<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>community</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/static/song/css/styles.css" rel="stylesheet" />
</head>
<style>
#file-input:after{
padding-left:10px;
width:10px;
height:10px;
content: "\00d7";
cursor: pointer;
}
</style>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- Page content-->
    <div class="container mt-5">
        <div class="row">
            <div class="editable offset-3 col-6">
                <form name="addBoard" class="form-horizontal" action="/ToGo/board/cmWritePro" method="post" enctype="multipart/form-data">
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
                            <div class="file_div">
                            file : <input type="file" id="file-input" name="save" /> <br />
							</div>
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
<script>
$('.file_div').on('change', 'input[name=save]', function() {
    let div1 = '<label for="file-input">file :&nbsp</label><input type="file" id="file-input" name="save" /> <br />';
    $('.file_div').append(div1);
});
</script>
</body>
</html>
