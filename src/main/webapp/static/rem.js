//屏幕适配
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth>640?640:docEl.clientWidth;
            if (!clientWidth) return;

           //1rem = 10vw；

            docEl.style.fontSize = (clientWidth / 10) + 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
    doc.addEventListener('load', recalc, false);
})(document, window);