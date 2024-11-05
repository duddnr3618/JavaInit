function handleCustomInputs() {
    // 모든 custom-select와 custom-input 요소를 쌍으로 처리
    const selects = document.querySelectorAll('.custom-select');
    const inputs = document.querySelectorAll('.custom-input');

    selects.forEach((select, index) => {
        // "custInputOption"이 선택된 경우 해당 input을 표시하고, 아니면 숨김
        inputs[index].style.display = (select.value === "custInputOption") ? "inline" : "none";
    });
}

function InputData () {
    document.getElementById("actualLoss").textContent = "";
    document.getElementById("lostSeed").textContent = "";
    document.getElementById("margin").textContent = "";
    document.getElementById("actualProfit").textContent = "";
    document.getElementById("profitedSeed").textContent = "";
    document.getElementById("adjustedProfit").textContent = "";
    document.getElementById("adjustedProfitSeed").textContent = "";
    // 시드
    const seed = document.getElementById("seed").value;
    // 손실율
    const lossRatio = document.getElementById("lossRatio").value;
    // 거래소 수수료
    const Exchange = document.getElementById("Exchange").value;
    // 손익비
    const profitToLossRatio = document.getElementById("profitToLossRatio").value;
    //손절선
    const lossLine = document.getElementById("lossLine").value;
    // 익절선
    const profitLine = parseFloat(lossLine) * parseFloat(profitToLossRatio)
    document.getElementById("profitLine").textContent = profitLine.toFixed(2)
    // 실제 손실
    const actualLoss = parseFloat(seed) * parseFloat(lossRatio) * 0.01;
    document.getElementById("actualLoss").textContent = actualLoss.toFixed(0) + "usdt";
    // 손실시 시드
    const lostSeed = parseFloat(seed) - actualLoss;
    document.getElementById("lostSeed").textContent = lostSeed.toFixed(0) + "usdt";
    // 증거금
    const margin = (actualLoss / (parseFloat(lossLine) + parseFloat(Exchange))) * 100;
    document.getElementById("margin").textContent = margin.toFixed(1);
    // 실제 이익
    const actualProfit = margin * profitLine * 0.01;
    document.getElementById("actualProfit").textContent = actualProfit.toFixed(2);
    // 수익시 시드
    const profitedSeed = parseFloat(seed) + actualProfit;
    document.getElementById("profitedSeed").textContent = actualProfit.toFixed(0)+"usdt"
    // 보정 이익
    const adjustedProfit = lostSeed * profitToLossRatio;
    document.getElementById("adjustedProfit").textContent = adjustedProfit.toFixed(0)+"usdt"
    // 보정시 시드
    const adjustedProfitSeed =  parseFloat(seed) + adjustedProfit;
    document.getElementById("adjustedProfitSeed").textContent = adjustedProfitSeed.toFixed(0) + "usdt";
    
    
    
}

