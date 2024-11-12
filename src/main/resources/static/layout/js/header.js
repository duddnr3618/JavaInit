// 전역 변수 선언
window.userInfo = null;

document.addEventListener("DOMContentLoaded", function() {
    axios.get('/api/user/info')
        .then(response => {
            const userInfoData = response.data;

            // 사용자 정보를 전역 변수에 저장
            window.userInfo = userInfoData;
            console.log("header --->> ", window.userInfo)

            if (userInfoData.userName) {
                // 사용자 정보를 헤더에 표시
                const userGreeting = document.createElement("p");
                userGreeting.innerText = `안녕하세요, ${userInfoData.userName}님!`;
                userGreeting.className = "user-greeting";
                document.querySelector(".navBar").appendChild(userGreeting);

                document.getElementById("loginLink").style.display = "none";
                document.getElementById("logoutLink").style.display = "block";
            } else {
                document.getElementById("loginLink").style.display = "block";
                document.getElementById("logoutLink").style.display = "none";
            }
        })
        .catch(error => {
        });
});
