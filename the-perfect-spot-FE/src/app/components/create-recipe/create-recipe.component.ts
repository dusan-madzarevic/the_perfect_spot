import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RecipeModel } from 'src/app/model/recipe.model';
import { IngredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.css']
})
export class CreateRecipeComponent implements OnInit {

  constructor(private recipeService: RecipeService,
    private router: Router, private ingredientService: IngredientService) { }

recipe: RecipeModel;

recipeForm = new FormGroup({
types: new FormControl(''),
stepsText: new FormControl(''),
stepsNo: new FormControl(('')),
servings: new FormControl(('')),
prepTime: new FormControl(('')),
name: new FormControl(('')),

});

dropdownList = [];
selectedItems = [];
dropdownSettings = {};

onItemSelect(item: any) {
  console.log(item);
}
onSelectAll(items: any) {
  console.log(items);
}

ngOnInit(): void {

  this.ingredientService.getAllIngredients().subscribe((response) =>{

    this.dropdownList = response;

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true,
      enableCheckAll: false
    };

  },error => {


  })

}

create() {
let dto : RecipeModel = {

  id: 0,
  name: this.recipeForm.get('name').value,
  stepsText: this.recipeForm.get('stepsText').value,
  stepsNumber: this.recipeForm.get('stepsNo').value,
  servings: this.recipeForm.get('servings').value,
  ingredients: [],
  prepDuration: this.recipeForm.get('prepTime').value,
  type: this.recipeForm.get('types').value,
  grade: 0,
  image: null

}

this.selectedItems.forEach(element => {
  dto.ingredients.push({id: element.id, name: '', amount: 0, calories: 0});
});

this.recipeService.create(dto).subscribe((res) => {
Swal.fire({
title: 'Recipe added successfully!',
icon: 'success',
showConfirmButton: false,
position: 'center',
timer:1500,

});
this.router.navigate(['/recipes']);
},error => {
Swal.fire({
title: 'Unable to add recipe.',
icon: 'error',
showConfirmButton: false,
position: 'center',
timer:1500,

});
})
}

}
