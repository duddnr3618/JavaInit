function handleCustomInputs() {
    const selects = document.querySelectorAll('.custom-select');
    const inputs = document.querySelectorAll('.custom-input');

    selects.forEach((select, index) => {
        inputs[index].style.display = (select.value === "custInputOption") ? "inline" : "none";
        // 선택한 옵션을 직접 입력한 값으로 설정
        if (select.value !== "custInputOption") {
            inputs[index].value = ''; // 입력창을 초기화
        }
    });
}

function updateCustomInput(fieldName, value) {
    document.getElementById(fieldName).value = value;
}

function InputData () {
    document.getElementById("lossAmount").textContent = "";
    document.getElementById("lostSeed").textContent = "";
    document.getElementById("margin").textContent = "";
    document.getElementById("profitAmount").textContent = "";
    document.getElementById("profitedSeed").textContent = "";
    document.getElementById("adjustedProfit").textContent = "";
    document.getElementById("adjustedProfitSeed").textContent = "";
    // 시드
    const seed = document.getElementById("seed").value;
    // 손실율
    const lossRatio = document.getElementById("lossRatio").value;
    // 거래소 수수료
    const Exchange = document.getElementById("Exchange").value;
    var exchangeCharge = 0;
    if(Exchange =="비트겟"){
        exchangeCharge = 0.06;
    }else if(Exchange =="바이낸스"){
        exchangeCharge = 0.1;
    }else {
        exchangeCharge = 0.1;
    }

    // 손익비
    const profitToLossRatio = document.getElementById("profitToLossRatio").value;
    // 손절선
    const lossLine = document.getElementById("lossLine").value;
    // 익절선
    const profitLine = parseFloat(lossLine) * parseFloat(profitToLossRatio)
    document.getElementById("profitLine").textContent = profitLine.toFixed(2)
    // 실제 손실
    const lossAmount = parseFloat(seed) * parseFloat(lossRatio) * 0.01;
    document.getElementById("lossAmount").textContent = lossAmount.toFixed(0);
    document.getElementById("hiddenLossAmount").value = lossAmount.toFixed(0);
    // 손실시 시드
    const lostSeed = parseFloat(seed) - lossAmount;
    document.getElementById("lostSeed").textContent = lostSeed.toFixed(0) + "usdt";
    // 증거금
    const margin = (lossAmount / (parseFloat(lossLine) + parseFloat(exchangeCharge))) * 100;
    document.getElementById("margin").textContent = margin.toFixed(1);
    document.getElementById("hiddenMargin").value = margin.toFixed(1);
    // 실제 이익
    const profitAmount = margin * profitLine * 0.01;
    document.getElementById("profitAmount").textContent = profitAmount.toFixed(1);
    document.getElementById("hiddenProfitAmount").value = profitAmount.toFixed(1);
    // 수익시 시드
    const profitedSeed = parseFloat(seed) + profitAmount;
    document.getElementById("profitedSeed").textContent = profitedSeed.toFixed(0)+"usdt"
    // 보정 이익
    const adjustedProfit = lostSeed * profitToLossRatio;
    document.getElementById("adjustedProfit").textContent = adjustedProfit.toFixed(0);
    // 보정시 시드
    const adjustedProfitSeed =  parseFloat(seed) + adjustedProfit;
    document.getElementById("adjustedProfitSeed").textContent = adjustedProfitSeed.toFixed(0) + "usdt";


    console.log("profitAmount",profitAmount)
    console.log("lossAmount",lossAmount)
}

document.addEventListener("DOMContentLoaded", function() {
    const intervalId = setInterval(() => {
        if (window.userInfo) {
            // userInfo가 정의되면 값 설정
            var userEmail = document.getElementById("userEmail").value = window.userInfo.userEmail;
            clearInterval(intervalId); // 설정 후 인터벌 중지

        } else {
            console.log("userInfo가 아직 정의되지 않았습니다.");
        }
    }, 100); // 100ms마다 확인
});





