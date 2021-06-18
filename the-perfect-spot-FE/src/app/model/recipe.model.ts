import { ImageModel } from "./image.model";

interface IngredientModel{

  id: number,
  name: string,
  amount: number,
  calories: number

}

export interface RecipeModel {
    id:number,
    name: string,
    servings: number,
    stepsNumber: number,
    stepsText: string,
    prepDuration: number,
    ingredients: IngredientModel[],
    grade: number,
    image: ImageModel,
  }