<?php
//Create variables
$sortBy = $_REQUEST["sort"];
$working = $_REQUEST["working"];
$descAsc = $_REQUEST["asc"];
$category = $_REQUEST["category"];

//Create SQL connection
$login = include("../config/config.php");
$conn = new mysqli($login["host"], $login["username"], $login["password"], $login["database"]);

if($conn->connect_errno) {
	die();
}

//Prepare WHERE statement
if($category != "" && working != 2) {
	$sql = "WHERE";
	if($category != "") {
		$sql = $sql." category=?";
	}f
}
//Prepare statement
if($category != "") {
	$stmt = $conn->prepare("SELECT * FROM inventory WHERE category=? AND ");
}
?>