document.addEventListener('DOMContentLoaded', function () {
    var ckElements = document.querySelectorAll('.faq_table .ck');

    ckElements.forEach(function (ckElement) {
        ckElement.addEventListener('click', function () {
            // 클릭된 요소의 부모(tr)를 찾기
            var parentTr = ckElement.parentElement;

            // 부모의 다음 sibling에 있는 hide 클래스를 가진 tr 찾기
            var nextTr = parentTr.nextElementSibling;

            // nextTr이 null이 아닌 경우에만 toggle 기능 수행
            if (nextTr) {
                nextTr.classList.toggle('hide');
                nextTr.style.backgroundColor = (nextTr.classList.contains('hide')) ? '' : '#eeeeee';
                nextTr.style.paddingTop = (nextTr.classList.contains('hide')) ? '' : '30px';
                nextTr.style.paddingBottom = (nextTr.classList.contains('hide')) ? '' : '30px';
            }
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var hashTagList = document.getElementById('hashTagList');
    var hashTags = hashTagList.getElementsByClassName('hashTag');

    // 랜덤한 색상을 생성하는 함수 (투명도를 50%로 설정)
    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = 'rgba(';
        for (var i = 0; i < 3; i++) {
            color += Math.floor(Math.random() * 256) + ',';
        }
        color += '0.3)';
        return color;
    }

    // 각 hashTag에 랜덤한 배경색을 적용
    Array.from(hashTags).forEach(function (hashTag) {
        var newDiv = document.createElement('div');
        newDiv.style.backgroundColor = getRandomColor();
        newDiv.innerHTML = hashTag.innerHTML;
        newDiv.className = 'hashTagStyled';  // 추가된 랜덤 배경색이 적용된 hashTag의 클래스
        hashTag.innerHTML = '';  // 기존의 hashTag 비우기
        hashTag.appendChild(newDiv);  // 새로 생성한 div를 추가
    });
});