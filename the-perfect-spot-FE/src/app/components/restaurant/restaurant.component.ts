import {Component, Input, OnInit} from '@angular/core';
import {RestaurantModel} from '../../model/restaurant.model';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  @Input() public restaurant: RestaurantModel;
  isWorking: boolean = false;
  workingClass: string = '';

  constructor() { }

  ngOnInit(): void {
    this.checkIfWorking();
    console.log(this.restaurant.name , this.isWorking)
  }

  showDetails() {

  }

  checkIfWorking(){
    const id = "card"+this.restaurant.id;
    const fadeTarget = document.getElementById(id);
    const start = new Date();
    const end = new Date();
    const now = new Date();
    const midnight = new Date();
    midnight.setHours(23,59,59);
    console.log(now<midnight)
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

  }

  isAdmin() {

  }

  deleteRestaurant(id: number) {

  }

  edit(restaurant: RestaurantModel) {

  }
}
