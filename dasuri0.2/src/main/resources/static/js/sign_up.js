/*==========================================================================================================================================================
 * 중복 검사 ajax
 */
$(document).ready(function() { // 아이디 중복검사
    $('#userId').on('input', function() {
        var userId = $(this).val();
        // 정규식 패턴
        var pattern = /^[a-z0-9]{4,12}$/;

        if (!pattern.test(userId)) {
            // 아이디가 정규식 패턴과 일치하지 않는 경우
            $('#duplicateMessage').text('영문 소문자/숫자 4~12자리여야 합니다.').css({
                'color': '#ff0909',
                'font-weight': 'bold',
                'font-size': '0.8em'
            });
            return; // 검사 종료
        }

        // AJAX 요청
        $.ajax({
            type: 'POST',
            url: '/check_duplicate',
            data: {'userId': userId},
            success: function(response) {
                if (response.duplicate) {
                    // 빨간색 텍스트 스타일
                    $('#duplicateMessage').text('이미 사용 중인 아이디입니다.').css({
                        'color': '#ff0909',
                        'font-weight': 'bold',
                        'font-size': '0.8em'
                    });
                } else {
                    // 녹색 텍스트 스타일 #7CFC00
                    $('#duplicateMessage').text('사용할 수 있는 아이디입니다.').css({
                        'color': '#14da3b',
                        'font-size': '0.8em'
                    });
                }
            },
            error: function() {
                console.error('아이디 중복 확인 요청 실패');
            }
        });
    });
});
// $(document).ready(function() { //아이디 중복검사
//     $('#userId').on('input', function() {
//         var userId = $(this).val();
//         $.ajax({
//             type: 'POST',
//             url: '/check_duplicate',
//             data: {'userId': userId},
//             success: function(response) {
//                 if (response.duplicate) { // 빨간색 텍스트 스타일
//                     $('#duplicateMessage').text('이미 사용 중인 아이디입니다.').css({'color': '#ff0909', 'font-weight': 'bold', 'font-size': '0.8em'});
//                 } else { // 녹색 텍스트 스타일 #7CFC00
//                     $('#duplicateMessage').text('사용할 수 있는 아이디입니다.').css({'color': '#14da3b', 'font-size': '0.8em'});
//                 }
//             },
//             error: function() { console.error('아이디 중복 확인 요청 실패'); }
//         });
//     });
// });

//비밀번호 확인
// $(document).ready(function() {
//     $('#userPwdConfirm').on('input', function() {
//         validatePassword();
//     });
//
//     function validatePassword() {
//         var password = $('#userPwd').val();
//         var confirmPassword = $('#userPwdConfirm').val();
//
//         if (password === '') {
//             // 비밀번호가 입력되지 않은 경우
//             $('#passwordMatchMessage').text('비밀번호를 먼저 입력하세요.').css({'color': '#ff0909', 'font-weight': 'bold', 'font-size': '0.8em'});
//         } else if (password === confirmPassword) {
//             // 비밀번호가 일치하는 경우
//             $('#passwordMatchMessage').text('비밀번호가 일치합니다.').css({'color': '#14da3b', 'font-size': '0.8em'});
//         } else {
//             // 비밀번호가 일치하지 않는 경우
//             $('#passwordMatchMessage').text('비밀번호가 일치하지 않습니다.').css({'color': '#ff0909', 'font-weight': 'bold', 'font-size': '0.8em'});
//         }
//     }
// });


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
/*==========================================================================================================================================================
 * 기사 지역구 전체 선택 처리 (start)
 */

function toggleCheckboxes() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[name="proLegions"]');

    checkboxes.forEach(function (checkbox) {
        checkbox.disabled = selectAllCheckbox.checked;
        checkbox.checked = selectAllCheckbox.checked;
    });
}

document.getElementById("regionForm").addEventListener("submit", function (event) {
    event.preventDefault(); // 기존의 submit 동작 방지

    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[name="proLegions"]:checked');

    // 선택된 지역들을 저장할 변수
    var selectedRegions = [];

    if (selectAllCheckbox.checked) {
        selectedRegions.push("aaa");
    } else {
        checkboxes.forEach(function (checkbox) {
            selectedRegions.push(checkbox.value);
        });
    }

    // 선택된 지역들을 문자열로 합치기 (구분자: '/')
    var result = selectedRegions.join('/');

    // 결과 변수를 어딘가에 전달하거나 로직에 활용
    console.log('선택된 지역: ' + result);

});
// function toggleCheckboxes() {
//     var selectAllCheckbox = document.getElementById("selectAll");
//     var checkboxes = document.querySelectorAll('input[name="proLegions"]');
//
//     checkboxes.forEach(function (checkbox) {
//         checkbox.disabled = selectAllCheckbox.checked;
//         checkbox.checked = selectAllCheckbox.checked;
//     });
// }
//
// document.getElementById("regionForm").addEventListener("submit", function (event) {
//     event.preventDefault(); // 기존의 submit 동작 방지
//
//     var selectAllCheckbox = document.getElementById("selectAll");
//     var checkboxes = document.querySelectorAll('input[name="proLegions"]:checked');
//
//     // 선택된 지역들을 저장할 변수
//     var selectedRegions = [];
//
//     if (selectAllCheckbox.checked) {
//         selectedRegions.push("aaa");
//         var result = selectedRegions;
//     } else {
//         checkboxes.forEach(function (checkbox) {
//             selectedRegions.push(checkbox.value);
//             // 선택된 지역들을 문자열로 합치기 (구분자: '/')
//             var result = selectedRegions.join('/');
//         });
//     }
//
//
//     // 결과 변수를 어딘가에 전달하거나 로직에 활용
//     // console.log('선택된 지역: ' + result);
// });


/*
 * 기사 지역구 전체 선택 처리 (end)
 */



/*==========================================================================================================================================================
 * 휴대폰 인증 script (start)
 *
 */

function verifyPhoneNumber() {

}

/*
 * 휴대폰 인증 script (end)
 */

/*==========================================================================================================================================================
 * 기사 활동 구역 부산 (start)
 */
$(document).ready(function() {
    // 부산 전체 선택
    $("#selectAllBusan").change(function() {
        var isChecked = $(this).prop("checked");
        $(".busanDistrict").prop("checked", isChecked).prop("disabled", isChecked);
    });

    // 부산 구 선택
    $(".busanDistrict").change(function() {
        $("#selectAllBusan").prop("checked", false);
    });
});
/*
 * 기사 활동 구역 부산 (end)
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