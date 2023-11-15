let R = 1;
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

function drawPoints(xhr, status, args) {
    console.log(args.response);
    console.log(xhr.responseText)
    if (args && args.response && args.response !== '[]') {
        var points = JSON.parse(args.response);
        console.log(points);
        points.forEach(function(el) {
            let normalizedX = el.x;
            let normalizedY = el.y;
            // let r = el.r;
            let r = Number($(".r-input select").val());
            let posX = normalizedX * 300 / 2 / r + 200;
            let posY = 200 - normalizedY * 300 / 2 / r;
            let point = document.createElementNS(
                "http://www.w3.org/2000/svg",
                "circle"
            );
            point.setAttribute("cx", posX);
            point.setAttribute("cy", posY);
            point.setAttribute("r", "3");
            point.setAttribute("class", "graph-point");
            let checkStatus = checker(normalizedX, normalizedY, r);
            if (checkStatus) {
                point.setAttribute("fill", "white");
            } else {
                point.setAttribute("fill", "#e42575");
            }

            $("svg").append(point);
        });
    }else{
        console.log("no points");
    }
}

function rSetter () {
    R = $(".r-input select").val()
    console.log(R)
    $(".svgR").each(function(){
        $(this).text(R)
    })
    $(".svg-R").each(function(){
        $(this).text(-R)
    })
    $(".svgRd2").each(function(){
        $(this).text(R/2)
    })
    $(".svg-Rd2").each(function(){
        $(this).text(-R/2)
    })
}

$(document).ready(function() {
    rSetter()
    loadPoints();
    console.log("loadPoints");

    $(".XYcoord svg").click(function (event) {
        // if (R_button) {
        let R_button = $(".r-input select");
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
        point.setAttribute("class", "graph-point");
        let checkStatus = checker(normalizedX, normalizedY, R);
        if (checkStatus) {
            point.setAttribute("fill", "white");
        } else {
            point.setAttribute("fill", "#e42575");
        }

        $("svg").append(point);

        console.log(`x: ${x}, normX: ${normalizedX}`)
        console.log(`y: ${y}, normY: ${normalizedY}`)
        try {
            clickSender([{name: 'x', value: normalizedX}, {name: 'y', value: normalizedY}, {name: 'r', value: R}]);
        } catch (e) {
            console.log("clickSender")
        }
        // }
    });

    $("#clear").click(function(){
        $(".graph-point").each(function() {
            $(this).remove();
        });
    })

    $(".r-input select").change(function (){
        R = $(this).val();
        rSetter()
        $(".graph-point").each(function() {
            $(this).remove();
        });
        loadPoints()
    })

    $(document).on('click', '#conf', function(e) {
        console.log('Button clicked!');

        let Y_button = $(".y-input select");
        let R_button = $(".r-input select");
        let x = $('input[id$="x"]').val();
        let y = Y_button.val();
        let r = R_button.val();
        let numericX = parseFloat(x);
        if (!isNaN(numericX)) {
            let posX = x * 300 / 2 / r + 200;
            let posY = 200 - y * 300 / 2 / r;

            console.log("drawPoint")
            console.log(x, y, r)

            let point = document.createElementNS(
                "http://www.w3.org/2000/svg",
                "circle"
            );

            point.setAttribute("cx", posX);
            point.setAttribute("cy", posY);
            point.setAttribute("r", "3");
            point.setAttribute("class", "graph-point");
            let checkStatus = checker(x, y, r);
            if (checkStatus) {
                point.setAttribute("fill", "white");
            } else {
                point.setAttribute("fill", "#e42575");
            }

            $("svg").append(point);
        } else {
            console.log("x - error")
        }
    });
});