function handleCustomInputs() {
    // 모든 custom-select와 custom-input 요소를 쌍으로 처리
    const selects = document.querySelectorAll('.custom-select');
    const inputs = document.querySelectorAll('.custom-input');

    selects.forEach((select, index) => {
        // "custInputOption"이 선택된 경우 해당 input을 표시하고, 아니면 숨김
        inputs[index].style.display = (select.value === "custInputOption") ? "inline" : "none";
    });
}