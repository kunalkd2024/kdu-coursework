let bills = [140, 45, 280];
let tips = [];
let finalAmount = [];


function tipCalculator(bill){
    let tip;
    if(bill < 50){
        tip = bill * 0.2;
    }
    else if(bill >= 50 && bill <= 200){
        tip = bill * 0.15;
    }
    else tip = bill * 0.1;
    return tip;
}

bills.forEach(function(bill) {
    let tip = tipCalculator(bill);
    tips.push(tip);
    finalAmount.push(bill + tip);
});

console.log(tips);
console.log(finalAmount);