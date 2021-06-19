import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RestaurantService} from '../../services/restaurant.service';
import {RestaurantModel} from '../../model/restaurant.model';
import {StarRatingColor} from '../star-rating/star-rating.component';
import {GradeService} from '../../services/grade.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  @Input('rating') rating: number;
  @Input('starCount') starCount: number;
  @Input('color') starColor: StarRatingColor;

  constructor(private route: ActivatedRoute,
              private restaurantService: RestaurantService,
              private gradeService: GradeService,
              private authService: AuthService) { }
  id: string = '';
  restaurant?: RestaurantModel;
  isWorking: boolean = false;
  lat: number;
  lng: number;

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.restaurantService.getOneById(this.id).subscribe((res)=>{
      this.restaurant = res;
      this.lat = this.restaurant.lat;
      this.lng = this.restaurant.lon;
      this.checkIfWorking();
      if(this.isUser()) {
        this.gradeService.getGradeForRestaurantByLoggedUser(this.restaurant.id).subscribe((res) => {
          this.rating = res;
        });
        this.starColor = StarRatingColor.primary;
      }
    })


  }
  onRatingChanged(rating){
    this.rating = rating;


    this.gradeService.gradeRestaurant(this.restaurant.id,rating).subscribe((res)=>{
      console.log(res);
    })
    this.gradeService.getGradeForRestaurantByLoggedUser(this.restaurant.id).subscribe((res)=>
    {
      console.log(res)
    })

  }

  checkIfWorking(){
    console.log(this.isWorking)

    const start = new Date();
    const end = new Date();
    const now = new Date();
    const midnight = new Date();
    midnight.setHours(23,59,59);
    start.setHours(parseInt(this.restaurant?.workingHours.split('-')[0].split(':')[0]),0,0);
    if( now <= midnight  &&  this.restaurant?.workingHours.split('-')[1] == '00:00' || this.restaurant?.workingHours.split('-')[1] == '01:00'){
      end.setDate(end.getDate() + 1);
    }
    end.setHours(parseInt(this.restaurant?.workingHours.split('-')[1].split(';')[0]),0, 0);

    if (now.getTime() >= start.getTime() && now.getTime() <= end.getTime()){
      this.isWorking = true;
      console.log(this.isWorking)

    }
    else {
      this.isWorking = false;
    }
  }

  isUser() {
    return this.authService.isUser();
  }
}
