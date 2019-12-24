/* Script for Default Layout

Purpose: Garnero Engineering
Author: Nicholas Jones
Date: 05/28/2018
Version: 1.0
*/

//Start of Code

//Variables
let windowX = $(window).width(),
    windowY = $(window).height(),
    pageWidth = document.body.style.width,
    pageHeight = document.body.style.height;





/*
let dropButtonToggle = true;
//End of Variables

//Initialize Screen
screenChange();
//End of Initialize Screen

//Screen Resize

window.addEventListener("resize", screenChange);

  function screenChange () {
     windowX = $(window).width();
     windowY = $(window).height();
     pageWidth = windowX.toString() + "px";
     pageHeight = windowY.toString() + "px";

     //Switch to Mobile
     if(windowX <= 860){

         //Nav and Drop Down Menu
         document.getElementById('topNav').style.display = "none";

         document.getElementById('dropButton').style.width = (windowX / 10) + "px";
         document.getElementById('dropButton').style.height = (windowY / 25) + "px";
         document.getElementById('dropDownMenu').style.width = (windowX / 10 + 2) + "px";
         document.getElementById('dropDownMenu').style.display = "block";
         document.getElementsByClassName('dropDown-content')[0].style.display = "none";

         //TODO Finish top

         document.getElementById('join_Small').style.display = "block";
         document.getElementById('join_Large').style.display = "none";

         if(windowX <= 768){
             //document.getElementById('cartButtonImage').style.width = "15%";
             document.getElementById('join_Small_ul').style.fontSize = "100%";
             document.getElementById('searchButtonSmall').style.width = "20%";

         }
         if(windowX <= 520){
             //document.getElementById('cartButtonImage').style.width = "15%";
             document.getElementById('join_Small_ul').style.fontSize = "80%";
             document.getElementById('searchButtonSmall').style.width = "30%";

         }
          if(windowX <= 400){
             //document.getElementById('cartButtonImage').style.width = "15%";
             document.getElementById('join_Small_ul').style.fontSize = "60%";
             document.getElementById('searchButtonSmall').style.width = "35%";

         }



     }
     //Switch to Desktop
     else {
         //Reset 860
         document.getElementById('topNav').style.display = "block";
         document.getElementById('dropDownMenu').style.display = "none";
         document.getElementById('join_Small').style.display = "none";
         document.getElementById('join_Large').style.display = "block";

         //Reset 768 or 520
         document.getElementById('cartButtonImage').style.width = "15%";
         document.getElementById('join_Large_ul').style.fontSize = "120%";
         document.getElementById('searchButtonLarge').style.width = "20%";
         document.getElementById('searchButtonLarge').style.maxWidth = "100%"

     }
     if(windowX >= 1268){
         document.getElementById('searchButtonLarge').style.maxWidth = "120%";
     }
     if(windowX >= 1900){
         //document.getElementById('cartButtonImage').style.width = "15%";
         document.getElementById('join_Large_ul').style.fontSize = "200%";

     }

}
//End Screen Resize

//Toggle Drop Button
document.getElementById('dropButton').addEventListener('click', (e) => {

    if(dropButtonToggle) {
        document.getElementsByClassName('dropDown-content')[0].style.display = "block";
        dropButtonToggle = false;
    }
    else{
        document.getElementsByClassName('dropDown-content')[0].style.display = "none";
        dropButtonToggle = true;
    }
});
window.onclick = function(e) {

    if(!(e.target.matches('.dropButton') || e.target.matches('#dropDownIcon'))){

        document.getElementsByClassName('dropDown-content')[0].style.display = "none";
        dropButtonToggle = true;
    }
};
//End Toggle Drop Button

//Logo Top

document.getElementById('logo')

//End Logo Top

//Search Box
document.getElementById('searchBox').addEventListener('keyup', (e) => {

    if(e.key === "Enter") {

        //TODO I need to open a search Page

        }
    });
document.getElementById('searchIcon').addEventListener('click', (e) => {

    //TODO Same as searchBox

});
//End Search Box

//End of Code

//Notes
/*
$(window).height();   // returns height of browser viewport
$(document).height(); // returns height of HTML document (same as pageHeight in screenshot)
$(window).width();   // returns width of browser viewport
$(document).width(); // returns width of HTML document (same as pageWidth in screenshot)
screen.height;
screen.width;
*/
/*
let w = window;
    d = document,
    e = d.documentElement,
    g = d.getElementsByTagName('body')[0],
    x = w.innerWidth || e.clientWidth || g.clientWidth,
    y = w.innerHeight|| e.clientHeight|| g.clientHeight;
*/

