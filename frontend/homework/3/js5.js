player = {

    firstName: "Leo",
  
    lastName: "Messi",
  
    address: {
  
      country: "Spain",
  
      city: "Barcelona",
  
    },
  
    careerInfo: {
  
      fcBarcelona: {
  
        appearances: 780,
  
        goals: {
  
          premierLeagueGoals: 590,
  
          championsLeagueGoals: 50,
  
        },
  
      },
  
    },
};

function getKeyandValue(player){
    let keys = [];
    let values = [];

    function helper(player){
        for(let key in player){
            if(typeof player[key] === 'object' && player[key] !== null){
                helper(player[key]);
            }
            else{
                keys.push(key);
                values.push(player[key]);
            }
        }
    }

    helper(player)

    console.log("Keys : ", keys);
    console.log("Values : ", values);
};

getKeyandValue(player);