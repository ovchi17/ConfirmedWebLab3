function updateTime(){
    let now = new Date();
    let clock = document.getElementById("clock");
    clock.innerHTML = now.toLocaleTimeString() + " " + now.toLocaleDateString();
}

window.onload = function(){
    updateTime();
    window.setInterval(function() {updateTime()}, 9000);
};