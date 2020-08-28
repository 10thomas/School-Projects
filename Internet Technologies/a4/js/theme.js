var themeTest = localStorage.getItem('theme') + "Theme";
console.log(document.getElementById("cssFile").href);
document.getElementById("cssFile").href = "css/" + themeTest + ".css";
console.log(document.getElementById("cssFile").href);