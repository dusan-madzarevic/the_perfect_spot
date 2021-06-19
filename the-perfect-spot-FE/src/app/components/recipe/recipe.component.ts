import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeModel } from 'src/app/model/recipe.model';
import { AuthService } from 'src/app/services/auth.service';
import { GradeService } from 'src/app/services/grade.service';
import { RecipeService } from 'src/app/services/recipe.service';
import Swal from 'sweetalert2';
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
              private route: Router,
              private recipeService: RecipeService) { }

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
    return this.authService.isAdmin();
  }

  deleteRecipe(id: number) {
    this.recipeService.delete(id).subscribe((res) =>{
      Swal.fire({
        title: 'Recipe deleted successfully!',
        icon: 'success',
        showConfirmButton: false,
        position: 'center',
        timer:2000,

      }).then(() => {
        window.location.reload();
      });
    },error => {
      Swal.fire({
        title: 'Unable to delete recipe.',
        icon: 'error',
        showConfirmButton: false,
        position: 'center',
        timer:2000,

      });
    })
  }

  edit(id: number) {
    this.route.navigate(['/edit-recipe/'+ id]);
  }

}
