function checker(x, y, r) {
    let resultF = false;

    if (x <= 0 && y >= 0) {
        if (y <= r && x >= -1 * r / 2) {
            resultF = true;
        }
    }

    if (x <= 0 && y <= 0) {
        if (x >= -r && y >= -r && Number(x) + Number(y) >= -r) {
            resultF = true;
        }
    }

    if (x >= 0 && y >= 0) {
        if (x <= r && y <= r && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) {
            resultF = true;
        }
    }
    return resultF;
}

$(document).ready(function () {
    let R_button = $(".r-input select");
    $(".XYcoord svg").click(function (event) {
        if (R_button) {
            const x = event.offsetX;
            const y = event.offsetY;
            const R = R_button.val();

            let normalizedX = (((x - 200) * 2 * R) / 300).toFixed(2);
            let normalizedY = (((200 - y) * 2 * R) / 300).toFixed(2);

            let point = document.createElementNS(
                "http://www.w3.org/2000/svg",
                "circle"
            );

            point.setAttribute("cx", x);
            point.setAttribute("cy", y);
            point.setAttribute("r", "3");
            let checkStatus = checker(normalizedX, normalizedY, R);
            if (checkStatus) {
                point.setAttribute("fill", "white");
            } else {
                point.setAttribute("fill", "#e42575");
            }

            $("svg").append(point);

            console.log(`x: ${x}, normX: ${normalizedX}`)
            console.log(`y: ${y}, normY: ${normalizedY}`)
        }
    });
});