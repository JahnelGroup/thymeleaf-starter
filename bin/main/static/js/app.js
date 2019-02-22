import { clickHandler, blurHandler } from './controller.js'

document.body.addEventListener('click', clickHandler)
document.body.addEventListener('focusout', blurHandler)