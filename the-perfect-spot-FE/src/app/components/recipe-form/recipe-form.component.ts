import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RecipeService } from 'src/app/services/recipe.service';
import { SkillLevel } from 'src/app/util/skill-enum';
import Swal from 'sweetalert2';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  cookingSkills = [
    {id: 0, level: "Basic level"},
    {id: 1, level: "Medium level"},
    {id: 2, level: "High level"}
  ];

  types = [
    {id: 0, typeName: "Appetizer"},
    {id: 1, typeName: "Main dish"},
    {id: 2, typeName: "Dessert"}
  ];

  selectedSkill = null;
  selectedType = null;

  recipeForm = new FormGroup({
    cookingSkill: new FormControl(''),
    lowCalorie: new FormControl(''),
    servings: new FormControl(''),
    limitedTime: new FormControl(''),
    type: new FormControl('')
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

  constructor(private recipeService: RecipeService, private route: ActivatedRoute, private ingredientService: IngredientService) { }

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

  requestRecipes(): any {
    let request = {
      skill: this.recipeForm.get('cookingSkill').value,
      lowCalorie: this.recipeForm.get('lowCalorie').value,
      servings: this.recipeForm.get('servings').value,
      ingredients: [],
      limitedTime: this.recipeForm.get('limitedTime').value,
      type: this.recipeForm.get('type').value
    }

    this.selectedItems.forEach(element => {
      request.ingredients.push({ingredientId: element.id, amount: 0});
    });


    this.recipeService.getRecipes(request).subscribe((response) =>{
      Swal.fire({
        icon: 'success',
        title: 'Success!',
        text: 'Request was successful!'
      })
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Something went wrong. Please try again later!',
        confirmButtonColor: '#DC143C'
      })
    })

  }

}
