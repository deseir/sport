// $(document).ready(function(){
//     $("img").click(function () {
//         var imgName=$(this).attr("src");
//         var newwin = window.open();
//         myimg= newwin.document.createElement("img");
//         myimg.src=imgName;
//         newwin.document.body.appendChild(myimg);
//         //var imgPath="C:/nodedata/node/loantest/identityPic/2018-08-01/3e57e6ae-55cd-4c3e-ba7f-c9c8b648928b.png";
//         //window.open(imgName);
//     });
// });

$(document).delegate("img","click",function () {
    var imgName=$(this).attr("src");
    var newwin = window.open();
    myimg= newwin.document.createElement("img");
    myimg.src=imgName;
    newwin.document.body.appendChild(myimg);
});