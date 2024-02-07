/*==========================================================================================================================================================
 * 중복 검사 ajax
 */
// $(document).ready(function() {
//     function checkId(idInput, messageElement) { // `messageType` 파라미터 제거
//         var id = $(idInput).val();
//         var pattern = /^[a-z0-9]{4,12}$/;
//
//         if (!pattern.test(id)) {
//             $(messageElement).text('영문 소문자/숫자 4~12자리여야 합니다.').css({
//                 'color': '#ff0909',
//                 'font-weight': 'bold',
//                 'font-size': '0.8em'
//             });
//             return;
//         }
//
//         $.ajax({
//             type: 'POST',
//             url: '/check_duplicate',
//             data: {
//                 'userId': id, // 서버 측에서 기대하는 파라미터명으로 변경
//             },
//             success: function(response) {
//                 if (response.duplicate) {
//                     $(messageElement).text('이미 사용 중인 아이디입니다.').css({
//                         'color': '#ff0909',
//                         'font-weight': 'bold',
//                         'font-size': '0.8em'
//                     });
//                 } else {
//                     $(messageElement).text('사용할 수 있는 아이디입니다.').css({
//                         'color': '#14da3b',
//                         'font-size': '0.8em'
//                     });
//                 }
//             },
//             error: function() {
//                 console.error('아이디 중복 확인 요청 실패');
//             }
//         });
//     }
//
//     // userId 중복 검사
//     $('#userId').on('input', function() {
//         checkId('#userId', '#duplicateMessage');
//     });
//
//     // proId 중복 검사
//     $('#proId').on('input', function() {
//         checkId('#proId', '#duplicateProMessage');
//     });
// });

//닉네임 중복검사
$(document).ready(function() {
    function checkNickname(NicknameInput, messageElement) { // `messageType` 파라미터 제거
        var Nickname = $(NicknameInput).val();
        var pattern = /^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/;

        if (!pattern.test(Nickname)) {
            $(messageElement).text('특수문자를 제외한 2~10자리여야 합니다.').css({
                'color': '#ff0909',
                'font-weight': 'bold',
                'font-size': '0.8em'
            });
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/check_duplicate_nickname',
            data: {
                'userNickname': Nickname, // 서버 측에서 기대하는 파라미터명으로 변경
            },
            success: function(response) {
                if (response.duplicate) {
                    $(messageElement).text('이미 사용 중인 닉네임입니다.').css({
                        'color': '#ff0909',
                        'font-weight': 'bold',
                        'font-size': '0.8em'
                    });
                } else {
                    $(messageElement).text('사용할 수 있는 닉네임입니다.').css({
                        'color': '#14da3b',
                        'font-size': '0.8em'
                    });
                }
            },
            error: function() {
                console.error('닉네임 중복 확인 요청 실패');
            }
        });
    }

    // userId 중복 검사
    $('#userNickname').on('input', function() {
        checkNickname('#userNickname', '#nickduplicateMessage');
    });

    // proId 중복 검사
    $('#proNickname').on('input', function() {
        checkNickname('#proNickname', '#nickduplicateProMessage');
    });
});

$(document).ready(function() {
    $('#userPwdConfirm').on('input', function() {
        validatePassword();
    });

    function validatePassword() {
        var password = $('#userPwd').val();
        var confirmPassword = $('#userPwdConfirm').val();

        // 비밀번호의 정규식 패턴
        var pattern = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}$/;

        if (!pattern.test(password)) {
            // 비밀번호가 정규식 패턴과 일치하지 않는 경우
            $('#passwordMatchMessage').text('비밀번호는 8~16자 영문 대/소문자, 숫자, 특수문자를 사용하세요.').css({
                'color': '#ff0909',
                'font-weight': 'bold',
                'font-size': '0.8em'
            });
            return; // 검사 종료
        }

        if (password === '') {
            // 비밀번호가 입력되지 않은 경우
            $('#passwordMatchMessage').text('비밀번호를 먼저 입력하세요.').css({
                'color': '#ff0909',
                'font-weight': 'bold',
                'font-size': '0.8em'
            });
        } else if (password === confirmPassword) {
            // 비밀번호가 일치하는 경우
            $('#passwordMatchMessage').text('비밀번호가 일치합니다.').css({
                'color': '#14da3b',
                'font-size': '0.8em'
            });
        } else {
            // 비밀번호가 일치하지 않는 경우
            $('#passwordMatchMessage').text('비밀번호가 일치하지 않습니다.').css({
                'color': '#ff0909',
                'font-weight': 'bold',
                'font-size': '0.8em'
            });
        }
    }
});

//회원정보 수정시 비밀번호 입력값 필수
function validateForm() {
    var password = document.getElementById('userPwd').value;
    var confirmPassword = document.getElementById('userPwdConfirm').value;

    // 비밀번호와 비밀번호 확인이 비어있는지 확인
    if (password === '' || confirmPassword === '') {
        Swal.fire({
            icon: 'warning',
            text: '비밀번호는 필수 입력값입니다.',
            confirmButtonText: '확인'
        });
        return false; // 폼 제출을 방지
    }

    // 비밀번호와 비밀번호 확인이 일치하는지 확인
    if (password !== confirmPassword) {
        Swal.fire({
            icon: 'error',
            text: '비밀번호가 일치하지 않습니다.',
            confirmButtonText: '확인'
        });
        return false; // 폼 제출을 방지
    }

    // 모든 검사를 통과하면 토스트 알림을 띄우고 폼 제출을 진행
    const Toast = Swal.mixin({
        toast: true,
        position: 'center-center',
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer);
            toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
    });

    Toast.fire({
        icon: 'success',
        title: '완료! 로그인으로 이동합니다.'
    }).then(() => {
        // 토스트가 닫힌 후 폼 제출
        document.querySelector('form').submit();
    });

    return false; // 비동기 처리를 위해 기본 제출을 방지
}

//파일 선택 미리보기
function previewImage(input) {
    var file = input.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('profileImage').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
}


/*==========================================================================================================================================================
 * 기사 지역구 전체 선택 처리 (start)
 */
document.addEventListener("DOMContentLoaded", function() {
    // 전체 선택 체크박스의 상태 변경을 감지하는 이벤트 리스너
    document.getElementById("selectAll").addEventListener("change", function() {
        var checkboxes = document.querySelectorAll('input[name="proLegions"]');
        var allChecked = this.checked;
        checkboxes.forEach(function(checkbox) {
            if (checkbox !== document.getElementById("selectAll")) {
                checkbox.checked = allChecked;
                checkbox.disabled = allChecked; // 전체 선택 시 다른 체크박스 비활성화
            }
        });
    });

    // 폼 제출 처리
    document.querySelector("form").addEventListener("submit", function(e) {
        // e.preventDefault(); // 실제 제출을 방지하기 위해

        var selectedRegions = [];
        var checkboxes = document.querySelectorAll('input[name="proLegions"]:checked:not(#selectAll)'); // 전체 선택을 제외한 체크된 체크박스

        checkboxes.forEach(function(checkbox) {
            selectedRegions.push(checkbox.value);
        });

        var result = selectedRegions.join('/');
        console.log('선택된 지역: ' + result); // 선택된 지역을 콘솔에 출력

        // 여기서 result 값을 서버로 전송하는 코드를 추가할 수 있습니다.
        // 예: AJAX 요청을 사용해서 서버에 result 값 전송
    });
});

/*
 * 기사 지역구 전체 선택 처리 (end)
 */
/*==========================================================================================================================================================
 * 휴대폰 인증 script (start)
 *
 */
function sendVerificationCode() {
    var userPhone = $('#userPhone').val();
    $.ajax({
        url: '/sendVerificationCode', // 인증번호를 보내는 서버의 URL
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ phoneNumber: userPhone }),
        success: function(response) {
            // SweetAlert를 사용하여 성공 메시지를 표시합니다.
            Swal.mixin({
                toast: true,
                position: 'center-center',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            }).fire({
                icon: 'success',
                title: '인증번호가 발송되었습니다.'
            });
        },
        error: function(error) {
            // SweetAlert를 사용하여 에러 메시지를 표시합니다.
            Swal.mixin({
                toast: true,
                position: 'center-center',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            }).fire({
                icon: 'error',
                title: '인증번호 발송에 실패했습니다.'
            });
        }
    });
}
/*<![CDATA[*/
// function sendVerificationCode() {
//     var userPhone = $('#userPhone').val();
//     $.ajax({
//         url: '/sendVerificationCode', // 인증번호를 보내는 서버의 URL
//         type: 'POST',
//         contentType: 'application/json',
//         data: JSON.stringify({ phoneNumber: userPhone }),
//         success: function(response) {
//             alert("인증번호가 발송되었습니다.");
//         },
//         error: function(error) {
//             alert("인증번호 발송에 실패했습니다.");
//         }
//     });
// }

function verifyCode() {
    var userPhone = $('#userPhone').val();
    var verificationCode = $('#verificationCode').val();
    $.ajax({
        url: '/verifyCode', // 사용자가 입력한 인증번호를 확인하는 서버의 URL
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ phoneNumber: userPhone, code: verificationCode }),
        success: function(response) {
            $('#verificationMessage').text("인증 성공!");
        },
        error: function(error) {
            $('#verificationMessage').text("인증 실패. 다시 시도해주세요.");
        }
    });
}
/*]]>*/
/*
 * 휴대폰 인증 script (end)
 */
/*==========================================================================================================================================================
 * kakao 우편번호 API script (start)
 */
function sample6_execDaumPostcode() {
    console.log("sing_up.js 진입");
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}


/*
 * kakao 우편번호 API script (end)
 */