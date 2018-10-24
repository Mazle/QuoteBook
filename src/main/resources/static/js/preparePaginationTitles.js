var result=[];
var totalPages=[[${pages.getTotalPages()}]];
var activePage=[[${pages.getNumber()}]];
var numberOfTitles = 7;
if ((totalPages)<numberOfTitles) {
    for(i=1;i<(numberOfTitles+1);i++) {
        result.push(i);
    }
}
else {
    if ((activePage+1)>3) result.push(1,-1);
    result.push((activePage-1),(activePage),(activePage+1),(activePage+2),(activePage+3));
    if ((totalPages-3)>(activePage+1)) result.push(-1,totalPages);
}
var htmlOutputCode = "<li class=\"page-item\" th:classappend=\"${pages.hasPrevious()?'':'disabled'}\">\n" +
    "                <a class=\"page-link\" th:href=\"@{/(page=${pages.getNumber()})}\">Previous</a>\n" +
    "            </li>";
for (i=0;i<(result.length+1);i++) {

    htmlOutputCode += "<li class=\"page-item\"" +
        (result[i]==-1)?"disabled":"" +
        (result[i]==(activePage+1))?"active":"" + ">\n" +
        "<a class=\"page-link\" th:href=\"/page=" + result[i] + "\">" + result[i] + "</a>\n" +
        "</li>";
}
htmlOutputCode += "<li class=\"page-item\" th:classappend=\"${pages.hasNext()?'':'disabled'}\">\n" +
    "                <a class=\"page-link\" th:href=\"@{/(page=${(pages.getNumber()+2)})}\">Next</a>\n" +
    "            </li>"
document.getElementById("paginationList").innerHTML = htmlOutputCode;