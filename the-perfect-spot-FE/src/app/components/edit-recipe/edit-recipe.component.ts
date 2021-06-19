import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EditRecipeModel } from 'src/app/model/edit.recipe.model';
import { RecipeModel } from 'src/app/model/recipe.model';
import { IngredientService } from 'src/app/services/ingredient.service';
import { RecipeService } from 'src/app/services/recipe.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit {

  constructor(private recipeService: RecipeService,
    private router: Router, private ingredientService: IngredientService, private route: ActivatedRoute) { }

recipe: RecipeModel;
id: string = '';
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
  this.id = this.route.snapshot.paramMap.get('id');
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

    this.recipeService.getOneById(this.id).subscribe((res)=>{

      this.recipe = res;
      this.recipeForm.controls['name'].setValue(res.name);
      this.recipeForm.controls['prepTime'].setValue(res.prepDuration);
      this.recipeForm.controls['stepsNo'].setValue(res.stepsNumber);
      this.recipeForm.controls['stepsText'].setValue(res.stepsText);
      this.recipeForm.controls['servings'].setValue(res.servings);
      this.recipeForm.controls['types'].setValue(res.type);

      res.ingredients.forEach(element => {
        this.selectedItems.push({id: element.id, name: element.name});
      });

    });

  },error => {


  })

}

update() {
let dto : EditRecipeModel = {

  recipeId: Number(this.id),
  recipeName: this.recipeForm.get('name').value,
  stepsText: this.recipeForm.get('stepsText').value,
  stepsNumber: this.recipeForm.get('stepsNo').value,
  servings: this.recipeForm.get('servings').value,
  ingredients: [],
  prepDuration: this.recipeForm.get('prepTime').value,
  type: this.recipeForm.get('types').value,
  image: null

}

this.selectedItems.forEach(element => {
  dto.ingredients.push({ingredientId: element.id, amount: 10});
});

this.recipeService.update(dto, this.id).subscribe((res) => {
Swal.fire({
title: 'Recipe updated successfully!',
icon: 'success',
showConfirmButton: false,
position: 'center',
timer:1500,

});
this.router.navigate(['/recipes']);
},error => {
Swal.fire({
title: 'Unable to update recipe.',
icon: 'error',
showConfirmButton: false,
position: 'center',
timer:1500,

});
})
}

}
