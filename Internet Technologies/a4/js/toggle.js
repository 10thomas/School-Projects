if (typeof (localStorage) == 'undefined') {
    document.getElementById("result").innerHTML = 
'Your browser does not support HTML5 localStorage. Try upgrading.';
} else {
    if (localStorage.getItem("fav") != null) {
        getFav = localStorage.fav;
        $(".item").addClass('favorites');
    }
}
$(document).ready(function () {
    $('.btn').on('click', function () {
        getFav = localStorage.fav;
        //$(".item").removeClass('favorites');
        //localStorage.removeItem('background');
        $(this).closest(".item").toggleClass('favorites');
		if ($(this).closest(".item").hasClass('favorites')) {
        localStorage.setItem('fav', 'favorites');
        
    }else{
		localStorage.removeItem('fav');
	}
        
    });
});