/**
 * WEBSITE: https://themefisher.com
 * TWITTER: https://twitter.com/themefisher
 * FACEBOOK: https://www.facebook.com/themefisher
 * GITHUB: https://github.com/themefisher/
 */


(function ($) { 
	'use strict';
	/* ----------------------------------------------------------- */
	/*  Site search
	/* ----------------------------------------------------------- */

 // overlay search

    $('.search_toggle').on('click', function(e) {
        e.preventDefault();
        $('.search_toggle').toggleClass('active');
        $('.overlay').toggleClass('open');
        setTimeout(function(){
            $('.search-form .form-control').focus();
        },400);

    });

 // instafeed Js 

 if (($('#instafeed').length) !== 0) {
    var userId = $('#instafeed').attr('data-userId');
    var accessToken = $('#instafeed').attr('data-accessToken');
      var userFeed =  new Instafeed({
      get: 'user',
      userId: '8987997106',
      resolution: 'standard_resolution',
      accessToken: '8987997106.924f677.8555ecbd52584f41b9b22ec1a16dafb9',
      limit: 4,
      template: '<div class="instagram-post col-lg-3 col-md-3 col-sm-6 col-6" id="{{id}}" ><a href="{{link}}" target="_blank" ><img src="{{image}}" class="img-fluid w-100"/><div class="intsa-meta"><span>{{likes}}</span><span>{{comments}}</span></div></a></div>'

      });
    userFeed.run();
  }


/* ----------------------------------------------------------- */
  /*  Slick Carousel
  /* ----------------------------------------------------------- */

  $('.slider-wrap').slick({
    slidesToShow: 3,
    slidesToScroll: 2,
    autoplaySpeed: 4000,
    items:3,
    loop:true,
    autoplay:true,
    dots:true,
    responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow:3,
            slidesToScroll: 3,
            infinite: true,
            dots: true
          }
        },
        {
          breakpoint: 900,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2
          }
        },{
          breakpoint: 600,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
      
      ]
  });
 
 // post gallery

        $('.post_gallery').owlCarousel({
            loop:true,
            margin:1,
            nav:true,
            dots: false,
            responsive:{
                0:{
                    items:1
                },
                600:{
                    items:1
                },
                1000:{
                    items:1
                }
            }
        });
	
       

	$('.post-slide').slick({
		fade: true,
    autplay:true
	});		

	// magnific Popup iframe

      $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
          disableOn: 300,
          type: 'iframe',
          mainClass: 'mfp-fade',
          removalDelay: 160,
          preloader: false,
          fixedContentPos: false
      });

	// -----------------------------


	/* ----------------------------------------------------------- */
	/*  Scroll To Top
	/* ----------------------------------------------------------- */
	$(window).scroll(function () {
		if ($(this).scrollTop() > 500) {
			$('.scroll-to-top').fadeIn();
		} else {
			$('.scroll-to-top').fadeOut();
		}
	});
	
	
	
	
	
	
	
	
	/*--------------------- 1123-1045 추가 -------------------------------*/
    $(".pageInfo a").on("click", function(e){

        e.preventDefault();
        moveForm.find("input[name='pageNum']").val($(this).attr("href"));
        moveForm.attr("action", "/house/searchApt");
        moveForm.submit();
        

        let moveForm = $("#moveForm");
    	$(".move").on("click", function(e){
    		e.preventDefault();
    		
    		moveForm.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
    		moveForm.attr("action", "/board/get");
    		moveForm.submit();
    	});
    	$(".pageInfo a").on("click", function(e){
    		e.preventDefault();
    		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
    		moveForm.attr("action", "/board/list");
    		moveForm.submit();
    		
    	});	
    	
    	
    	$(".search_area button").on("click", function(e){
    		e.preventDefault();
    		
    		let type = $(".search_area select").val();
    		let keyword = $(".search_area input[name='keyword']").val();
    		
    		if(!type){
    			alert("검색 종류를 선택하세요.");
    			return false;
    		}
    		
    		if(!keyword){
    			alert("키워드를 입력하세요.");
    			return false;
    		}		
    		
    		moveForm.find("input[name='type']").val(type);
    		moveForm.find("input[name='keyword']").val(keyword);
    		moveForm.find("input[name='pageNum']").val(1);
    		moveForm.submit();
    	});

})(jQuery);