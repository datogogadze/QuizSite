$(document).ready(function() {
	$(".login-heading").css("display", "none");
	$(".login-form-holder").css("display", "none");
	$(".registration-heading").show();
	$(".registration-form-holder").show(600);
	$(".right-side").toggleClass('span-1-of-4 span-3-of-4');
	$(".left-side").toggleClass('span-3-of-4 span-1-of-4');
});