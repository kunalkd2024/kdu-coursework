let days = ["Sunday   ","   Monday  ",

"  Tuesday","Wednesday  ","  Thursday   ","   Friday",

"Saturday    "];

let final = [];
days.forEach(function(day){
    day = day.trim();
    let ready = day.substring(0,3).toUpperCase();
    final.push(ready);
});

console.log(final);