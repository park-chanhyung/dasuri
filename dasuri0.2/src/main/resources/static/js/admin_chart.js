<!--바 그래프-->
    var context2 = document
    .getElementById('myChart2')
    .getContext('2d');
    var myChart2 = new Chart(context2, {
    type: 'bar', // 차트의 형태
    data: { // 차트에 들어갈 데이터
    labels: [
    //x 축
    '금정구','부산진구','동래구','북구','수영구'
    ],
    datasets: [
{ //데이터
  // label: '', //차트 제목
  // fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
    data: [
    43,37,31,24,19 //x축 label에 대응되는 데이터 값
    ],
    backgroundColor: [
    //색상
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    ],
    borderColor: [
    //경계선 색상
    'rgba(255, 99, 132, 1)',
    'rgba(54, 162, 235, 1)',
    'rgba(255, 206, 86, 1)',
    'rgba(75, 192, 192, 1)',
    'rgba(153, 102, 255, 1)',
    ],
    borderWidth: 0 //경계선 굵기
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



<!--원 그래프-->
var context1 = document
    .getElementById('myChart1')
    .getContext('2d');
var myChart1 = new Chart(context1, {
    type: 'pie', // 차트의 형태
    data: { // 차트에 들어갈 데이터
        labels: [
            //x 축
            '고객','기사'
        ],
        datasets: [
            { //데이터
              // fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                data: [
                    300,100 //x축 label에 대응되는 데이터 값
                ],
                backgroundColor: [
                    //색상
                    'rgba(154, 205, 50, 1)',
                    'rgba(219, 112, 147, 1)',
                ],
                borderWidth: 0 //경계선 굵기
            }
        ]
    },
    options: {
        legend: {
            display: false
        }
    }
});