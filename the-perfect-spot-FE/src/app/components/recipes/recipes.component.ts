import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { FilterObjectRecipe } from 'src/app/model/filter.object.model';
import { RecipeModel } from 'src/app/model/recipe.model';
import { RecipeService } from 'src/app/services/recipe.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  private recipes: Array<RecipeModel> = [];
  public filterObj: FilterObjectRecipe = { name: '', dishTypes: [] };
  public isFilter: boolean = false;
  public isSearch: boolean = false;
  public restName: String = '';
  p_length = 0;
  pageSize = 6;
  pageIndex = 0;
  showFirstLastButtons = true;

  constructor(private authService: AuthService,
              private recipeService: RecipeService ) { }

  ngOnInit(): void {
    this.recipeService.getAll(this.pageIndex).subscribe((response) => {
      this.recipes = response.content;
      this.p_length= response.totalElements;
      console.log(this.recipes)
    });
    
  }

  isUser() {
    return this.authService.isUser();
  }

  getAllRecipes() {
    return this.recipes || [];
  }

  handlePageEvent(event: PageEvent) {
    this.p_length = event.length;
    this.pageIndex = event.pageIndex;

    this.recipeService.getAll(this.pageIndex).subscribe((response) => {
      this.recipes = response.content;
      this.p_length = response.totalElements;
    })}

  filterRecipes(data: FilterObjectRecipe) {
    this.isFilter = true;
    this.pageIndex = 1;
    this.filterObj = data;
    this.recipeService.getByPageFilter(this.pageIndex, data.dishTypes).subscribe(
      (res) => {
        this.recipes = res.content;
        console.log(res)
      }
      )
    }

  searchRecipes(data: String) {
    this.isSearch = true;
    this.pageIndex = 1;
    this.restName = data;
    this.recipeService.getByPageSearch(this.pageIndex, data).subscribe(
      (res) => {
        this.recipes = res.content;
      }
    )
  }
}
