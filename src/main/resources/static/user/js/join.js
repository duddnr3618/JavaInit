// 비밀번호와 비밀번호 확인 필드의 일치 여부를 검사하는 함수
function validatePassword(event) {
    const password = document.getElementById("password").value;
    const password2 = document.getElementById("password2").value;

    if (password !== password2) {
        alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        event.preventDefault(); // 폼 제출 방지
        return false;
    }
    return true;
}

// 페이지 로드 후 폼에 이벤트 리스너 추가
document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    form.addEventListener("submit", validatePassword);
});
