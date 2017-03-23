<?php
//Include Chrome logger
include "../config/chromephp.php";
//Create variables
$sortBy = $_REQUEST["sort"];
$working = $_REQUEST["working"];
$descAsc = $_REQUEST["asc"];
$category = $_REQUEST["category"];

//Create SQL connection
$login = include("../config/config.php");
$conn = new mysqli($login["host"], $login["username"], $login["password"], $login["database"]);

if ($conn->connect_errno) {
  die();
}

//Prepare WHERE statement
$categorySelect = false;
$workingSelect = false;
$sqlWhere = "";
if ($category != "" || $working != 2) {
  $sqlWhere = " WHERE";
  if ($category != "") {
    $sqlWhere = $sqlWhere . " category=\"" . $category . "\"";
    $categorySelect = true;
    if ($working != 2) {
      $sqlWhere = $sqlWhere . " AND";
    }
  }
  if ($working != 2) {
    $sqlWhere = $sqlWhere . " working=" . $working;
    $workingSelect = true;
  }
}

//Prepare statement
$sql = /* @lang MySQL */
  "SELECT * FROM inventory" . $sqlWhere . " ORDER BY " . $sortBy . " " . $descAsc;
$sqlNumRows = /* @lang MySQL */
  "SELECT DISTINCT " . $sortBy . " FROM inventory";
$result = $conn->query($sql);
$numRows = $conn->query($sqlNumRows)->num_rows;
if ($result->num_rows == 0) {
  echo "<b>No results!</b>";
  return;
}

$html = "";
$id = 0;
$numItem = 0;
$row = $result->fetch_assoc();
$last = $row[$sortBy];
$runLoop = true;
while ($runLoop) {
  $id++;
  $item = "";
  if ($id == 1) {
    $item = $item . " firstItem";
  }
  if ($id == $numRows) {
    $item = $item . " lastItem";
  }
  $header = $row[$sortBy];
  $html = /* @lang HTML */
    $html .
    "<div class=\"accordion" . $item . "\" id=\"accordion" . $id . "\">
				<div class=\"accordion-group\">
					<!-- Item title -->
					<div class=\"accordion-heading\">
						<a href=\"#collapse_" . $id . "\" class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion" . $id . "\">" . $header . "</a>
					</div>
					<!-- Items -->
					<div id=\"collapse_" . $id . "\" class=\"accordion-body collapse\">
						<div class=\"accordion-inner\">";
  while ($row[$sortBy] == $last) {
    $numItem++;
    $workBold = "";
    $workText = "";
    if ($row["working"] == 0) {
      $workBold = " class=\"red\"";
      $workText = "No";
    } else if ($row["working"] == 1) {
      $workText = "Yes";
    }
    $availBold = "";
    if ($row["num_available"] == 0) {
      $availBold = " class=\"red\"";
    }
    $rentedBold = "";
    if ($row["num_rented"] == 0) {
      $rentedBold = " class=\"red\"";
    }
    $origPath = "images/inventory/original/" . substr($row["picture_path"], strlen("images/inventory/"));
    $html = /* @lang HTML */
      $html .
      "<div class=\"inventory\">
								<a class='prop-img' id='img" . $numItem . "' href='" . $origPath . "' target='_blank'><img class=\"prop-img\" src=\"" . $row["picture_path"] . "\" alt=\"Item preview\"/></a>
								<p class=\"prop-text\" id=\"clamp\"><b>" . $row["title"] . "</b><br>" . $row["description"] . "</p>
								<p class=\"prop-id\"><b>Category: </b>" . $row["category"] . ",<b" . $workBold . "> Working: </b>" . $workText . ",<b> ID: </b>" . $row["id"] . "</p>
								<p class=\"prop-cat\"><b>Time Period: </b>" . $row["time_period"] . ",<b" . $availBold . "> Available: </b>" . $row["num_available"] . ",<b" . $rentedBold . "> Rented: </b>" . $row["num_rented"] . "</p>
							</div>
							";
    if (!($row = $result->fetch_assoc())) {
      $runLoop = false;
      break;
    }
  }
  $html = /* @lang HTML */
    $html .
    "</div>
					</div>
				</div>
			</div>";
  $last = $row[$sortBy];
}
$conn->close();
echo $html;
?>