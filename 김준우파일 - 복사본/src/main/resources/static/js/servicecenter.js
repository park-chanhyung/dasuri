$(document).ready(function(){
    // ajax json list
    var boardListArray = [
        {
            link : '#1',
            title : 'TITLE1',
            date : '2021-09-23'
        },
        {
            link : '#2',
            title : 'TITLE2',
            date : '2021-09-23'
        },
        {
            link : '#3',
            title : 'TITLE3',
            date : '2021-09-23'
        },
        {
            link : '#4',
            title : 'TITLE4',
            date : '2021-09-23'
        },
        {
            link : '#5',
            title : 'TITLE5',
            date : '2021-09-23'
        },
        {
            link : '#6',
            title : 'TITLE6',
            date : '2021-09-23'
        },
        {
            link : '#7',
            title : 'TITLE7',
            date : '2021-09-23'
        },
        {
            link : '#8',
            title : 'TITLE8',
            date : '2021-09-23'
        },
        {
            link : '#9',
            title : 'TITLE9',
            date : '2021-09-23'
        },
        {
            link : '#10',
            title : 'TITLE10',
            date : '2021-09-23'
        },
        {
            link : '#11',
            title : 'TITLE11',
            date : '2021-09-23'
        },
        {
            link : '#12',
            title : 'TITLE12',
            date : '2021-09-23'
        },
        {
            link : '#13',
            title : 'TITLE13',
            date : '2021-09-23'
        },
        {
            link : '#14',
            title : 'TITLE14',
            date : '2021-09-23'
        },
        {
            link : '#15',
            title : 'TITLE15',
            date : '2021-09-23'
        },
    ];

    // board items
    var boardCount = 5,
        boardList = $('.board-list'),
        boardPager = $('.board-pager'),
        listMin = 0,
        boardPagerFirst = '<button class="first">첫페이지</button><button class="prev">이전페이지</button>',
        boardPagerLast = '<button class="next">다음페이지</button><button class="last">마지막페이지</button>';

    // board list set
    boardList.empty();
    for( var x = 0 ; x < boardCount ; x++ ){
        boardList.append('<li><a href="' + boardListArray[x].link + '"><span class="subject">' + boardListArray[x].title + '</span><span class="date">' + boardListArray[x].date + '</span></a></li>');
    }

    // board pager set
    boardPager.empty();
    for( var i = 0 ; i < ( boardListArray.length / 5 ) ; i++ ){
        if( i == 0 ){
            boardPager.append(boardPagerFirst + '<a href="#">' + ( i + 1 ) + '</a>');
        }else if( i == ( ( boardListArray.length / 5 ) - 1 ) ){
            boardPager.append('<a href="#">' + ( i + 1 ) + '</a>' + boardPagerLast);
        }else{
            boardPager.append('<a href="#">' + ( i + 1 ) + '</a>');
        }
    };
    boardPager.find('a').eq(0).addClass('active');

    // pager click event
    $(document).on('click', '.board-pager a', function(){
        boardList.empty();
        for( var y = ( $(this).text() - 1 ) * boardCount ; y < ( boardCount * $(this).text() ) ; y++ ){
            boardList.append('<li><a href="' + boardListArray[y].link + '"><span class="subject">' + boardListArray[y].title + '</span><span class="date">' + boardListArray[y].date + '</span></a></li>');
        }
        $('.board-pager a').removeClass('active');
        $(this).addClass('active');
        return false;
    });

    // pager arrows click event
    $(document).on('click', '.board-pager button', function(){
        if( $(this).hasClass('first') ){
            // first page
            listMin = 0;
        }else if( $(this).hasClass('last') ){
            // last page
            listMin = boardListArray.length / 5 - 1;
        }else if( $(this).hasClass('prev') ){
            // prev page
            if( listMin <= 0 ){
                listMin = 0
            }else{
                listMin--;
            }
        }else if( $(this).hasClass('next') ){
            // next page
            if( listMin >= ( boardListArray.length / 5 - 1 ) ){
                listMin = boardListArray.length / 5 - 1;
            }else{
                listMin++;
            }
        }

        $('.board-pager a').removeClass('active').eq(listMin).addClass('active');
        boardList.empty();
        for( var index = ( listMin * boardCount ) ; index < ( boardCount * ( listMin + 1 ) ) ; index++ ){
            boardList.append('<li><a href="' + boardListArray[index].link + '"><span class="subject">' + boardListArray[index].title + '</span><span class="date">' + boardListArray[index].date + '</span></a></li>');
        }
    });


});