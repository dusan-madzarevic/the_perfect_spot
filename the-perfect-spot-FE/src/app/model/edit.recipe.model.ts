import { ImageModel } from "./image.model";

export interface IngredientEditModel{

    ingredientId: number,
    amount: number

}

export interface EditRecipeModel{

    recipeId:number,
    recipeName: string,
    servings: number,
    stepsNumber: number,
    stepsText: string,
    prepDuration: number,
    type: string,
    ingredients: IngredientEditModel[],
    image: ImageModel,
   
  }