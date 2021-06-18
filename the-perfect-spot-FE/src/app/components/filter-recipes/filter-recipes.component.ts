import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FilterObjectRecipe } from 'src/app/model/filter.object.model';
import { RecipeModel } from 'src/app/model/recipe.model';
import { FilterRecipeService } from 'src/app/services/filter.recipe.service';


@Component({
  selector: 'app-filter-recipes',
  templateUrl: './filter-recipes.component.html',
  styleUrls: ['./filter-recipes.component.css']
})
export class FilterRecipesComponent implements OnInit {

  @Output() filterEvent: EventEmitter<FilterObjectRecipe> = new EventEmitter<FilterObjectRecipe>();
  @Output() searchEvent: EventEmitter<String> = new EventEmitter<String>();

  //availableCuisines = ['SERBIAN','ASIAN','CHINESE'];
  selectedDishTypes: Array<any> = [];
  //name = '';
  filteredRecipes: Array<RecipeModel> = [];

  filterForm = new FormGroup({
    recipeName: new FormControl(''),
    dishTypes: new FormControl(''),
  });
  constructor(private service: FilterRecipeService) { }

  onApplyFilter(){
    let filterParams: FilterObjectRecipe = {
      name: this.filterForm.controls['recipeName'].value,
      dishTypes: this.filterForm.controls['dishTypes'].value
    };
    this.filterEvent.emit(filterParams);
  }

  onSearch(){

    this.searchEvent.emit(this.filterForm.controls['recipeName'].value);
  }





  ngOnInit(): void {

  }

}
