var result=[];
var pagesHasPrevious = [[${pages.hasPrevious()}]];
var pagesHasNext = [[${pages.hasNext()}]];
var totalPages=[[${pages.getTotalPages()}]];
var activePage=[[${pages.getNumber()}]];
var numberOfTitles = 7;
if ((totalPages)<numberOfTitles) {
    for(i=1;i<(totalPages);i++) {
        result.push(i);
    }
}
else {
    if ((activePage+1)>3) result.push(1,-1);
    result.push((activePage-1),(activePage),(activePage+1),(activePage+2),(activePage+3));
    if ((totalPages-3)>(activePage+1)) result.push(-1,totalPages);
}
var htmlOutputCode = "<li class=\"page-item ";
if (!pagesHasPrevious) htmlOutputCode += " disabled";
htmlOutputCode += "\">\n" +
    "                <a class=\"page-link\" href=\"/?page=" + activePage + "\">Previous</a>\n" +

    "            </li>";

alert("1\n"+htmlOutputCode);
for (i=0;i<(result.length);i++) {

    htmlOutputCode += "<li class=\"page-item";
    if (result[i]==-1) htmlOutputCode+=" disabled";
    if (result[i]==(activePage+1)) htmlOutputCode+=" active";
    htmlOutputCode += "\">\n" +
        "<a class=\"page-link\" href=\"/?page=" + result[i] + "\">" + result[i] + "</a>\n" +
        "</li>";
}
alert("2\n"+htmlOutputCode);
htmlOutputCode += "<li class=\"page-item ";
if (!pagesHasNext) htmlOutputCode += " disabled";
htmlOutputCode += "\">\n" +
    "                <a class=\"page-link\" href=\"/?page=" + (activePage+2) + "\">Next</a>\n" +
    "            </li>";
alert("3\n"+htmlOutputCode);
document.getElementById("paginationList").innerHTML = htmlOutputCode;