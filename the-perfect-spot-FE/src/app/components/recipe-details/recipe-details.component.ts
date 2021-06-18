import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecipeModel } from 'src/app/model/recipe.model';
import { AuthService } from 'src/app/services/auth.service';
import { GradeService } from 'src/app/services/grade.service';
import { RecipeService } from 'src/app/services/recipe.service';
import { StarRatingColor } from '../star-rating/star-rating.component';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  @Input('rating') rating: number;
  @Input('starCount') starCount: number;
  @Input('color') starColor: StarRatingColor;

  constructor(private route: ActivatedRoute,
              private recipeService: RecipeService,
              private gradeService: GradeService,
              private authService: AuthService) { }
  id: string = '';
  recipe?: RecipeModel;


  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.recipeService.getOneById(this.id).subscribe((res)=>{
      this.recipe = res;
      this.gradeService.getGradeForRecipeByLoggedUser(this.recipe.id).subscribe((res)=>
      {
        this.rating = res;
        console.log(res)
      })
      this.starColor = StarRatingColor.primary
    })
    console.log(this.recipe)


  }
  onRatingChanged(rating){
    this.rating = rating;


    this.gradeService.gradeRestaurant(this.recipe.id,rating).subscribe((res)=>{
      console.log(res);
    })
    this.gradeService.getGradeForRestaurantByLoggedUser(this.recipe.id).subscribe((res)=>
    {
      console.log(res)
    })

  }

  isUser() {
    return this.authService.isUser();
  }

}
