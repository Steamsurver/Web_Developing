function triggerSignInForm(){
	var root = document.getElementById('root_point');
    signInRendering.renderSignIn(root);
}

function triggerSignUpForm(){
	var root = document.getElementById('root_point');
    signUpRendering.renderSignUp(root);
}

function triggerCatalogForm(game_catalog){
	var root = document.getElementById('root_point');
    catalogRendering.renderCatalog(root, game_catalog);
}