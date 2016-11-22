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
            jQuery(function ($) {
                $('.accordion-body.collapse').on('shown', function (event) {
                    $(this).parent('.accordion-group').find('.accordion-toggle').addClass('selected')
                });
                $('.accordion-body.collapse').on('hidden', function (event) {
                    $(this).parent('.accordion-group').find('.accordion-toggle').removeClass('selected')
                });
            });
        }
    };
    xmlhttp.open("GET", "scripts/return-prop-html.php?sort="+sortBy+"&working="+working+"&asc="+descAsc+"&category="+category, true);
    xmlhttp.send();
}
