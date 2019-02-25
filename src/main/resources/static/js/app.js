import { clickHandler, blurHandler, mouseHandler } from './controller.js'

document.body.addEventListener('click', clickHandler)
document.body.addEventListener('focusout', blurHandler)
document.body.addEventListener('mouseover', mouseHandler )
document.body.addEventListener('mouseout', mouseHandler )
// TODO: on load?