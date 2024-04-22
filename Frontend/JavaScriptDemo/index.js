console.log("Hello World");
document.getElementById("app").innerHTML = "hello world";

const arr = ["john", "hans", "lasse", "tom"];

const arrList = arr.map((name) =>{
  return '<li>'+name+ ' Doe' + '</li>';
})

document.getElementById("app").innerHTML = '<ul>'+arrList.join('')+'</ul>';