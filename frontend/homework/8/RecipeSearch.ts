interface recipe {
    id: number;
    name: string;
    ingredients: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    image: string;
    rating: number;
    timeTaken: number;
}

class RecipeSearch {
    private recipes: recipe[] = [];

    async fetchRecipesFromAPI(): Promise<void> {
        try {
            const response = await fetch('https://dummyjson.com/recipes');
            const data = await response.json();
            this.recipes = data.recipes;
            console.log("Fetched the recipes from the given api");
        } catch (error) {
            console.error('Error fetching recipes:', error);
            throw error;
        }
    }

    async searchRecipes(query: string): Promise<void> {
        const matchedRecipes = this.recipes.filter(recipe =>
            recipe.name.toLowerCase().includes(query.toLowerCase())
        );
        console.log('Matched Recipes:', matchedRecipes);
    }

    printAllRecipes(): void {
        this.recipes.forEach(recipe => {
            const { id, name, ingredients, prepTimeMinutes, cookTimeMinutes, difficulty, cuisine, caloriesPerServing, image, rating } = recipe;
            const timeTaken = prepTimeMinutes + cookTimeMinutes;

            console.log('ID:', id);
            console.log('Name:', name);
            console.log('Ingredients:', ingredients);
            console.log('Prep Time (minutes):', prepTimeMinutes);
            console.log('Cook Time (minutes):', cookTimeMinutes);
            console.log('Difficulty:', difficulty);
            console.log('Cuisine:', cuisine);
            console.log('Calories per Serving:', caloriesPerServing);
            console.log('Image:', image);
            console.log('Rating:', rating);
            console.log('Time Taken (minutes):', timeTaken);
            console.log('----------------------');
        });
    }
}

const curr = new RecipeSearch();

curr.fetchRecipesFromAPI()
    .then(() => {
        curr.printAllRecipes();
        curr.searchRecipes("caprese");
    })
    .catch(error => {
        console.error('Error:', error);
    });