import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { RecipeModel } from 'src/app/model/recipe.model';

@Component({
  selector: 'app-recipe-modal',
  templateUrl: './recipe-modal.component.html',
  styleUrls: ['./recipe-modal.component.css']
})
export class RecipeModalComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public recipe: RecipeModel,
  private route: Router) { }

  ngOnInit(): void {
  }

  showDetails() {
    this.route.navigate(['/recipe-details/'+this.recipe.id]);
  }

}
