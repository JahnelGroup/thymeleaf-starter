import { clickHandler, blurHandler, mouseHandler, keyListener } from './controller.js'

document.body.addEventListener('click'      , clickHandler  )
document.body.addEventListener('focusout'   , blurHandler   )
document.body.addEventListener('mouseover'  , mouseHandler  )
document.body.addEventListener('mouseout'   , mouseHandler  )
document.body.addEventListener('keydown'    , keyListener   )
document.body.addEventListener('keyup'      , keyListener   )