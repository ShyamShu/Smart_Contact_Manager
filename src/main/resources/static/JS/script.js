console.log("script loaded");

let currentTheme = getTheme();
console.log(currentTheme);

document.addEventListener("DOMContentLoaded", () => {
    changeTheme();
  });

function changeTheme()
{
    changePageTheme(currentTheme, "");

   const changeThemeButton = document.querySelector('#changeTheme')

   changeThemeButton.addEventListener('click' , () => {
    changeThemeButton.querySelector("span").textContent = currentTheme =="light"? "Dark":"Light";
    let oldTheme = currentTheme;
    if(currentTheme === "dark")
    {
        currentTheme = "light";
    }
    else{
        currentTheme = "dark";
    }

    changePageTheme(currentTheme, oldTheme);
    
   });
}


function setTheme(theme)
{
localStorage.setItem("theme" , theme);
}

function getTheme()
{
    let theme = localStorage.getItem("theme");
    return  theme ? theme : "light";
}
function changePageTheme(theme, oldTheme) {
    //localstorage mein update karenge
    setTheme(currentTheme);
    //remove the current theme
  
    if (oldTheme) {
      document.querySelector("html").classList.remove(oldTheme);
    }
    //set the current theme
    document.querySelector("html").classList.add(theme);
  
    // change the text of button
    document
      .querySelector("#changeTheme")
      .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
  }
  
   