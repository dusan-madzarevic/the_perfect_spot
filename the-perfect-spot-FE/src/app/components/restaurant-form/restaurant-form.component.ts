import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {RestaurantService} from '../../services/restaurant.service';
import {PreferencesModel} from '../../model/preferences.model';
import Swal from 'sweetalert2';
import {MatDialog} from '@angular/material/dialog';
import {RestaurantComponent} from '../restaurant/restaurant.component';
import {StarRatingColor} from '../star-rating/star-rating.component';
import {RestaurantModel} from '../../model/restaurant.model';
import {RestaurantModalComponent} from '../restaurant-modal/restaurant-modal.component';
import { MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {

  recommendForm = new FormGroup({
    cuisine: new FormControl(''),
    prices: new FormControl(''),
    goingByCar: new FormControl(('')),
    occassion: new FormControl(('')),
    accessForDisabled: new FormControl(('')),
    petFriendly: new FormControl(('')),
    locationMatters: new FormControl(('')),

  });
  lat: number =  45.267136;
  lng: number = 19.833549;



  constructor(private restaurantService: RestaurantService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.setCurrentLocation();
  }


  showMap(){

  }
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
      });
    }
  }


  recommendRestaurant() {
    let preferences: PreferencesModel = {
      prices: this.recommendForm.controls["prices"].value,
      petFriendly: this.recommendForm.controls['petFriendly'].value,
      occassion: this.recommendForm.controls['occassion'].value,
      lat: this.lat,
      goingByCar: this.recommendForm.controls["goingByCar"].value,
      cuisine: this.recommendForm.controls['cuisine'].value,
      accessForDisabled: this.recommendForm.controls['accessForDisabled'].value,
      lon: this.lng

    }
    console.log(preferences)
    this.restaurantService.recommendRestaurant(preferences).subscribe((res) => {

      const rest: RestaurantModel = res;
      console.log(rest)
      this.dialog.open(RestaurantModalComponent, {
        width: '490px',
        height: '480px',
        data: rest,
        panelClass: ['animate__animated', 'animate__heartBeat']
      });
    }, error => {
      Swal.fire({
        title: 'Error!',
        text: 'Old password is wrong.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      })
    });
  }



  markerDragEnd($event: google.maps.MouseEvent) {
    console.log($event.latLng.lat());
    this.lng = $event.latLng.lng();
    this.lat = $event.latLng.lat();

  }
}
