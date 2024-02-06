IMP.init("imp00024650");


document.addEventListener("DOMContentLoaded", function() {
    const button = document.querySelector("#pay-button");
    const form = document.querySelector("#payment-form");
    const paycheckInput = document.querySelector("#paycheck");
    console.log("paycheckInput value: " + paycheckInput.value);  // 수정된 부분


    if (button) {
        button.addEventListener("click", async () => {
            const price = button.getAttribute("data-price");
            const itemname = button.getAttribute("data-itemname");
            const merchant_uid = "ORD" + Math.floor(Math.random() * 1000000);

            IMP.request_pay({
                pg: "nice_v2",
                pay_method: "card",
                amount: price,
                name: itemname,
                merchant_uid: merchant_uid,
                // m_redirect_url: "http://localhost:9000/shop",
            }, function(response) {
                if (response.success) {
                    // 결제가 성공하면 paycheck 값을 true로 변경
                    console.log("결제결과성공"+JSON.stringify(response));
                    paycheckInput.value = 1;
                    alert("결제에 성공했습니다.");
                } else {
                    console.log("결제결과실패"+JSON.stringify(response));
                    paycheckInput.value = 2;
                    alert("결제에 성공했습니다.");
                }
                // form을 서버로 제출할 수 있도록 코드를 alert() 함수 호출 뒤로 이동
                form.submit();
            });
        });
    } else {
        console.error("'pay-button' id를 가진 요소를 찾을 수 없습니다.");
    }
});