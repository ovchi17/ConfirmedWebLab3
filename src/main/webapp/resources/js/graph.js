// jQuery(document).ready(function () {
//     jQuery(".XYcoord svg").click(function (event) {
//         const x = event.offsetX;
//         const y = event.offsetY;
//         const R = 1;
//         let normalizedX = (((x - 200) * 2 * R) / 300).toFixed(2);
//         let normalizedY = (((200 - y) * 2 * R) / 300).toFixed(2);
//
//         console.log(`x: ${x}, normX: ${normalizedX}`)
//         console.log(`y: ${y}, normY: ${normalizedY}`)
//     });
// });

document.addEventListener("DOMContentLoaded", function() {
    var svgElement = document.querySelector(".XYcoord svg");
    svgElement.addEventListener("click", function(event) {
        var x = event.offsetX;
        var y = event.offsetY;
        var R = 1;
        var normalizedX = (((x - 200) * 2 * R) / 300).toFixed(2);
        var normalizedY = (((200 - y) * 2 * R) / 300).toFixed(2);

        console.log("x: " + x + ", normX: " + normalizedX);
        console.log("y: " + y + ", normY: " + normalizedY);
    });
});