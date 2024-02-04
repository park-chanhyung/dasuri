<!--지역 회원수 top3 (고객) -->
var ctx = document.getElementById('myChart_loc').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: [userLocName0, userLocName1, userLocName2],
        datasets: [{
            data: [userLocAmount0, userLocAmount1, userLocAmount2],
            backgroundColor: ['rgba(178, 34, 34, 1)','rgba(178, 34, 34, 0.6)','rgba(178, 34, 34, 0.3)'],
            borderWidth: 0 // 막대 테두리 없앰
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        legend: {
            display: false // 레전드 숨기기
        }
    }
});


<!-- 유형별 비율 (원 그래프) -->
    var context1 = document.getElementById('myChart1').getContext('2d');
    var myChart1 = new Chart(context1, {
    type: 'pie',
    data: {
        labels: ['고객', '기사'],
        datasets: [{
        data: [userCount, proCount],
        backgroundColor: ['rgba(52, 152, 219, 0.5)',
            'rgba(241, 196, 15, 0.5)'],
        borderColor: [
            'rgba(52, 152, 219, 0.8)',
            'rgba(241, 196, 15, 0.8)'
        ],
        borderWidth: 1
        }]
    },
    options: {
    legend: {
    display: false
}
}
});

<!--신규 가입 수-->
var myChart5 = new Chart(document.getElementById('myChart5').getContext('2d'), {
    type: 'bar',
    data: {
        labels: ['6일 전','5일 전','4일 전','3일 전','2일 전','어제','오늘'],
        datasets: [
            {
                data: [day6, day5, day4, day3, day2, day1, day0],
                backgroundColor: [
                    'rgba(65, 72, 193, 0.08)',
                    'rgba(65, 72, 193, 0.15)',
                    'rgba(65, 72, 193, 0.3)',
                    'rgba(65, 72, 193, 0.45)',
                    'rgba(65, 72, 193, 0.6)',
                    'rgba(65, 72, 193, 0.75)',
                    'rgba(65, 72, 193, 0.85)'
                ],
                borderColor: 'rgba(65, 72, 193, 1)',
                borderWidth: 1
            }
        ]
    },
    options: {
        legend: {
            display: false
        },
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true
                    }
                }
            ]
        }
    }
});