function displayProps() {
    var temp = document.getElementById("sortBySelect");
    temp.disabled = true;
    var sortBy = temp.options[temp.selectedIndex].value;
    temp = document.getElementById("workingSelect");
    temp.disabled = true;
    var working = temp.options[temp.selectedIndex].value;
    temp = document.getElementById("descAscSelect");
    temp.disabled = true;
    var descAsc = temp.options[temp.selectedIndex].value;
    temp = document.getElementById("categorySelect");
    temp.disabled = true;
    var category = temp.options[temp.selectedIndex].value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            document.getElementById("prop-accordion").innerHTML = this.responseText;
            var temp = document.getElementById("sortBySelect");
            temp.disabled = false;
            temp = document.getElementById("workingSelect");
            temp.disabled = false;
            temp = document.getElementById("descAscSelect");
            temp.disabled = false;
            temp = document.getElementById("categorySelect");
            temp.disabled = false;
        }
    };
    console.log("scripts/return-prop-html.php?sort="+sortBy+"&working="+working+"&asc="+descAsc+"&category="+category);
    xmlhttp.open("GET", "scripts/return-prop-html.php?sort="+sortBy+"&working="+working+"&asc="+descAsc+"&category="+category, true);
    xmlhttp.send();
}
