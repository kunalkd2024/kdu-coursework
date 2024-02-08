class Shoe{
    constructor(type, color, size, price){
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}

class Shirt{
    constructor(type, color, size, price){
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}

let shoe1 = new Shoe("sneakers", "black", 9, 50);
let shoe2 = new Shoe("boots", "brown", 10, 80);

let shirt1 = new Shirt("t-shirt", "blue", "M", 20);
let shirt2 = new Shirt("polo shirt", "white", "L", 30);
let shirt3 = new Shirt("dress shirt", "black", "XL", 40);

let shoes = [shoe1, shoe2];
let shirts = [shirt1, shirt2, shirt3];

let warehouse = [];
for(let shoe of shoes){
    warehouse.push(shoe);
}
for(let shirt of shirts){
    warehouse.push(shirt);
}

let total = 0;
warehouse.forEach(item => {
    total += item.price;
});

console.log("Total cost of all the items : ", total);

warehouse.sort(function(a, b){
    return b.price - a.price;
});

console.log("List of warehouse items in descending order : ", warehouse);

console.log("The blue items are ");
warehouse.forEach(item => {
    if(item.color == "blue"){
        console.log(item);
    }
});