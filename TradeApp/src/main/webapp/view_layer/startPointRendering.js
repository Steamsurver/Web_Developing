import view_module_controller from '../view_layer/view_module_controller.js'
let root = document.getElementById('root_point');
let message = document.getElementById('massageField');

view_module_controller.init(root, message);
view_module_controller.startHandler();
