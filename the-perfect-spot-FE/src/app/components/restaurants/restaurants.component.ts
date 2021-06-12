import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {RestaurantModel} from '../../model/restaurant.model';
import {RestaurantService} from '../../services/restaurant.service';
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

  private restaurants: Array<RestaurantModel> = [];

  p_length = 0;
  pageSize = 6;
  pageIndex = 0;
  showFirstLastButtons = true;

  constructor(private authService: AuthService,
              private restService: RestaurantService ) { }

  ngOnInit(): void {
    this.restService.getAll(this.pageIndex).subscribe((response) => {
      this.restaurants = response.content;
      this.p_length= response.totalElements;
    });
    console.log(this.restaurants)
  }

  isUser() {
    return this.authService.isUser();
  }

  getAllRestaurants() {
    return this.restaurants || [];
  }

  handlePageEvent(event: PageEvent) {
    this.p_length = event.length;
    this.pageIndex = event.pageIndex;

    this.restService.getAll(this.pageIndex).subscribe((response) => {
      this.restaurants = response.content;
      this.p_length = response.totalElements;
    })}
}
