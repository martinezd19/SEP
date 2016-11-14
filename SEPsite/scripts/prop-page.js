function displayProps() {
    var temp = document.getElementById("sortBySelect");
    var sortBy = temp.options[temp.selectedIndex].text;
    temp = document.getElementById("workingSelect");
    var working = temp.options[temp.selectedIndex].text;
    temp = document.getElementById("descAscSelect");
    var descAsc = temp.options[temp.selectedIndex].text;
    temp = document.getElementById("categorySelect");
    var category = temp.options[temp.selectedIndex].text;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            document.getElementById("accordion173").innerHTML = this.responseText;
        }
    }
    xmlhttp.open("GET", "return-prop-html.php?sort="+sortBy+"&working="+working+"&asc="+descAsc+"&category="+category, true);
    xmlhttp.send();
}
