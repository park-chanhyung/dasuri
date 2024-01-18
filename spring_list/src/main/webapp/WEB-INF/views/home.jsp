<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link rel="stylesheet" href="style.css" >
</head>
<body>

<header>
      <!-- 배너 칸입니다. 나중에 활용할수도있으니 남겨놓음 -->
    <!-- <div class="banner">Get free delivery on orders over $80</div> -->
    <nav>
          
        <div id="logo">
           <!-- 로고 사진 -->
            <svg width="40" height="32" viewBox="0 0 40 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M12.8 32H0V21.9849C0 17.1239 4.0116 13.1839 8.96 13.1839H15.2V0.468341C15.2 0.251458 15.3792 0.0754376 15.6 0.0754376C15.7184 0.0754376 15.8308 0.127301 15.9068 0.216883L17.3616 1.92719C18.402 1.07577 19.74 0.563816 21.2 0.563816H22C23.442 0.563816 24.7656 1.06359 25.8 1.89654L27.2932 0.14066C27.3692 0.0514706 27.4812 0 27.6 0C27.8208 0 28 0.175628 28 0.392904V15.3896H23.36C17.528 15.3896 12.8 20.0337 12.8 25.7623V32Z" fill="#45413E" />
                <path fill-rule="evenodd" clip-rule="evenodd" d="M29.76 17.0569V12.0875C29.76 12.0836 29.76 12.0797 29.76 12.0758V6.79004C29.76 6.57435 29.9392 6.4 30.16 6.4C30.266 6.4 30.3676 6.44095 30.4424 6.51389L31.9576 7.98784C32.7864 7.42501 33.7936 7.09504 34.88 7.09504C35.97 7.09504 36.9808 7.42735 37.8112 7.99369L39.3176 6.52793C39.3924 6.45499 39.494 6.41404 39.6 6.41404C39.8208 6.41404 40 6.58878 40 6.80408V23.2632C40 28.0883 35.9884 32 31.04 32H14.4V25.7938C14.4 20.969 18.4116 17.0569 23.36 17.0569H29.76Z" fill="#45413E" />
            </svg>
            <!-- 프로젝트 이름칸 -->
            3팀 <br> 프로젝트
        </div>
        <!-- - **내정보 / 로그인 / 로그아웃**
- **관리자홈**
- **공지 / 고객센터**
- **피드(전문가찾기)**
- **검색창** -->
        <ul class="navigation-menu">
            
            <li><a href="notice.html">공지사항</a>
                <!-- 누르면 카드 나오는 코드 -->
<ul class="subnav">
    <li class="card-med" id="sup-dog">

                    </li>
                </ul> 


                <li><a href="proinfo.html">전문가 찾기</a>
                </li>

            <li><a href="servicecenter.html">고객센터</a>
            </li>
            <li>
                <a href="#">About Us</a>
            </li>
        </ul>
        <div id="utility">
            <span class="material-symbols-outlined">
              <a>내정보</a> 
            </span>
            <span class="material-symbols-outlined">
                <a>로그인</a> 
            </span>
            <span class="material-symbols-outlined">
                <a>회원가입</a> 
            </span>
        </div>
    </nav>
</header>
<section class="hero">
    <h1>Your One-Stop Shop for Every Pet's Needs!</h1>
    <div class="btn-group">
        <button class="btn-filled-dark"><span class="material-symbols-outlined">
                shopping_cart
            </span>Shop All Products</button>
        <button class="btn-outline-dark btn-hover-color"><span class="material-symbols-outlined">
                calendar_month
            </span> Book a Service</button>
    </div>

</section>
<section>
    <h2>이달의 인기 기사님</h2>

    <ul class="shop-pets">
        <li class="card-large card-light" id="sup-dog">
            <div class="card-image"><img src="data:image/jpeg;base64,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"></div>
            <ul>
                박찬형
                <li><a href="#">부산 & 울산</a></li>
                <li><a href="#">와이파이</a></li>
                <li><a href="#">컴퓨터 조립 </a></li>
                <li><a href="#">TV</a></li>
                <li><a href="#">Clothing</a></li>

                <button class="btn-outline-light">Shop All<span class="material-symbols-outlined">
                        arrow_forward
                    </span></button>

            </ul>


        </li>

        <li class="card-large card-dark" id="sup-cat">
            <div class="card-image"><img src="https://ouch-cdn2.icons8.com/RjiKOF2gGKiIVnIMFi0O1a4aU7DoHfhbkXr2JbUYZ3A/rs:fit:368:313/czM6Ly9pY29uczgu/b3VjaC1wcm9kLmFz/c2V0cy9wbmcvMzEy/LzliNDQ3MmVlLWZh/YjMtNDQwNy1iOWVh/LWMwOTdlYWNjNWE3/NS5wbmc.png"></div>
            <ul>Cats
                <li><a href="#">Food &amp; Treats</a></li>
                <li><a href="#">Toys</a></li>
                <li><a href="#">Beds &amp; Furniture</a></li>
                <button class="btn-outline-dark">Shop All<span class="material-symbols-outlined">
                        arrow_forward
                    </span></button>
            </ul>

        </li>

        <li class="card-large card-dark" id="sup-bird">
            <div class="card-image"><img src="https://ouch-cdn2.icons8.com/DF-XRInvbvWS9fQSpWc_SegC3meXZK8BmE-PjrdrF3Q/rs:fit:368:396/czM6Ly9pY29uczgu/b3VjaC1wcm9kLmFz/c2V0cy9wbmcvNzI3/LzQyYWIyNzliLWJj/ZDgtNGEyMC04MGRi/LTk3MzU4YWFmNTVk/OS5wbmc.png"> </div>
            <ul>Birds
                <li><a href="#">Food &amp; Treats</a></li>
                <li><a href="#">Toys</a></li>
                <li><a href="#">Furniture</a></li>
                <button class="btn-outline-dark">Shop All<span class="material-symbols-outlined">
                        arrow_forward
                    </span></button>
            </ul>

        </li>
        <li class="card-large card-light" id="sup-fish">
            <div class="card-image"><img src="https://ouch-cdn2.icons8.com/41Pv7w9rcbn7II_gB2vwvVCQRYE5mvpca1ZbsvMujR0/rs:fit:368:368/czM6Ly9pY29uczgu/b3VjaC1wcm9kLmFz/c2V0cy9wbmcvNjE5/LzRlZjE1YTgyLTI3/NjYtNDlkNC1hMGE3/LWY4ZjRmNzhjM2M5/NS5wbmc.png"></div>
            <ul>
                Fish
                <li><a href="#">Food</a></li>
                <li><a href="#">Aquariums</a></li>
                <li><a href="#">Rocks &amp; Decorations</a></li>
                <button class="btn-outline-light">Shop All<span class="material-symbols-outlined">
                        arrow_forward
                    </span></button>
            </ul>

        </li>
    </ul>
</section>

<section>
    <h2>Our Services</h2>

    <ul class="services">
        <li class="card-large card-dark card-wide" id="serv-groom">
            <div class="card-image"><img src="https://ouch-cdn2.icons8.com/T11rfGmMKgcStJyAFKNgtOfE79cadabx0DVMnvzA9Pk/rs:fit:368:313/czM6Ly9pY29uczgu/b3VjaC1wcm9kLmFz/c2V0cy9wbmcvNDQx/LzFlYWU4MWY3LWQ1/ZjYtNDM2Ny1hZjM5/LWVmNTFmMGM5Njk4/MS5wbmc.png"></div>
            <ul>
                Dog Grooming<span class="subtitle">Tail-wagging transformations are our specialty.</span>
                <li><a href="#">Coat Care</a><span>$80</span></li>
                <li><a href="#">Nail Care</a><span>$16</span></li>
                <li><a href="#">Doggie Deluxe Spa Day</a><span>$160</span></li>
                <button class="btn-filled-dark"><span class="material-symbols-outlined">
                        calendar_month
                    </span>Book Now</button>

            </ul>


        </li>
        <li class="card-large card-dark card-wide" id="serv-board">
            <div class="card-image"><img src="https://ouch-cdn2.icons8.com/F5Ea1suZtMYimKDkJr0CJLO_1bju6-bTyT1EuDKEg8s/rs:fit:368:254/czM6Ly9pY29uczgu/b3VjaC1wcm9kLmFz/c2V0cy9wbmcvMjcx/LzVjMzE4NWM0LWZh/NTMtNGQ1OS05ZTM2/LTZjYzBhNGU3ODg0/NC5wbmc.png"></div>
            <ul>
                Dog Boarding<span class="subtitle">Where fun and care never take a day off.</span>
                <li><a href="#">Doggie Daycare</a><span>$80</span></li>
                <li><a href="#">Short Term Boarding</a><span>$80</span></li>
                <button class="btn-filled-dark"><span class="material-symbols-outlined">
                        calendar_month
                    </span>Book Now</button>
            </ul>

        </li>
    </ul>
</section>

<section id="locate">

    <div>
        <h2>Location &amp; Hours</h2>
        <p>Our knowledgeable and friendly staff is always ready to assist you in making the best choices for your furry, feathered, or finned friends.</p>
        <div class="btn-group">
            <button class="btn-filled-dark"><span class="material-symbols-outlined">
pin_drop
</span>Find a Store</button>
            <button class="btn-outline-dark btn-hover-color"><span class="material-symbols-outlined">
contact_support
</span> Contact Us</button>
        </div>
    </div>
</section>

<footer>

    <ul>
        Products
        <li><a href="#">Food &amp; Treats</a></li>
        <li><a href="#">Toys</a></li>
        <li><a href="#">Beds &amp; Furniture</a></li>
        <li><a href="#">Outdoor Supplies</a></li>
        <li><a href="#">Clothing</a></li>
        <li><a href="#">Aquariums</a></li>
        <li><a href="#">Rocks &amp; Decorations</a></li>
    </ul>

    <ul>
        Shop by Pet
        <li><a href="#">Dogs</a></li>
        <li><a href="#">Cats</a></li>
        <li><a href="#">Birds</a></li>
        <li><a href="#">Fish</a></li>
    </ul>


    <ul>
        Our Services
        <li><a href="#">Grooming</a></li>
        <li><a href="#">Boarding</a></li>
    </ul>
    <ul>
        Our Company
        <li><a href="#">Locations &amp; Hours</a></li>
        <li><a href="#">About Us</a></li>
    </ul>


</footer>
</body>
</html>