function loadContent(page) {
    const contentDiv = document.getElementById('content');
    const profileImage = document.getElementById('profileImage');
    const nickname = document.getElementById('nickname');

    // 모든 버튼의 활성화 클래스 제거
    const buttons = document.querySelectorAll('.button');
    buttons.forEach(button => button.classList.remove('active'));

    switch (page) {
        case 'EditProfile':
            contentDiv.innerHTML = '<div class="active">회원 정보 수정 화면입니다.</div>';
            break;
        case 'Inquiries':
            contentDiv.innerHTML = '<div class="active">문의 내역 화면입니다.</div>';
            break;
        case 'Payments':
            contentDiv.innerHTML = '<div class="active">결제 내역 화면입니다.</div>';
            break;
        case 'Withdraw':
            contentDiv.innerHTML = '<div class="active">회원 탈퇴 화면입니다.</div>';
            break;
        default:
            contentDiv.innerHTML = '';
            break;
    }

    // 클릭된 버튼에 활성화 클래스 추가
    const clickedButton = document.getElementById(page.toLowerCase());
    if (clickedButton) {
        clickedButton.classList.add('active');
    }
}


//탈퇴 여부 스크립트
function confirmDelete() {
    if (confirm('정말 탈퇴하시겠습니까?')) {
        // 사용자가 '예'를 선택한 경우, 서버에 탈퇴 요청을 보냅니다.
        window.location.href = '/delete-account';
    }
}
