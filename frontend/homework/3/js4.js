let input = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';
let jsonObject = JSON.parse(input);

for(let key in jsonObject){
    if(key !== 'email' && key !== 'age'){
        jsonObject[key] = jsonObject[key].toUpperCase();
    }
}

console.log(jsonObject);

delete jsonObject.email;

let output = JSON.stringify(jsonObject);
console.log(output);