jQuery(function($) {
    'use strict';

    // Mean Menu
    $('.mean-menu').meanmenu({
        meanScreenWidth: '1199',
    });

    // Sticky Nav
    $(window).on('scroll', function() {
        $(window).scrollTop() >= 100 ?
        $('.main-navbar-area').addClass('stickyadd') :
        $('.main-navbar-area').removeClass('stickyadd');
    });

    // Side Option Responsive
    $('.side-option-button').on('click', function(e) {
        $('.side-option-responsive').toggleClass('active');
        e.preventDefault();
    })
    
    // Language Popup
    $('.language-button').on('click', function(e) {
        $('.language > .menu').toggle();
        e.preventDefault();
    });

    // Popup Video
    $('.youtube-popup').magnificPopup({
        disableOn: 320,
		type: 'iframe',
		mainClass: 'mfp-fade',
		removalDelay: 160,
		preloader: false,
		fixedContentPos: false
    });

    // Search Popup
    $('.btn-search').magnificPopup({
        removalDelay: 500,
        callbacks: {
            beforeOpen: function() {
            this.st.mainClass = this.st.el.attr('data-effect');
            }
        },
        midClick: true
    });

    // Product Single Popup Gallery
    $('.popup-gallery').magnificPopup({
		delegate: 'a',
		type: 'image',
		tLoading: 'Loading image #%curr%...',
		mainClass: 'mfp-img-mobile',
		gallery: {
			enabled: true,
			navigateByImgClick: true,
			preload: [0,1]
		},
		image: {
			tError: '<a href="%url%">The image #%curr%</a> could not be loaded.'
		}
	});

    // Home Banner Slider1
    $('.banner-slider').owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 500,
        transitionStyle : 'fade'
    });

    // Home Banner Slider2
    $('.banner-slider-two').owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: false,
        nav: true,
        navText: [
            "<i class='bx bxs-chevron-left'></i>",
            "<i class='bx bxs-chevron-right'></i>"
        ],
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 500,
        transitionStyle : 'fade'
    });

    // Blog Slider
    $('.blog-slider').owlCarousel({
        loop: true,
        margin: 20,
        dots: true,
        addClassActive: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 250,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },

            768: {
                items: 2,
            },

            991: {
                items: 2
            }
        }
    })

    // Blog Slider Two
    $('.blog-slider-two').owlCarousel({
        loop: true,
        margin: 15,
        dots: false,
        nav: true,
        navText: [
            "<i class='bx bxs-chevron-left'></i>",
            "<i class='bx bxs-chevron-right'></i>"
        ],
        addClassActive: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 250,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },

            768: {
                items: 2,
            },

            991: {
                items: 2
            }
        }
    })

    // Testimonial Slider
    $('.testimonial-slider').owlCarousel({
        loop: true,
        margin: 30,
        dots: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 250,
        items: 1
    })

    // Testimonial Slider Two
    $('.testimonial-slider-two').owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: true,
        dotsContainer: '#owl-custom-dots',
        nav: true,
        navText: [
            "<i class='bx bxs-chevron-left'></i>",
            "<i class='bx bxs-chevron-right'></i>"
        ],
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 450,
    });
    var testimonialSliderTwo = $('.testimonial-slider-two');
    $('.owl-dot').on('click', function() {
        testimonialSliderTwo.trigger('to.owl.carousel', [$(this).index(), 300]);
    });

    // Product Slider
    $('.product-slider').owlCarousel({
        loop: true,
        margin: 15,
        dots: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 450,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },

            768: {
                items: 2,
            },

            991: {
                items: 4,
            },
        }
    })

    // Product Slider Two
    $('.product-slider-two').owlCarousel({
        loop: true,
        margin: 15,
        dots: false,
        nav: true,
        navText: [
            "<i class='bx bxs-chevron-left'></i>",
            "<i class='bx bxs-chevron-right'></i>"
        ],
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 450,
        responsiveClass: true,
        responsive: {
            0: {
                items: 1,
            },

            768: {
                items: 2,
            },

            991: {
                items: 4,
            },
        }
    })

    // Product Single Slider
    $('.product-single-slider').owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: true,
        dotsContainer: '#owl-custom-dots',
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 450,
    });
    var productSingleSlider = $('.product-single-slider');
    $('.owl-dot').on('click', function() {
        productSingleSlider.trigger('to.owl.carousel', [$(this).index(), 300]);
    });

    // Service Slider
    $('.service-slider').owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: false,
        nav: true,
        navText: [
            "<i class='bx bxs-chevron-left'></i>",
            "<i class='bx bxs-chevron-right'></i>"
        ],
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 8500,
        smartSpeed: 500
    });

    // Product Plus Minus Input
    var $qtyValue = $('.qty-value').prop('disabled', true);
    $('.plus').on('click', function() {
        $qtyValue.val(parseInt($qtyValue.val()) + 1);
    });
    $('.minus').on('click', function() {
        $qtyValue.val(parseInt($qtyValue.val()) - 1);
        if ($qtyValue.val() == 0) {
            $qtyValue.val(1);
        }
    });

    // Back To Top
    $(window).on('scroll', function() {
        var scrolled = $(window).scrollTop();
        if (scrolled > 100) $('.go-top').addClass('active');
        if (scrolled < 100) $('.go-top').removeClass('active');
    });
    $('.go-top').on('click', function() {
        $('html, body').animate({
            scrollTop: '0',
        }, 0 );
    });

    // Count Time 
    function makeTimer() {
        var endTime = new Date('December 20, 2025 17:00:00 PDT');
        var endTime = (Date.parse(endTime)) / 1000;
        var now = new Date();
        var now = (Date.parse(now) / 1000);
        var timeLeft = endTime - now;
        var days = Math.floor(timeLeft / 86400);
        var hours = Math.floor((timeLeft - (days * 86400)) / 3600);
        var minutes = Math.floor((timeLeft - (days * 86400) - (hours * 3600)) / 60);
        var seconds = Math.floor((timeLeft - (days * 86400) - (hours * 3600) - (minutes * 60)));
        if (hours < '10') {
            hours = '0' + hours;
        }
        if (minutes < '10') {
            minutes = '0' + minutes;
        }
        if (seconds < '10') {
            seconds = '0' + seconds;
        }
        $('#days .h1').html(days);
        $('#hours .h1').html(hours);
        $('#minutes .h1').html(minutes);
        $('#seconds .h1').html(seconds);
    }
    setInterval(function() {
        makeTimer();
    }, 0);

    // Subscribe Form
    $('.newsletter-form').validator().on('submit', function(event) {
        if (event.isDefaultPrevented()) {
            // Handle The Invalid Form...
            formErrorSub();
            submitMSGSub(false, 'Invalid E-mail.');
        } else {
            // Everything Looks Good!
            event.preventDefault();
        }
    });

    function callbackFunction(resp) {
        if (resp.result === 'success') {
            formSuccessSub();
        } else {
            formErrorSub();
        }
    }

    function formSuccessSub() {
        $('.newsletter-form')[0].reset();
        submitMSGSub(true, 'Thank you for subscribing!');
        setTimeout(function() {
            $('#validator-newsletter').addClass('hide');
        }, 4000)
    }

    function formErrorSub() {
        $('.newsletter-form').addClass('animated shake');
        setTimeout(function() {
            $('.newsletter-form').removeClass('animated shake');
        }, 1000)
    }

    function submitMSGSub(valid, msg) {
        if (valid) {
            var msgClasses = 'validation-success';
        } else {
            var msgClasses = 'validation-danger';
        }
        $('#validator-newsletter').removeClass().addClass(msgClasses).text(msg);
    }

    // AJAX MailChimp
    $('.newsletter-form').ajaxChimp({
        url: 'https://hibootstrap.us20.list-manage.com/subscribe/post?u=60e1ffe2e8a68ce1204cd39a5&amp;id=42d6d188d9', // Your url MailChimp
        callback: callbackFunction
    });

    // Mixitup
    try {
        var mixer = mixitup('.shorting', {
            controls: {
                toggleDefault: 'none'
            }
        });
    } catch (err) {}

    // Range Slider JS
    $('#range-slider').slider({
        range: true,
        min: 500,
        max: 10000,
        values: [500, 10000],
        slide: function(event, ui) {
            $('#price-amount').val('$' + ui.values[0] + ' - $' + ui.values[1]);
        }
    });
    $('#price-amount').val('$' + $('#range-slider').slider('values', 0) + ' - $' + $('#range-slider').slider('values', 1));
    
    // Preloader
    $(window).on('load', function(e) {
        $('#preloader')
        .delay(1000)
        .fadeOut(500)
        .queue(function() {
            $(this).remove();
        });
    });

    // Init Nice Select JS
    $('select').niceSelect();

	// Switch Btn
	$('body').append("<div class='switch-box'><label id='switch' class='switch'><input type='checkbox' onchange='toggleTheme()' id='slider'><span class='slider round'></span></label></div>");

}(jQuery));


// function to set a given theme/color-scheme
function setTheme(themeName) {
    localStorage.setItem('wofy_theme', themeName);
    document.documentElement.className = themeName;
}

// function to toggle between light and dark theme
function toggleTheme() {
    if (localStorage.getItem('wofy_theme') === 'theme-dark') {
        setTheme('theme-light');
    } else {
        setTheme('theme-dark');
    }
}

// Immediately invoked function to set the theme on initial load
(function () {
    if (localStorage.getItem('wofy_theme') === 'theme-dark') {
        setTheme('theme-dark');
        document.getElementById('slider').checked = false;
    } else {
        setTheme('theme-light');
      document.getElementById('slider').checked = true;
    }
})();


