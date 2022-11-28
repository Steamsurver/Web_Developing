import * as elementCreator from '../elementCreator.js'

export default class NavPanelRender {
//*********************************************  

constructor(root){
	this.root = root
	this.navForm = undefined;
	this.navButtonContainer = undefined;
	this.settingsButton = undefined;
	this.catalogButton = undefined;
}


setRootPoint(root){
	this.root = root;
	
}


render() {
	
}


adminRender() {
	this.navForm = elementCreator.createForm("navForm", null);
	
	this.navButtonContainer = elementCreator.createDiv(null, null, null);
	this.navButtonContainer.setAttribute("class", "navPanel");
	
	this.settingsButton = elementCreator.createButton(null, null, "Настройки", null, "navButton");
	this.catalogButton = elementCreator.createButton(null, null, "Каталог", null, "navButton");
	
	
	this.navButtonContainer.appendChild(this.settingsButton);
	this.navButtonContainer.appendChild(this.catalogButton);
	this.navForm.appendChild(this.navButtonContainer);
	this.root.appendChild(this.navForm);
}
//*********************************************   

setNavEvents(events){
	this.settingsButton.addEventListener("click", events[0]);
	this.catalogButton.addEventListener("click", events[1]);
	
}


}