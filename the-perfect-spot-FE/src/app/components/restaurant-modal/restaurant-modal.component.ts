import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {RestaurantModel} from '../../model/restaurant.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-restaurant-modal',
  templateUrl: './restaurant-modal.component.html',
  styleUrls: ['./restaurant-modal.component.css']
})
export class RestaurantModalComponent implements OnInit {

  constructor( @Inject(MAT_DIALOG_DATA) public restaurant: RestaurantModel,
               private route: Router) { }

  ngOnInit(): void {
  }

  showDetails() {
    this.route.navigate(['/restaurant-details/'+this.restaurant.id]);
  }
}
