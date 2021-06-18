import { EventEmitter, Component, Input, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'mat-star-rating-recipe',
  templateUrl: './star-rating-recipe.component.html',
  styleUrls: ['./star-rating-recipe.component.css'],
  encapsulation: ViewEncapsulation.Emulated
})
export class StarRatingRecipeComponent implements OnInit {

  @Input('rating') private rating: number;
  @Input('starCount')  starCount: number;
  @Input('color')  color: StarRatingColor;
  @Input('recipeName') recipeName: string;
  @Output() private ratingUpdated = new EventEmitter();

   snackBarDuration: number = 2000;
   ratingArr = [];

  constructor(private snackBar: MatSnackBar) {
  }


  ngOnInit() {
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
  }
  onClick(rating:number) {
    console.log(rating)
    this.snackBar.open('You rated recipe '+ this.recipeName+' '+ rating + ' / ' + this.starCount, '', {
      duration: this.snackBarDuration
    });
    this.ratingUpdated.emit(rating);
    return false;
  }

  showIcon(index:number) {
    if (this.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }

}
export enum StarRatingColor {
  primary = "primary",
  accent = "accent",
  warn = "warn",
}