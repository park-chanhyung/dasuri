/*==========================================================================================================================================================
 * 기사 지역구 전체 선택 처리 (start)
 */

function toggleCheckboxes() {
    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[name="region"]');

    checkboxes.forEach(function (checkbox) {
        checkbox.disabled = selectAllCheckbox.checked;
        checkbox.checked = selectAllCheckbox.checked;
    });
}

document.getElementById("regionForm").addEventListener("submit", function (event) {
    event.preventDefault(); // 기존의 submit 동작 방지

    var selectAllCheckbox = document.getElementById("selectAll");
    var checkboxes = document.querySelectorAll('input[name="region"]:checked');

    // 선택된 지역들을 저장할 변수
    var selectedRegions = [];

    if (selectAllCheckbox.checked) {
        selectedRegions.push("전체");
    } else {
        checkboxes.forEach(function (checkbox) {
            selectedRegions.push(checkbox.value);
        });
    }

    // 선택된 지역들을 문자열로 합치기 (구분자: '/')
    var result = selectedRegions.join('/');

    // 결과 변수를 어딘가에 전달하거나 로직에 활용
    console.log('선택된 지역: ' + result);

    // 추가로 필요한 로직 수행 (예: 서버로 전송 등)
    // ...
});

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