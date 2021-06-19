import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeAverageModel } from 'src/app/model/recipe.average.model';
import { RecipeModel } from 'src/app/model/recipe.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-recipe-report-view',
  templateUrl: './recipe-report-view.component.html',
  styleUrls: ['./recipe-report-view.component.css']
})
export class RecipeReportViewComponent implements OnInit {

  @Input() public recipeAvg: RecipeAverageModel;
  recipe: RecipeModel = {
      id: 0,
      name: '',
      servings: 0,
      stepsNumber: 0,
      stepsText: '',
      type: '',
      prepDuration: 0,
      ingredients: [],
      grade: 0,
      image: null
  }


  constructor(private route: Router,
              private authService: AuthService) { }

  ngOnInit(): void {

  }
  showDetails() {
    this.route.navigate(['/restaurant-details/'+this.recipe.id]);
  }

  isUser() {
    return this.authService.isUser();
  }

}
