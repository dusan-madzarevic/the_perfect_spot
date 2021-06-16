import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {RestaurantModel} from '../../model/restaurant.model';
import {RestaurantService} from '../../services/restaurant.service';
import {PageEvent} from "@angular/material/paginator";
import {FilterObject} from '../../model/filter.object.model';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

  private restaurants: Array<RestaurantModel> = [];
  public filterObj: FilterObject = { name: '', cuisines: [], prices: [] };
  public isFilter: boolean = false;
  public isSearch: boolean = false;
  public restName: String = '';
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

  filterRestaurants(data: FilterObject) {
    this.isFilter = true;
    this.pageIndex = 1;
    this.filterObj = data;
    this.restService.getByPageFilter(this.pageIndex, data.prices,data.cuisines).subscribe(
      (res) => {
        this.restaurants = res.content;
        console.log(res)
      }
      )
    }

  searchRestaurants(data: String) {
    this.isSearch = true;
    this.pageIndex = 1;
    this.restName = data;
    this.restService.getByPageSearch(this.pageIndex, data).subscribe(
      (res) => {
        this.restaurants = res.content;
      }
    )
  }

}
