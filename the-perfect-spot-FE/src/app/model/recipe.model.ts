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
    stepNo: number,
    stepText: string,
    prepDuration: number,
    ingredients: IngredientModel[],
    grade: number,
    image: ImageModel,
  }