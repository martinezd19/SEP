<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb" lang="en-gb">

<!-- Mirrored from localhost:8012/joomla/pages/pages/faqs by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 16 Jun 2016 21:41:15 GMT -->
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=utf-8"/><!-- /Added by HTTrack -->
<head>
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,400italic,500,700,900' rel='stylesheet'
	      type='text/css'>
	<base/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<meta name="generator" content="Joomla! - Open Source Content Management"/>
	<title>AIO Inspections - FAQs</title>
	<link href="pages/pages/faqsc0d0?format=feed&amp;type=rss" rel="alternate" type="application/rss+xml"
	      title="RSS 2.0"/>
	<link href="pages/pages/faqs7b17?format=feed&amp;type=atom" rel="alternate" type="application/atom+xml"
	      title="Atom 1.0"/>
	<link href="templates/theme3079/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
	<link rel="stylesheet" href="templates/theme3079/css/layout.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox-buttons.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/css/jquery.fancybox-thumbs.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/css/template.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/color_schemes/css/color_scheme_1.css" type="text/css"
	      id="color_scheme"/>
	<link rel="stylesheet" href="modules/mod_tm_ajax_contact_form/css/style.css" type="text/css"/>
	<link rel="stylesheet" href="modules/mod_bootstrap_collapse/css/style.css" type="text/css"/>
	<link rel="stylesheet" href="templates/theme3079/html/mod_icemegamenu/css/default_icemegamenu.css"
	      type="text/css"/>
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
	<script src="templates/theme3079/html/mod_icemegamenu/js/menu.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/prop-page.css" type="text/css"/>
	<script type="text/javascript" src="scripts/prop-page.js"/>
	<script type="text/javascript">
		jQuery(window).on('load', function () {
			new JCaption('img.caption');
		});
		jQuery(document).ready(function () {
			jQuery('.hasTooltip').tooltip({"html": true, "container": "body"});
		});
		(function ($) {
			$(document).ready(function () {
				autosize($("textarea"))
			})
		})(jQuery);
		(function ($) {
			$(document).ready(function () {
				var v = $("#contact-form_189").validate({
					wrapper: "mark", submitHandler: function (f) {
						$(f).ajaxsendmail();
						return false
					}
				});
				$("#message_2").rules("add", {minlength: 50});
			})
		})(jQuery);
		window.setInterval(function () {
			var r;
			try {
				r = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
			} catch (e) {
			}
			if (r) {
				r.open("../404.html", "../../index86dd.php?option=com_ajax&amp;format=json", true);
				r.send(null)
			}
		}, 840000);
	</script>

</head>
<body class=" com_content view-category task- itemid-138 body__faqs">
<!--[if lt IE 9]
<div style=' clear: both; text-align:center; position: relative;'>
  <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
    <img src="/joomla/templates/theme3079/images/warning_bar_0000_us.jpg" border="0" height="42" width="820"
         alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."/>
  </a>
</div>
<![endif]-->
<!-- Body -->
<?php
//Import login information from config file
$login = include('config/config.php');

//Create SQL connection
$conn = new mysqli($login['host'], $login['username'], $login['password'], $login['database']);

//Check if connection is valid
if ($conn->connect_errno) {
	echo "dead";
}
?>
<div id="wrapper">
	<div class="wrapper-inner">


		<div id="fixed-sidebar-right">
			<div class="moduletable login">
				<!--
				<i class="fa fa-user"></i>

				<div class="login_box">


					<h5 class="">Login or register</h5>
					<form action="http://localhost:8012/joomla/index.php/pages/faqs" method="post" id="login-form"
								class="form-inline">
						<div class="mod-login_userdata">
							<div id="form-login-username" class="control-group">
								<div class="controls">
									<div class="input-prepend">
						<span class="add-on">
							<span class="fa fa-user hasTooltip" title="Username"></span>
							<label for="modlgn-username" class="element-invisible">Username</label>
						</span>
										<input id="modlgn-username" type="text" name="username"
													 class="input-small placeholder" tabindex="0" size="18"
													 placeholder="Username" required/>
									</div>
								</div>
							</div>
							<div id="form-login-password" class="control-group">
								<div class="controls">
									<div class="input-prepend">
						<span class="add-on">
							<span class="fa fa-lock hasTooltip" title="Password">
							</span>
								<label for="modlgn-passwd" class="element-invisible">Password              </label>
						</span>
										<input id="modlgn-passwd" type="password" name="password"
													 class="input-small placeholder" tabindex="0" size="18"
													 placeholder="Password" required/>
									</div>
								</div>
							</div>
							<div class="mod-login_submit">
								<button type="submit" tabindex="3" name="Submit" class="btn btn-primary">Login</button>
								<a class="btn btn-primary register" href="../user-registration.html">Register</a>
							</div>
							<input type="hidden" name="option" value="com_users">
							<input type="hidden" name="task" value="user.login">
							<input type="hidden" name="return" value="aW5kZXgucGhwP0l0ZW1pZD0xMzg=">
							<input type="hidden" name="547566cb2c9b761e03de1837002c2431" value="1"/> <label
										for="mod-login_remember107" class="checkbox">
							<input id="mod-login_remember107" class="mod-login_remember" type="checkbox" name="remember"
										 value="yes">
							Remember my password </label>
							<div class="reset_remind">
								Forgot <a href="../username-reminder-request.html" class="hasTooltip">username</a>/
								<a href="../password-reset.html" class="hasTooltip">password</a>?
							</div>
						</div>
					</form>
					<div class="lr_social_login_basic_150">
						<div class="lr_providers">
							<div class="lr_icons_box">
								<div>
									<a class="lr_providericons lr_facebook" href="javascript:void(0);"
										 onclick="javascript:window.open('http://www.facebook.com/dialog/oauth?client_id=930899133604868&amp;redirect_uri=http://localhost:8012/joomla/?provider=facebook&amp;display=popup&amp;scope=email,user_photos,user_about_me,user_hometown,user_photos','Facebook','toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400px,height=400px');"
										 rel="nofollow" title="Login with Facebook">Login with Facebook</a>
								</div>
								<div>
									<a class="lr_providericons lr_google" href="javascript:void(0);"
										 onclick="javascript:window.open('https://accounts.google.com/o/oauth2/auth?response_type=code&amp;redirect_uri=http://localhost:8012/joomla/?provider=google&amp;client_id=4ea43331a8b16c6ddb33685fc03635a8&amp;scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email','Google','toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400px,height=400px');"
										 rel="nofollow" title="Login with Google +">Login with Google +</a>
								</div>
							</div>
						</div>
					</div>
				</div> -->

				<script>
					jQuery(function ($) {
						$('.moduletable.login>i.fa-user').click(function () {
							$('.moduletable.login').toggleClass('shown')
						});
					})
				</script>
			</div>
		</div>

		<div class="bg_top">
			<!-- Top -->
			<div id="top">
				<div class="row-container">
					<div class="container-fluid">
						<div class="row-fluid">
							<!-- Logo -->
							<div id="logo" class="span3">
								<a href="index.html">
									<img src="images/logo.png" alt="AIO Inspections">
									<h1>AIO Inspections</h1>
								</a>
							</div>
							<nav class="moduletable home_menu  span9">
								<div class="module_container">
									<div class="icemegamenu">
										<div class="ice-megamenu-toggle"><a data-toggle="collapse"
										                                    data-target=".nav-collapse">Menu</a></div>
										<div class="nav-collapse icemegamenu collapse left ">
											<ul id="icemegamenu" class="meganizr mzr-slide mzr-responsive">
												<li id="iceMenu_101" class="iceMenuLiLevel_1 mzr-drop parent fullwidth">
													<a href="index.html" class=" iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">Home</span></a>
													<ul class="icesubMenu icemodules sub_level_1" style="width:100%">
														<li>
															<div style="float:left;width:33%" class="iceCols">
																<ul>
																	<li id="iceMenu_347" class="iceMenuLiLevel_2 ">
																		<div class="icemega_cover_module"
																		     style="width:220px">
																			<div class="icemega_modulewrap  no_padding"
																			     style="width:auto; "><span
																						class="iceModuleTile">Categories</span>
																				<ul class="categories-module no_padding">
																					<li>
																						<a href="pages/blog/12-lorem-ipsum-dolor-sit-amet.html">Lorem
																						                                                        ipsum
																						                                                        dolor
																						                                                        sit
																						                                                        amet </a>
																					</li>
																					<li>
																						<a href="pages/blog/13-conse-ctetur-adipisicing.html">Conse
																						                                                      ctetur
																						                                                      adipisicing</a>
																					</li>
																					<li>
																						<a href="pages/blog/14-elit-sed-do-eiusmod-tempor.html">Elit
																						                                                        sed
																						                                                        do
																						                                                        eiusmod
																						                                                        tempor</a>
																					</li>
																					<li>
																						<a href="pages/blog/15-incididunt-ut-labore.html">Incididunt
																						                                                  ut
																						                                                  labore</a>
																					</li>
																					<li>
																						<a href="pages/blog/48-et-dolore-magna-aliqua.html">Et
																						                                                    dolore
																						                                                    magna
																						                                                    aliqua</a>
																					</li>
																					<li>
																						<a href="pages/blog/49-lorem-ipsum-dolor-sit-amet-2.html">Lorem
																						                                                          ipsum
																						                                                          dolor
																						                                                          sit
																						                                                          amet</a>
																					</li>
																					<li>
																						<a href="pages/blog/50-conse-ctetur-adipisicing-2.html">Conse
																						                                                        ctetur
																						                                                        adipisicing</a>
																					</li>
																					<li>
																						<a href="pages/blog/51-elit-sed-do-eiusmod-tempor-2.html">Elit
																						                                                          sed
																						                                                          do
																						                                                          eiusmod
																						                                                          tempor</a>
																					</li>
																					<li>
																						<a href="pages/blog/52-incididunt-ut-labore-2.html">Incididunt
																						                                                    ut
																						                                                    labore</a>
																					</li>
																					<li>
																						<a href="pages/blog/53-et-dolore-magna-aliqua-2.html">Et
																						                                                      dolore
																						                                                      magna
																						                                                      aliqua</a>
																					</li>
																					<li>
																						<a href="pages/blog/54-conse-ctetur-adipisicing-3.html">Conse
																						                                                        ctetur
																						                                                        adipisicing</a>
																					</li>
																				</ul>
																			</div>
																		</div>
																	</li>
																</ul>
															</div>
															<div style="float:left;width: 33%" class="iceCols">
																<ul>
																	<li id="iceMenu_349" class="iceMenuLiLevel_2 ">
																		<div class="icemega_cover_module"
																		     style="width:220px">
																			<div class="icemega_modulewrap list1"
																			     style="width:auto; "><span
																						class="iceModuleTile">What we offer</span>
																				<div class="mod-menu">
																					<ul class="nav menu ">
																						<li class="item-309"><a
																									href="#">Lorem ipsum
																						               dolor sit
																						               amet</a>
																						</li>
																						<li class="item-310"><a
																									href="#">Conse ctetur
																						               adipisicing</a>
																						</li>
																						<li class="item-311"><a
																									href="#">Elit sed do
																						               eiusmod
																						               tempor</a>
																						</li>
																						<li class="item-312"><a
																									href="#">Incididunt ut
																						               labore</a>
																						</li>
																					</ul>
																				</div>
																			</div>
																		</div>
																	</li>
																	<li id="iceMenu_423" class="iceMenuLiLevel_2 ">
																		<div class="icemega_cover_module"
																		     style="width:100%">
																			<div class="icemega_modulewrap list1"
																			     style="width:auto; "><span
																						class="iceModuleTile">What we do</span>
																				<div class="mod-menu">
																					<ul class="nav menu ">
																						<li class="item-416"><a
																									href="#">Conse ctetur
																						               adipisicing</a>
																						</li>
																						<li class="item-417"><a
																									href="#">Elit sed do
																						               eiusmod
																						               tempor</a>
																						</li>
																						<li class="item-418"><a
																									href="#">Incididunt ut
																						               labore</a>
																						</li>
																						<li class="item-419"><a
																									href="#">Lorem ipsum
																						               dolor sit
																						               amet</a>
																						</li>
																					</ul>
																				</div>
																			</div>
																		</div>
																	</li>
																</ul>
															</div>
															<div style="float:left;width: 33%" class="iceCols">
																<ul>
																	<li id="iceMenu_424" class="iceMenuLiLevel_2 ">
																		<div class="icemega_cover_module"
																		     style="width:100%">
																			<div class="icemega_modulewrap menu_about"
																			     style="width:auto; "><span
																						class="iceModuleTile">About</span>
																				<div
																						class="mod-article-single mod-article-single__menu_about"
																						id="module_177">
																					<div class="item__module"
																					     id="item_117">

																						<!-- Intro Text -->
																						<div class="item_introtext">
																							<p>Lorem ipsum dolor sit
																							   amet conse ctetur
																							   adipisicing elit, sed do
																							   eiusmod tempor
																							   incididunt ut labore et
																							   dolore magna aliqua. Ut
																							   enim ad minim. ex ea
																							   commodo consequat. Duis
																							   aute irure dolor in
																							   reprehenderit in
																							   voluptate velit esse
																							   cillum dolore eu fugiat
																							   nulla pariatur.
																							   Excepteur sint occaecat
																							   cupidatat non proident,
																							   sunt in culpa qui
																							   offici. Lorem ipsum
																							   dolor sit amet conse
																							   ctetur adipisicing
																							   elit,</p>
																						</div>
																						<a class="btn btn-info readmore"
																						   href="pages/about-3.html"><span>Read more</span></a>
																					</div>
																				</div>
																			</div>
																		</div>
																	</li>
																</ul>
															</div>
														</li>
													</ul>
												</li>
												<li id="iceMenu_134" class="iceMenuLiLevel_1 "><a href="about.html"
												                                                  class=" iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">About</span></a>
												</li>
												<li id="iceMenu_335" class="iceMenuLiLevel_1 mzr-drop parent active "><a
															class="icemega_active iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">Pages</span></a>
													<ul class="icesubMenu icemodules sub_level_1" style="width:200px">
														<li>
															<div style="float:left;width:200px" class="iceCols">
																<ul>
																	<li id="iceMenu_336" class="iceMenuLiLevel_2 "><a
																				href="pricing.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Pricing</span></a>
																	</li>
																	<li id="iceMenu_138"
																	    class="iceMenuLiLevel_2 active "><a
																				href="faqs.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">FAQs</span></a>
																	</li>
																	<li id="iceMenu_136" class="iceMenuLiLevel_2 "><a
																				href="our-team.html" class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Our team</span></a>
																	</li>
																	<li id="iceMenu_135" class="iceMenuLiLevel_2 "><a
																				href="history.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">History</span></a>
																	</li>
																	<li id="iceMenu_137" class="iceMenuLiLevel_2 "><a
																				href="testimonials.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Testimonials</span></a>
																	</li>
																	<li id="iceMenu_305" class="iceMenuLiLevel_2 "><a
																				href="site-map.html" class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Site map</span></a>
																	</li>
																	<li id="iceMenu_342" class="iceMenuLiLevel_2 "><a
																				href="../forum.html" class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Forum</span></a>
																	</li>
																	<li id="iceMenu_343" class="iceMenuLiLevel_2 "><a
																				href="careers.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Careers</span></a>
																	</li>
																	<li id="iceMenu_283" class="iceMenuLiLevel_2 "><a
																				href="portfolio.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Portfolio</span></a>
																	</li>
																	<li id="iceMenu_426" class="iceMenuLiLevel_2 "><a
																				href="elements.html" class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Elements</span></a>
																	</li>
																	<li id="iceMenu_171" class="iceMenuLiLevel_2 "><a
																				href="../template-settings.html"
																				class=" iceMenuTitle "><span
																					class="icemega_title icemega_nosubtitle">Template settings</span></a>
																	</li>
																</ul>
															</div>
														</li>
													</ul>
												</li>
												<li id="iceMenu_285" class="iceMenuLiLevel_1 "><a href="../blog.html"
												                                                  class=" iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">Blog</span></a>
												</li>
												<li id="iceMenu_203" class="iceMenuLiLevel_1 gallery"><a
															href="../gallery.html" class=" iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">Gallery</span></a>
												</li>
												<li id="iceMenu_142" class="iceMenuLiLevel_1 "><a
															href="../faq.html" class=" iceMenuTitle "><span
																class="icemega_title icemega_nosubtitle">FAQ</span></a>
												</li>
											</ul>
										</div>
									</div>


									<script type="text/javascript">
										jQuery(document).ready(function () {
											var browser_width1 = jQuery(window).width();
											jQuery("#icemegamenu").find(".icesubMenu").each(function (index) {
												var offset1 = jQuery(this).offset();
												var xwidth1 = offset1.left + jQuery(this).width();
												if (xwidth1 >= browser_width1) {
													jQuery(this).addClass("ice_righttoleft");
												}
											});

										});
										jQuery(window).resize(function () {
											var browser_width = jQuery(window).width();
											jQuery("#icemegamenu").find(".icesubMenu").removeClass("ice_righttoleft");
											jQuery("#icemegamenu").find(".icesubMenu").each(function (index) {
												var offset = jQuery(this).offset();
												var xwidth = offset.left + jQuery(this).width();

												if (xwidth >= browser_width) {
													jQuery(this).addClass("ice_righttoleft");
												}
											});
										});
									</script>
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- Header -->

		</div>
		<div class="bg_cont">

			<!-- breadcrumbs row -->
			<div class="row-container">
				<div class="container-fluid">
					<!-- Breadcrumbs -->
					<div id="breadcrumbs" class="row-fluid">
						<div class="moduletable   span12">
							<div class="module_container">
								<ul class="breadcrumb">
									<li><a href="index.html" class="pathway">Home</a><span
												class="divider">&nbsp;|&nbsp;</span>
									</li>
									<li class="active"><span>FAQs</span></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Main Content row -->
			<div id="content">
				<div class="row-container">
					<div class="container-fluid">
						<div class="content-inner row-fluid">

							<div id="component" class="span12">
								<main role="main">

									<div id="system-message-container">
									</div>

									<section class="page-category page-category__faqs">
									</section>

									<!-- Content-bottom -->
									<div id="content-bottom" class="row-fluid">
										<div class="moduletable accordion accordion_style1  span12">
											<div class="module_container" id="prop-module">
												<div class="prop-container" id="prop-container">
													<div class="prop-select">
														<select id="sortBySelect" onchange="displayProps()">
															<option disabled selected value="title">Sort by</option>
															<option value="title">Title</option>
															<option value="time_period">Time period</option>
															<option value="category">Category</option>
														</select>
													</div>
													<div class="prop-select" onchange="displayProps()">
														<select id="workingSelect">
															<option disabled selected value="2">Select working/non-working
															</option>
															<option value="2">Either</option>
															<option value="1">Working</option>
															<option value="0">Non-working</option>
														</select>
													</div>
													<div class="prop-select" onchange="displayProps()">
														<select id="descAscSelect">
															<option disabled selected value="1">Select descending/ascending
															</option>
															<option value="1">Ascending</option>
															<option value="0">Descending</option>
														</select>
													</div>
													<div class="prop-select" onchange="displayProps()">
														<select id="categorySelect">
															<option disabled selected value="">Select category</option>
															<?php
															$sql = "SELECT DISTINCT category FROM inventory ORDER BY category ASC";
															$result = $conn->query($sql);
															while ($row = $result->fetch_assoc()) {
																$category = $row["category"];
																echo "<option value=\"$category\">$category</option>";
															}
															?>
														</select>
													</div>
												</div>
												<div
														class="mod-bootstrap-collapse mod-bootstrap-collapse__accordion accordion_style1">


													<div class="accordion" id="accordion173">

														<div class="accordion-group">
															<!-- Item title -->
															<div class="accordion-heading">
																<a href="#collapse_173_44" class="accordion-toggle "
																   data-toggle="collapse" data-parent="#accordion173">
																	1980 </a>
															</div>

															<div id="collapse_173_44" class="accordion-body collapse ">
																<div class="accordion-inner">
																	<div class="inventory">
																		<img class="prop-img" src="images/prop-img.jpg"
																		     alt="title"/>
																		<p class="prop-text" id="clamp"><b>WWWWWWWWWWWWWWWWWWWW</b><br>This
																		                                                               is
																		                                                               a
																		                                                               description
																		</p>
																		<p class="prop-id"><b>Category:</b> Computer,
																			<b>Working:</b> Yes, <b>ID:</b> 38749</p>
																		<p class="prop-cat"><b>Time Period:</b> 1970,
																			<b>Available: </b>3, <b
																					class="red">Rented: </b>2</p>
																	</div>
																	<div class="inventory">
																		<img class="prop-img" src="images/img_404.png"
																		     alt="title"/>
																		<p class="prop-text"><b>This is a title</b><br>This
																		                                               is
																		                                               a
																		                                               description
																		</p>
																		<p class="prop-id"><b>Category:</b> Computer, <b
																					class="red">Working:</b> No, <b>ID:</b>
																		                                    38749</p>
																		<p class="prop-cat"><b>Time Period:</b> 1970,
																			<b>Available: </b>3, <b
																					class="red">Rented: </b>2, </p>
																	</div>
																</div>
															</div>
														</div>
														<div class="accordion-group">
															<!-- Item title -->


														</div>

													</div>
												</div>
											</div>
										</div>
									</div>
								</main>
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
		<div id="footer">
			<div class="row-container">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="moduletable FAQ  span6">
							<div class="module_container">
								<header><h3 class="moduleTitle "><span
												class="item_title_part0 item_title_part_odd item_title_part_first_half item_title_part_first">Contact</span>
										<span class="item_title_part1 item_title_part_even item_title_part_second_half">info</span>
									</h3></header>
								<div class="mod-article-single mod-article-single__FAQ" id="module_191">
									<div class="item__module" id="item_130">

										<!-- Intro Text -->
										<div class="item_introtext">
											<p>Lorem ipsum dolor sit amet conse ctetur adipisicing elit, sed do eiusmod
											   tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
											   veniam. Dolor sit amet conse ctetur adipisicing elit, sed do eiusmod
											   tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
											   veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.</p>
											<ul class="FAQ">
												<li class="address">1234 Johnson Road, City, State</li>
												<li class="phone">000-000-0000</li>
												<li class="mail"><a href="http://www.dr3am.org/" target="_blank">dr3am.org</a>
												</li>
											</ul>
											<p>
											<div class="moduletablesocial span12">

												<div class="mod-menu mod-menu__social">
													<ul class="nav menu ">
														<li class="item-148"><a class="fa fa-facebook hasTooltip"
														                        href="#" title="Facebook"></a>
														</li>
														<li class="item-150"><a class="fa fa-twitter hasTooltip"
														                        href="#" title="Twitter"></a>
														</li>
														<li class="item-151"><a class="fa fa-skype hasTooltip" href="#"
														                        title="Skype"></a>
														</li>
														<li class="item-149"><a class="fa fa-google-plus hasTooltip"
														                        href="#" title="Google+"></a>
														</li>
														<li class="item-152"><a class="fa fa-instagram hasTooltip"
														                        href="#" title="Instagram"></a>
														</li>
														<li class="item-280"><a class="fa fa-vimeo-square hasTooltip"
														                        href="#" title="Vimeo"></a>
														</li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="moduletable contact-form  span6">
							<div class="module_container">
								<header><h3 class="moduleTitle "><span
												class="item_title_part0 item_title_part_odd item_title_part_first_half item_title_part_first">Get</span>
										<span class="item_title_part1 item_title_part_even item_title_part_first_half">in</span>
										<span class="item_title_part2 item_title_part_odd item_title_part_second_half">touch</span>
									</h3></header>
								<div id="contact_189">
									<form class="mod_tm_ajax_contact_form" id="contact-form_189" novalidate>
										<input type="hidden" id="module_id" name="module_id" value="189">
										<div class="mod_tm_ajax_contact_form_message" id="message_189">
											<span class="s">Thank You! Your message has been sent.</span>
											<span class="e">Something went wrong, please try again later.</span>
											<span class="c">Please enter a correct Captcha answer.</span>
										</div>
										<fieldset>
											<div class="row-fluid">
												<div class="control-group control-group-input span6">
													<div class="controls"><input type="text" placeholder="Name:"
													                             name="name" id="name_0"
													                             class="mod_tm_ajax_contact_form_text hasTooltip"
													                             required title="Name:"></div>
												</div>
												<div class="control-group control-group-input span6">
													<div class="controls"><input type="email" placeholder="Email:"
													                             name="email" id="email_1"
													                             class="mod_tm_ajax_contact_form_email hasTooltip"
													                             required title="Email:"></div>
												</div>
												<div class="control-group control-group-input span12">
													<div class="controls"><textarea name="message"
													                                placeholder="Message:"
													                                id="message_2"
													                                class="mod_tm_ajax_contact_form_textarea hasTooltip"
													                                required
													                                title="Message:"></textarea></div>
												</div>                <!-- Submit Button -->
												<div class="control-group control-group-button span12">
													<div class="controls">
														<button type="submit"
														        class="btn btn-primary mod_tm_ajax_contact_form_btn">
															Send
														</button>
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Copyright -->
		<div id="copyright" role="contentinfo">
			<div class="row-container">
				<div class="container-fluid">
					<div class="row-fluid">

						<div class="copyright span12">
							<span class="siteName">AIO Inspections</span>
							<span class="copy">&copy;</span> <span class="year">2016</span> <a class="privacy_link"
							                                                                   rel="license"
							                                                                   href="pages/privacy-policy.html">Privacy
							                                                                                                    Policy</a>
						</div>
						More <a rel='nofollow'
						        href='http://www.templatemonster.com/category/web-design-joomla-templates/'
						        target='_blank'>Web Design Joomla Templates at TemplateMonster.com</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="back-top">
	<a href="#"><span></span> </a>
</div>
<div id="modal" class="modal hide fade loginPopup">
	<div class="modal-hide"></div>
	<div class="modal_wrapper">
		<button type="button" class="close modalClose">×</button>
		<div class="moduletable ">
			<div class="modal-body">

				<!-- begin olark code
				<script data-cfasync="false" type='text/javascript'>/*<![CDATA[*/
				window.olark || (function (c) {
					var f = window, d = document, l = f.location.protocol == "https:" ? "https:" : "http:", z = c.name, r = "load";
					var nt = function () {
						f[z] = function () {
							(a.s = a.s || []).push(arguments)
						};
						var a = f[z]._ = {}, q = c.methods.length;
						while (q--) {
							(function (n) {
								f[z][n] = function () {
									f[z]("call", n, arguments)
								}
							})(c.methods[q])
						}
						a.l = c.loader;
						a.i = nt;
						a.p = {
							0: +new Date
						};
						a.P = function (u) {
							a.p[u] = new Date - a.p[0]
						};
						function s() {
							a.P(r);
							f[z](r)
						}

						f.addEventListener ? f.addEventListener(r, s, false) : f.attachEvent("on" + r, s);
						var ld = function () {
							function p(hd) {
								hd = "head";
								return ["<", hd, "></", hd, "><", i, ' onl' + 'oad="var d=', g, ";d.getElementsByTagName('head')[0].", j, "(d.", h, "('script')).", k, "='", l, "//", a.l, "'", '"', "></", i, ">"].join("")
							}

							var i = "body", m = d[i];
							if (!m) {
								return setTimeout(ld, 100)
							}
							a.P(1);
							var j = "appendChild", h = "createElement", k = "src", n = d[h]("div"), v = n[j](d[h](z)), b = d[h]("iframe"), g = "document", e = "domain", o;
							n.style.display = "none";
							m.insertBefore(n, m.firstChild).id = z;
							b.frameBorder = "0";
							b.id = z + "-loader";
							if (/MSIE[ ]+6/.test(navigator.userAgent)) {
								b.src = "javascript:false"
							}
							b.allowTransparency = "true";
							v[j](b);
							try {
								b.contentWindow[g].open()
							} catch (w) {
								c[e] = d[e];
								o = "javascript:var d=" + g + ".open();d.domain='" + d.domain + "';";
								b[k] = o + "void(0);"
							}
							try {
								var t = b.contentWindow[g];
								t.write(p());
								t.close()
							} catch (x) {
								b[k] = o + 'd.write("' + p().replace(/"/g, String.fromCharCode(92) + '"') + '");d.close();'
							}
							a.P(2)
						};
						ld()
					};
					nt()
				})({
					loader: "static.olark.com/jsclient/loader0.js",
					name: "olark",
					methods: ["configure", "extend", "declare", "identify"]
				});
				/* custom configuration goes here (www.olark.com/documentation) */
				olark.identify('5615-604-10-1042');
				/*]]>*/</script>
				<!-- end olark code --></div>
		</div>
	</div>
</div>


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

<!-- Mirrored from localhost:8012/joomla/pages/pages/faqs by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 16 Jun 2016 21:41:19 GMT -->
</html>
