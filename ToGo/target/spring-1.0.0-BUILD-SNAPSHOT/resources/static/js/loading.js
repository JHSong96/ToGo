function loadingProcess(){
    openLoading();
    // 타이머를 이용해 로딩창 종료
    setTimeout(closeLoading, 3000);
}

function openLoading() {
    //화면 높이와 너비를 구합니다.
    let maskHeight = $(document).height();
    let maskWidth =  window.document.body.clientWidth;
    //출력할 마스크를 설정해준다.
    let mask ="<div id='mask' style='position:absolute; z-index:900000; background-color:#000000; display:none; left:0; top:0;'></div>";
    // 로딩 이미지 주소 및 옵션
    let loadingImg ='';
    loadingImg += "<div id='loadingImg' style='position:absolute; top: calc(50% - (200px / 2)); width:100%; z-index:999800;'>";
    loadingImg += " <img src='https://img.pikbest.com/png-images/20190918/cartoon-snail-loading-loading-gif-animation_2734139.png!bw700' style='position: relative; display: block; margin: 0px auto;width: 150px;height: 150px'/>";
    loadingImg += "<a class='close' href='#'></a>";
    loadingImg += "</div>";
    //레이어 추가
    $('body')
        .append(mask)
        .append(loadingImg)
    //마스크의 높이와 너비로 전체 화면을 채운다.
    $('#mask').css({
        'width' : maskWidth,
        'height': maskHeight,
        'opacity' :'0.3'
    });
    //마스크 표시
    $('#mask').show();
    //로딩 이미지 표시
    $('#loadingImg').show();
}

// 로딩창 끄는 함수
function closeLoading() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').empty();
}