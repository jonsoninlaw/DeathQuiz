var clignotement = function () {
    if (document.getElementById('blink').style.visibility == 'visible') {
        document.getElementById('blink').style.visibility = 'hidden';
    } else {
        document.getElementById('blink').style.visibility = 'visible';
    }
}
setInterval(clignotement, 500);