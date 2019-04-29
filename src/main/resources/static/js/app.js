const modules = [
    "./settings.js",
    "./controller.js"
];

const listeners = {
    'click'      :   'clickHandler',
    'focusout'   :   'blurHandler' ,
    'mouseover'  :   'mouseHandler',
    'mouseout'   :   'mouseHandler',
    'keydown'    :   'keyListener',
    'keyup'      :   'keyListener'
};

modules.forEach(function(mod){
    import(mod).then((mod) => {
        for (const [key, value] of Object.entries(listeners)) {
            if( mod[value] ){
                document.body.addEventListener(key, mod[value])
            }
        }
    });
});