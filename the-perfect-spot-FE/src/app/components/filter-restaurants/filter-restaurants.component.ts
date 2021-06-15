import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FilterObject} from '../../model/filter.object.model';
import {RestaurantModel} from '../../model/restaurant.model';
import {FilterRestaurantService} from '../../services/filter.restaurant.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-filter-restaurants',
  templateUrl: './filter-restaurants.component.html',
  styleUrls: ['./filter-restaurants.component.css']
})
export class FilterRestaurantsComponent implements OnInit {

  @Output() filterEvent: EventEmitter<FilterObject> = new EventEmitter<FilterObject>();

  availableCuisines = ['SERBIAN','ASIAN','CHINESE'];
  availablePrices = ['AFFORDABLE','EXPENSIVE','CHEAP'];
  selectedCuisines: Array<any> = [];
  selectedPrices: Array<any> = [];
  //name = '';
  filteredRestaurants: Array<RestaurantModel> = [];

  filterForm = new FormGroup({
    restName: new FormControl(''),
    cuisines: new FormControl(''),
    prices: new FormControl(('')),
  });
  constructor(private service: FilterRestaurantService) { }

  onApplyFilter(){
    let filterParams: FilterObject = {
      name: this.filterForm.controls['restName'].value,
      cuisines: this.filterForm.controls['cuisines'].value,
      prices: this.filterForm.controls['prices'].value
    };
    console.log(this.filterForm.controls['restName'].value)
    this.filterEvent.emit(filterParams);
  }

  filterRestaurants(){
    //this.culturalOfferService.getByPageFilter(1,this.expression,this.selectedTypes);
    console.log(this.filterForm.controls['cuisines'].value)
  }



  ngOnInit(): void {

  }


}
