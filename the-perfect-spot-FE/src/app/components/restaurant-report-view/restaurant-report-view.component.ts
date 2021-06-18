import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from '../../model/restaurant.model';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {RestaurantAverageModel} from '../../model/restaurant.average.model';

@Component({
  selector: 'app-restaurant-report-view',
  templateUrl: './restaurant-report-view.component.html',
  styleUrls: ['./restaurant-report-view.component.css']
})
export class RestaurantReportViewComponent implements OnInit {
  @Input() public restaurantAvg: RestaurantAverageModel;
  restaurant: RestaurantModel = {
    workingHours: '',
    isServingAlcohol: false,
    cuisine:'',
    hasSmokingPart: false,
    hasBusinessHall: false,
    hasWifi: false,
    webSite: '',
    phone:'',
    description:'',
    name:'',
    address:'',
    id:0,
    grade:0,
    image:null,
    hasCarPark:false,
    hasLiveMusic:false,
    hasPlayground:false,
    petFriendly:false,
    prices:'',
    recommendationCount:0,
    hasAccessForDisabled:false,
    end:'',
    lat:0,
    lon:0,
    musicGenre:'',
    start:''
  }


  constructor(private route: Router,
              private authService: AuthService) { }

  ngOnInit(): void {

  }
  showDetails() {
    this.route.navigate(['/restaurant-details/'+this.restaurant.id]);
  }

  isUser() {
    return this.authService.isUser();
  }
}
