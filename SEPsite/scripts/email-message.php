<?php
//Include Chrome logger
include "../config/chromephp.php";
//Create variables
$name = $_REQUEST["name"];
$email = $_REQUEST["email"];
$message = $_REQUEST["message"];
$config = include("../config/config.php");
$mailSent = mail($config["email"], "Contact from: ".$name, "Email: ".$email."\n\n".$message);
if($mailSent) {
	echo "OK";
} else {
	echo "ERROR";
}
?>