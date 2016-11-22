<?php
//Include Chrome logger
include "../config/chromephp.php";
//Create variables
$email = $_REQUEST["email"];
$config = include("../config/config.php");
$mailSent = mail($config["email"], "Appointment Request", "From: ".$email);
if($mailSent) {
	echo "OK";
} else {
	echo "ERROR";
}
?>