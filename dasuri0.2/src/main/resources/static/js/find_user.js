// function findUserId() {
//     var name = document.getElementById('user').value;
//     var birth = document.getElementById('birth').value;
//
//     // AJAX 요청을 보내는 부분
//     $.ajax({
//         url: '/find_user_id', // 서버의 URL 주소를 입력하세요.
//         type: 'POST',
//         dataType: 'json',
//         data: {
//             name: name,
//             birth: birth
//         },
//         success: function(response) {
//             // 서버로부터 응답을 받았을 때 실행할 코드
//             if(response.success) {
//                 var maskedIds = response.maskedIds;
//                 alert("찾으신 아이디는 다음과 같습니다:\n" + maskedIds.join("\n"));
//             } else {
//                 alert("해당하는 사용자 정보를 찾을 수 없습니다.");
//             }
//         },
//         error: function(xhr, status, error) {
//             // 요청 실패 시 실행할 코드
//             alert("에러 발생: " + error);
//         }
//     });
// }
function findUserId() {
    var name = document.getElementById('name').value;
    var birth = document.getElementById('birth').value;

    fetch('/findUserIds', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: name, birth: birth }),
    })
        .then(response => response.json())
        .then(data => {
            // 여기서 data.userIds가 정의되어 있는지 확인
            if (data.userIds && data.userIds.length > 0) {
                var message = 'Found User IDs:\n' + data.userIds.map(userId => userId).join('\n');
                alert(message);
            } else {
                // 사용자에게 결과 없음을 알림
                alert('No users found with that name and birth date.');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}