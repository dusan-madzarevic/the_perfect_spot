import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeModel } from 'src/app/model/recipe.model';
import { AuthService } from 'src/app/services/auth.service';
import { GradeService } from 'src/app/services/grade.service';
import { StarRatingColor } from '../star-rating/star-rating.component';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  @Input() public recipe: RecipeModel;
  @Input('rating') rating: number;
  @Input('starCount') starCount: number;
  @Input('color') starColor: StarRatingColor;

  isWorking: boolean = false;
  workingClass: string = '';

  constructor(private authService: AuthService,
              private gradeService: GradeService,
              private route: Router) { }

  ngOnInit(): void {
    this.gradeService.getGradeForRecipeByLoggedUser(this.recipe.id).subscribe((res)=>
    {
      this.rating = res;
      console.log(res)
    })
    this.starColor = StarRatingColor.primary
  }

  showDetails() {
    this.route.navigate(['/recipe-details/'+this.recipe.id]);
  }

  onRatingChanged(rating){
    this.rating = rating;


    this.gradeService.gradeRecipe(this.recipe.id,rating).subscribe((res)=>{
      console.log(res);
    })
    this.gradeService.getGradeForRecipeByLoggedUser(this.recipe.id).subscribe((res)=>
    {
      console.log(res)
    })

  }

  isUser() {
    return this.authService.isUser();
  }

  isAdmin() {

  }

  deleteRecipe(id: number) {

  }

  edit(recipe: RecipeModel) {

  }

}
