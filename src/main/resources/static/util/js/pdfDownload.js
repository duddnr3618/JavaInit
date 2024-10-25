// PDF 임시 저장 함수
function PdfSave() {
    const inputs = document.querySelectorAll(".container input"); // PDF 내 모든 input 태그 선택
    const inputData = {};

    // 각 input의 id와 값을 객체로 저장
    inputs.forEach(input => {
        inputData[input.id] = input.value;
    });

    // localStorage에 JSON 형식으로 저장
    localStorage.setItem("pdfInputData", JSON.stringify(inputData));
    alert("PDF 데이터가 임시 저장되었습니다.");
}

// PDF 데이터 불러오기 함수
function PdfSavedLoad() {
    const savedData = localStorage.getItem("pdfInputData");

    if (savedData) {
        const inputData = JSON.parse(savedData);
        const inputs = document.querySelectorAll(".container input");

        // 저장된 데이터가 있는 경우 각 input에 값 설정
        inputs.forEach(input => {
            if (inputData[input.id]) {
                input.value = inputData[input.id];
            }
        });
        alert("임시 저장된 데이터를 불러왔습니다.");
    } else {
        alert("저장된 데이터가 없습니다.");
    }
}


// 현재 페이지 번호를 저장할 변수
let currentPage = 1;

// 전체 페이지 개수
const totalPages = 3;

// 페이지를 전환하여 보여주는 함수
function showPage(pageNumber) {
    const pages = document.querySelectorAll(".container > div");

    pages.forEach((page, index) => {
        if (index + 1 === pageNumber) {
            page.style.display = "block"; // 현재 페이지 표시
        } else {
            page.style.display = "none"; // 다른 페이지 숨김
        }
    });

    // 현재 페이지 번호 업데이트
    document.querySelector(".currentPage").textContent = `${pageNumber} / ${totalPages}`;
}

// 이전 페이지로 이동
function prePageBtn() {
    if (currentPage > 1) {
        currentPage -= 1;
        showPage(currentPage);
    }
}

// 다음 페이지로 이동
function nextPageBtn() {
    if (currentPage < totalPages) {
        currentPage += 1;
        showPage(currentPage);
    }
}

// 현재 페이지를 PDF로 다운로드
function PdfDownload() {
    const currentContent = document.querySelector(`.page-${currentPage}`);

    // html2pdf 라이브러리를 사용하여 PDF 생성
    html2pdf()
        .set({
            margin: -1,
            filename: `Page-${currentPage}.pdf`,
            html2canvas: { scale: 2 },
            jsPDF: { orientation: 'portrait' }
        })
        .from(currentContent)
        .save();
}

// 현재 페이지를 인쇄
function PdfPrint() {
    const currentContent = document.querySelector(`.page-${currentPage}`).outerHTML;

    // 새 창을 열어 현재 페이지 내용만 출력
    const printWindow = window.open('', '_blank');
    printWindow.document.open();
    printWindow.document.write(`
        <html>
            <head>
                <title>Print Page-${currentPage}</title>
                <style>
                    body { font-family: Arial, sans-serif; text-align: center; }
                    .page { width: 210mm; height: 297mm; }
                </style>
            </head>
            <body onload="window.print(); window.close();">
                ${currentContent}
            </body>
        </html>
    `);
    printWindow.document.close();
}

// 페이지 로딩 시 기본적으로 첫 번째 페이지 표시
document.addEventListener("DOMContentLoaded", () => {
    showPage(currentPage);
});


function PdfAllDownload() {
    // 개별 페이지를 선택하여 모두 추가
    const pages = [
        document.querySelector('.page-1'),
        document.querySelector('.page-2'),
        document.querySelector('.page-3')
    ];

    // 페이지들을 담을 가상 컨테이너 생성
    const container = document.createElement('div');

    // 모든 페이지를 클론하여 컨테이너에 추가
    pages.forEach(page => {
        const clonedPage = page.cloneNode(true); // 페이지 클론
        clonedPage.style.pageBreakAfter = "always"; // 페이지 구분을 위해 CSS 추가
        container.appendChild(clonedPage); // 컨테이너에 추가
    });

    // html2pdf 설정 및 PDF 다운로드
    html2pdf()
        .set({
            margin: 1,
            filename: 'AllPages.pdf',
            image: { type: 'jpeg', quality: 0.98 },
            html2canvas: { scale: 2 },
            jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
        })
        .from(container) // 컨테이너를 PDF로 변환
        .save();
}
