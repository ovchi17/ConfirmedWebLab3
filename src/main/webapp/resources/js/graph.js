function checker(x, y, r) {
    let resultF = "false";

    if (x <= 0 && y >= 0) {
        if (y <= r && x >= -1 * r / 2) {
            resultF = "true";
        }
    }

    if (x <= 0 && y <= 0) {
        if (x >= -r && y >= -r && x + y >= -r) {
            resultF = "true";
        }
    }

    if (x >= 0 && y >= 0) {
        if (x <= r && y <= r && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))) {
            resultF = "true";
        }
    }

    return resultF;
}

$(document).ready(function () {
    let R_button = $(".r-input")
    console.log(R_button.val())
    $(".XYcoord svg").click(function (event) {
        if (R_button) {
            const x = event.offsetX;
            const y = event.offsetY;
            const R = R_button.val();
            let normalizedX = parseFloat((((x - 200) * 2 * R) / 300).toFixed(2));
            let normalizedY = parseFloat((((200 - y) * 2 * R) / 300).toFixed(2));

            let point = document.createElementNS(
                "http://www.w3.org/2000/svg",
                "circle"
            );

            point.setAttribute("cx", x);
            point.setAttribute("cy", y);
            point.setAttribute("r", "3");
            if (checker(normalizedX, normalizedY, R)) {
                point.setAttribute("fill", "white");
            } else {
                point.setAttribute("fill", "black");
            }

            $("svg").append(point);

            console.log(`x: ${x}, normX: ${normalizedX}`)
            console.log(`y: ${y}, normY: ${normalizedY}`)
            // console.log(clicked_points[clicked_points.length - 1]);
        }
    });
});


// $(".window svg").click(function (event) {
//     let clicked_points = [];
//     if (R_button) {
//         const x = event.offsetX;
//         const y = event.offsetY;
//
//         const R = R_button.val();
//
//         let point = document.createElementNS(
//             "http://www.w3.org/2000/svg",
//             "circle"
//         );
//         point.setAttribute("cx", x);
//         point.setAttribute("cy", y);
//         point.setAttribute("r", "3");
//         point.setAttribute("fill", "blue");
//
//         $("svg").append(point);
//
//         let normalizedX = (((x - 200) * 2 * R) / 300).toFixed(2);
//         let normalizedY = (((200 - y) * 2 * R) / 300).toFixed(2);
//
//         console.log(`x: ${x}, normX: ${normalizedX}`);
//         console.log(`y: ${y}, normY: ${normalizedY}`);
//         console.log(clicked_points[clicked_points.length - 1]);
//
//         areaCheckGetRequest({ x: normalizedX, y: normalizedY, R: R }, true);
//     } else {
//         showError("Укажите параметр R!", 5000);
//     }
// });
// });