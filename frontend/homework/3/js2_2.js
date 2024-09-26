function convertToCode(input){
    input = input.trim();
    let result = '';
    for(i = 0;i < input.length;i++){
        let char = input[i];
        switch (char) {
            case 'a':
                result += '4';
                break;
            case 'e':
                result += '3';
                break;
            case 'i':
                result += '1';
                break;
            case 'o':
                result += '0';
                break;
            case 's':
                result += '5';
                break;
            default:
                result += char;
        }
    }
    return result;
};

console.log(convertToCode("javascript is cool  "));
console.log(convertToCode("programming is fun"));
console.log(convertToCode("  become a coder"));