import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from '../../model/restaurant.model';
import {StarRatingColor} from '../star-rating/star-rating.component';
import {AuthService} from '../../services/auth.service';
import {RestaurantService} from '../../services/restaurant.service';
import {GradeService} from '../../services/grade.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  @Input() public restaurant: RestaurantModel;
  @Input('rating') rating: number;
  @Input('starCount') starCount: number;
  @Input('color') starColor: StarRatingColor;

  isWorking: boolean = false;
  workingClass: string = '';

  constructor(private authService: AuthService,
              private gradeService: GradeService,
              private route: Router) { }

  ngOnInit(): void {
    this.checkIfWorking();
    this.gradeService.getGradeForRestaurantByLoggedUser(this.restaurant.id).subscribe((res)=>
    {
      this.rating = res;
      console.log(res)
    })
    this.starColor = StarRatingColor.primary
  }

  showDetails() {
    this.route.navigate(['/restaurant-details/'+this.restaurant.id]);
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
    const id = "card"+this.restaurant.id;
    const fadeTarget = document.getElementById(id);
    const start = new Date();
    const end = new Date();
    const now = new Date();
    const midnight = new Date();
    midnight.setHours(23,59,59);
    start.setHours(parseInt(this.restaurant.workingHours.split('-')[0].split(':')[0]),0,0);
    if( now <= midnight  &&  this.restaurant.workingHours.split('-')[1] == '00:00' || this.restaurant.workingHours.split('-')[1] == '01:00'){
      end.setDate(end.getDate() + 1);
    }
    end.setHours(parseInt(this.restaurant.workingHours.split('-')[1].split(';')[0]),0, 0);

    if (now.getTime() >= start.getTime() && now.getTime() <= end.getTime()){
      this.isWorking = true;
      this.workingClass = "";
    }
    else {
      this.isWorking = false;
      this.workingClass = "closedOpacity";
    }
  }

  isUser() {
    return this.authService.isUser();
  }

  isAdmin() {

  }

  deleteRestaurant(id: number) {

  }

  edit(restaurant: RestaurantModel) {

  }
}
