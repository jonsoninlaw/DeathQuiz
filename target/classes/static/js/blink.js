function blinkIt() {
    var blinks = document.getElementsByClassName("blink");
    for(var i = 0, l = blinks.length; i < l; i++){
        var blink = blinks[i];
        var visiblity = blink.style.visibility;
        blink.style.visibility = visiblity == 'visible' ? 'hidden' : 'visible';
    }
}

setInterval(blinkIt, 500 /* blinking interval in ms */);