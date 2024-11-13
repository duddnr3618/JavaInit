function toggleLoginMenu() {
    const loginMenu = document.querySelector('.loginMenu');
    loginMenu.style.display = loginMenu.style.display === 'none' || loginMenu.style.display === '' ? 'block' : 'none';
}

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
