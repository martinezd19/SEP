<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html" xml:lang="en-gb" lang="en-gb">
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,400italic,500,700,900' rel='stylesheet'
          type='text/css'>
    <base/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="generator" content="Joomla! - Open Source Content Management"/>
    <title>Props</title>
    <link href="templates/theme3079/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon"/>
    <link rel="stylesheet" href="templates/theme3079/css/layout.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/css/jquery-fancybox.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/css/jquery-fancybox-buttons.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/css/jquery-fancybox-thumbs.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/css/template.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/color_schemes/css/color_scheme_1.min.css" type="text/css"
          id="color_scheme"/>
    <link rel="stylesheet" href="modules/mod_tm_ajax_contact_form/css/style.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/css/caroufredsel.min.css" type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/html/mod_icemegamenu/css/default_icemegamenu.min.css"
          type="text/css"/>
    <link rel="stylesheet" href="templates/theme3079/html/mod_icemegamenu/css/default_icemegamenu-reponsive.min.css"
          type="text/css"/>
    <script src="media/jui/js/jquery.min.js" type="text/javascript"></script>
    <script src="media/jui/js/jquery-noconflict.min.js" type="text/javascript"></script>
    <script src="media/jui/js/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="media/system/js/caption.min.js" type="text/javascript"></script>
    <script src="media/jui/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="media/system/js/mootools-core.min.js" type="text/javascript"></script>
    <script src="media/system/js/core.min.js" type="text/javascript"></script>
    <script src="modules/mod_caroufredsel/js/jquery.caroufredsel.js" type="text/javascript"></script>
    <script src="templates/theme3079/html/mod_icemegamenu/js/menu.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/prop-page.css" type="text/css"/>

    <script type="text/javascript" src="scripts/prop-page.js"></script>
    <script type="text/javascript">
        jQuery(window).on('load', function () {
            displayProps();
        });
    </script>

</head>
<body id="body">
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

        <div class="bg_top">
            <!-- Top -->
          <?php
          include "header.html";
          ?>
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
                                    <li><a href="home.php" class="pathway">Home</a><span
                                                class="divider">&nbsp;|&nbsp;</span>
                                    </li>
                                    <li class="active"><span>Props</span></li>
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

                                    <!-- Content-bottom -->
                                    <div id="content-bottom" class="row-fluid">
                                        <div class="moduletable accordion accordion_style1  span12">
                                            <div class="module_container" id="prop-module">
                                                <div class="prop-container" id="prop-container">
                                                    <div class="prop-select">
                                                        <select id="sortBySelect" onchange="displayProps()">
                                                            <option disabled selected value="title_first_letter">Sort
                                                                by
                                                            </option>
                                                            <option value="title_first_letter">Title</option>
                                                            <option value="time_period">Time period</option>
                                                            <option value="category">Category</option>
                                                        </select>
                                                    </div>
                                                    <div class="prop-select" onchange="displayProps()">
                                                        <select id="workingSelect">
                                                            <option disabled selected value="2">Select
                                                                working/non-working
                                                            </option>
                                                            <option value="2">Either</option>
                                                            <option value="1">Working</option>
                                                            <option value="0">Non-working</option>
                                                        </select>
                                                    </div>
                                                    <div class="prop-select" onchange="displayProps()">
                                                        <select id="descAscSelect">
                                                            <option disabled selected value="ASC">Select
                                                                descending/ascending
                                                            </option>
                                                            <option value="ASC">Ascending</option>
                                                            <option value="DESC">Descending</option>
                                                        </select>
                                                    </div>
                                                    <div class="prop-select" onchange="displayProps()">
                                                        <select id="categorySelect">
                                                            <option disabled selected value="">Select category</option>
                                                            <option value="">Any category</option>
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
                                                <div class="mod-bootstrap-collapse mod-bootstrap-collapse__accordion accordion_style1"
                                                     id="prop-accordion">
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
      <?php
      include "footer.html";
      ?>
    </div>
</div>
<div id="back-top">
    <a href="#"><span></span> </a>
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
