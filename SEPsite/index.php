<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb" lang="en-gb">

<!-- Mirrored from localhost:8012/joomla/ by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 16 Jun 2016 21:36:11 GMT -->
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=utf-8"/><!-- /Added by HTTrack -->
<head>
  <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,400italic,500,700,900' rel='stylesheet'
        type='text/css'>
  <base/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <title>Home</title>
  <link href="indexc0d0.php?format=feed&amp;type=rss" rel="alternate" type="application/rss+xml" title="RSS 2.0"/>
  <link href="index7b17.xml?format=feed&amp;type=atom" rel="alternate" type="application/atom+xml" title="Atom 1.0"/>
  <link href="templates/theme3079/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
  <link rel="stylesheet" href="templates/theme3079/css/layout.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox-buttons.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox-thumbs.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/css/template.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/color_schemes/css/color_scheme_1.css" type="text/css"
        id="color_scheme"/>
  <link rel="stylesheet" href="modules/mod_tm_ajax_contact_form/css/style.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/css/caroufredsel.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/html/mod_icemegamenu/css/default_icemegamenu.css" type="text/css"/>
  <link rel="stylesheet" href="templates/theme3079/html/mod_icemegamenu/css/default_icemegamenu-reponsive.css"
        type="text/css"/>
  <link rel="stylesheet" href="modules/mod_socialloginandsocialshare/lrstyle.css" type="text/css"/>
  <script src="media/jui/js/jquery.min.js" type="text/javascript"></script>
  <script src="media/jui/js/jquery-noconflict.js" type="text/javascript"></script>
  <script src="media/jui/js/jquery-migrate.min.js" type="text/javascript"></script>
  <script src="media/system/js/caption.js" type="text/javascript"></script>
  <script src="media/jui/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="modules/mod_tm_ajax_contact_form/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="modules/mod_tm_ajax_contact_form/js/additional-methods.min.js" type="text/javascript"></script>
  <script src="modules/mod_tm_ajax_contact_form/js/autosize.min.js" type="text/javascript"></script>
  <script src="modules/mod_tm_ajax_contact_form/js/ajaxsendmail.js" type="text/javascript"></script>
  <script src="media/com_acymailing/js/acymailing_module02de.js?v=550" type="text/javascript" async="async"></script>
  <script src="media/system/js/mootools-core.js" type="text/javascript"></script>
  <script src="media/system/js/core.js" type="text/javascript"></script>
  <script src="modules/mod_caroufredsel/js/jquery.caroufredsel.js" type="text/javascript"></script>
  <script src="templates/theme3079/html/mod_icemegamenu/js/menu.js" type="text/javascript"></script>


</head>
<body class=" com_content view-category task- itemid-101 body__home">

<div id="wrapper">
  <div class="wrapper-inner">

    <div class="bg_top">
      <!-- Top -->
      <?php
      include "header.html";
      ?>
      <!-- Header -->
      <!-- Showcase -->
      <!-- <div id="showcase">
        <div class="row-container">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="moduletable top-img  span12">
                <div class="module_container">
                  <div class="mod-article-single mod-article-single__top-img" id="module_184">
                    <div class="item__module" id="item_122">

                      <!-- Intro Text -->
                      <div class="module_container testimonials">
                        <div class="mod_caroufredsel mod_caroufredsel__testimonials" id="module_100">
                          <div id="list_carousel_100" class="list_carousel">

                            <ul id="caroufredsel_100">
                              <li class="item firstItem visible visible-first" id="item_47" style="width: 100%; text-align: center;">
                                <img src="images/carousel1.png" alt="">
                              </li>
                              <li class="item firstItem visible visible-first" id="item_46" style="width: 100%; text-align: center;">
                                <div>
                                  <img src="images/carousel2.png" alt="">
                                </div>
                              </li>
                              <li class="item firstItem visible visible-first" id="item_45" style="width: 100%; text-align: center;">
                                <div>
                                  <img src="images/carousel3.png" alt="">
                                </div>
                              </li>
                              <li class="item firstItem visible visible-first" id="item_44" style="width: 100%; text-align: center;">
                                <div>
                                  <img src="images/carousel4.png" alt="">
                                </div>
                              </li>
                              <li class="item firstItem visible visible-first" id="item_43" style="width: 100%; text-align: center;">
                                <div>
                                  <img src="images/carousel5.png" alt="">
                                </div>
                              </li>
                            </ul>


                            <div id="carousel_100_pag" class="caroufredsel_pagination"></div>
                            <div class="clearfix"></div>
                          </div>
                        </div>
                        <script>
                          jQuery(function ($) {
                            var carousel = $("#caroufredsel_100");
                            carousel.carouFredSel({
                              responsive: true,
                              width: '100%',
                              items: {
                                width: 570,
                                height: 'variable',
                                visible: {
                                  min: 1,
                                  max: 1
                                },
                                minimum: 1
                              },
                              scroll: {
                                items: 1,
                                fx: "scroll",
                                easing: "swing",
                                duration: 500,
                                queue: true
                              },
                              auto: false,
                              pagination: "#carousel_100_pag",
                              swipe: {
                                onTouch: true
                              }
                            });
                            $(window).load(function () {
                              setTimeout(function () {
                                carousel.trigger('configuration', {reInit: true})
                              }, 100);
                            });
                          });
                        </script>
                        </div>
                        <!-- <img src="images/logo.png" style="width: auto; max-height: 250px; margin: 0 auto; display: block;"> -->
                      <div class="item_introtext">
                        <!-- <h2>Be Creative</h2>
                        <h1>With Dr3am</h1>x`
                        <a href="#">
                          <div class="img">
                            <div class="layer-1"><img src="images/img-top/layer-2.png" alt=""/>
                            </div>
                            <div class="layer-2"><img src="images/img-top/layer-3.png" alt=""/>
                            </div>
                            <div class="layer-3"><img src="images/img-top/layer-1.png" alt=""/>
                            </div>
                          </div>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div> -->

    </div>
    <div class="bg_cont" style="background: #f7f7f7">

      <!-- breadcrumbs row -->
      <!-- Feature -->
      <div id="feature" data-stellar-background-ratio="0.5">
        <div class="row-container">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="moduletable way  span12">
                <div class="module_container">
                  <header><h3 class="moduleTitle "><span
                          class="item_title_part0 item_title_part_odd item_title_part_first_half item_title_part_first">Why should you choose Sunshine Electronics Props?</span>
                  </h3></header>
                  <div class="mod-newsflash-adv mod-newsflash-adv__way cols-3" id="module_185">
                    <div class="pretext">
                      At SEProps we are reusing older equipment that still has usable
                      productive life left in it. We found very little on electronic props
                      available in the South Florida area and decided that this would be an
                      excellent use of our equipment versus breaking products down for recycling.
                      We believe in Reuse/Reduce/Recycle!!!!
                    </div>
                    <div class="row-fluid">
                      <article class="span4 item item_num0 item__module  " id="item_123">

                        <div class="item_content">
                          <!-- Item title -->
                          <h5 class="item_title item_title__way">
                            <a href="pages/services/services.html">Services</a>
                          </h5>
                          <!-- Introtext -->
                          <div class="item_introtext">
                            <p><img src="images/icon-1.png" alt=""/></p>
                            <p>Shop online with delivery to your location, or pick up items at our warehouse in Miami.</p>
                          </div>


                          <!-- Read More link -->
                        </div>
                        <div class="clearfix"></div>
                      </article>
                      <article class="span4 item item_num1 item__module  " id="item_124">

                        <div class="item_content">
                          <!-- Item title -->
                          <h5 class="item_title item_title__way">
                            <a href="pages/pricing.html">Pricing</a>
                          </h5>
                          <!-- Introtext -->
                          <div class="item_introtext">
                            <p><img src="images/icon-2.png" alt=""/></p>
                            <p>Easily get high-quality items for $75-$100* a month.<br><span style="font-size: 13px">*Prices can vary with shipment load or special requirements.
                            All pricing is non-negotiable.</span></p>
                          </div>


                          <!-- Read More link -->
                        </div>
                        <div class="clearfix"></div>
                      </article>
                      <article class="span4 item item_num2 item__module  lastItem" id="item_125">

                        <div class="item_content">
                          <!-- Item title -->
                          <h5 class="item_title item_title__way">
                            <a href="media/sample-report.pdf">Ease of Use</a>
                          </h5>
                          <!-- Introtext -->
                          <div class="item_introtext">
                            <p><img src="images/icon-3.png" alt=""/></p>
                            <p>Send a message to us online, or request an appointment in just seconds, and let us send you any information you need.</p>
                          </div>


                          <!-- Read More link -->
                        </div>
                        <div class="clearfix"></div>
                      </article>
                    </div>
                    <div class="clearfix"></div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Maintop -->
      <div class="accenttop">
        <div class="row-container">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="moduletable present  span12">
                <div class="module_container">
                  <div class="mod-article-single mod-article-single__present" id="module_186">
                    <img src="images/bg_img1.png" class="span6" alt="computer chip"/>
                    <div class="span6" id="item_126">
                      <!-- Item Title -->
                      <h3 class="item-title">
                        Pick from a variety of props</h3>

                      <!-- Intro Text -->
                      <div class="item_introtext">

                        <p>We have collected a wide variety of electronic props
                          which we restock daily with anything from computers to
                          stereo systems. If you find that the item you need is not in
                          inventory, click our “contact us” (hyperlink) link. Here
                          you are able to inform us what items you need, so we are
                          able to add to our inventory for the near future. </p>
                      </div>
                      <a class="btn btn-info readmore"
                         href="props.php"><span>View props</span></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Bottom -->
	    <script type="text/javascript">
		    jQuery(function ($) {
			    $(document).ready(function () {
				    $("#appointment-button").mouseup(function () {
					    this.blur();
					    //Gather inputs from form
					    var email = document.getElementById("appointment-email").value;
					    email = email.trim();
					    //Check if inputs are valid
					    var valid = true;
					    var regex = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/i;
					    if(!regex.test(email) || email == "" || email == "Email:") {
						    document.getElementById("appointment-message").innerHTML = "Please enter a valid email";
						    valid = false;
					    } else {
						    document.getElementById("appointment-message").innerHTML = "";
					    }
					    if(!valid) {
						    return false;
					    }
					    //If valid, submit email
					    document.getElementById("appointment-message").innerHTML = "Creating appointment request...";
					    var xmlhttp = new XMLHttpRequest();
					    xmlhttp.onreadystatechange = function () {
						    if (this.readyState == 4 && this.status == 200) {
							    if(this.responseText == "OK") {
								    document.getElementById("appointment-message").innerHTML = "Appointment request created!";
							    } else {
								    document.getElementById("appointment-message").innerHTML = "Uh oh! Something went wrong. Please try again later";
							    }
						    }
					    };
					    var url = "scripts/email-appointment.php?&email="+email;
					    url = encodeURI(url);
					    xmlhttp.open("GET", url, true);
					    xmlhttp.send();
				    });
			    });
		    });
	    </script>
	    <style>
		    .message {
			    color: #ffffff;
			    font-weight: bold;
			    font-size: 22px;
			    padding-bottom: 10px !important;
		    }
	    </style>
      <div id="bottom" data-stellar-background-ratio="0.9">
        <div class="row-container">
          <div class="container-fluid">
            <div class="row-fluid">
              <div class="moduletable newsletter  span12">
                <div class="module_container">
                  <header><h3 class="moduleTitle ">
                    <span class="item_title_part1 item_title_part_even item_title_part_first_half">Create an appointment</span>
                  </h3></header>
                  <div class="acymailing_fulldiv">
                    <div class="acymailing_module_form">
                      <p>If you would like to create an appointment to check out our inventory,
                        pick up/drop off a rental, consult with a technician, or otherwise see us in person, please submit your email below.
                      </p>
                      <table class="acymailing_form">
	                      <tr>
		                      <p class="message" id="appointment-message"></p>
	                      </tr>
                        <tr>
                          <td>
                            <input id="appointment-email"
                                   onfocus="if(this.value == 'Enter your e-mail address') this.value = '';"
                                   onblur="if(this.value=='') this.value='Enter your e-mail address';"
                                   class="inputbox" type="text"
                                   style="width:80%"
                                   value="Enter your e-mail address"
                                   title="Enter your e-mail address"/>
                          </td>

                          <td>
                            <button class="button subbutton btn btn-primary"
                                   type="button"
                                   name="Submit" id="appointment-button"
                                   >Create now!</button>
                          </td>
                        </tr>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>
</div>
<div id="footer-wrapper">
  <div class="footer-wrapper-inner">
    <!-- Footer -->
	  <?php
	  include "footer.html";
	  ?>


<script src="templates/theme3079/js/jquery.modernizr.min.js"></script>
<script src="templates/theme3079/js/jquery.stellar.min.js"></script>
<script>
  jQuery(function ($) {
    if (!Modernizr.touch) {
      $(window).load(function () {
        $.stellar({responsive: true, horizontalScrolling: false});
      });
    }
  });
</script>
<script src="templates/theme3079/js/jquery.simplr.smoothscroll.min.js"></script>
<script>
  jQuery(function ($) {
    if (!Modernizr.touch) {
      var platform = navigator.platform.toLowerCase();
      if (platform.indexOf('win') == 0 || platform.indexOf('linux') == 0) {
        if ($.browser.webkit) {
          $.srSmoothscroll({ease: 'easeOutQuart'});
        }
      }
    }
  });
</script>
<script src="templates/theme3079/js/jquery.fancybox.pack.js"></script>
<script src="templates/theme3079/js/jquery.fancybox-buttons.js"></script>
<script src="templates/theme3079/js/jquery.fancybox-media.js"></script>
<script src="templates/theme3079/js/jquery.fancybox-thumbs.js"></script>
<script src="templates/theme3079/js/jquery.pep.min.js"></script>
<script src="templates/theme3079/js/jquery.vide.min.js"></script>
<script src="templates/theme3079/js/scripts.js"></script>

</body>

<!-- Mirrored from localhost:8012/joomla/ by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 16 Jun 2016 21:38:02 GMT -->
</html>