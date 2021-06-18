import { Component, OnInit } from '@angular/core';
import {RestaurantService} from '../../services/restaurant.service';
import {ActivatedRoute, Router} from '@angular/router';
import {RestaurantModel} from '../../model/restaurant.model';
import {FormControl, FormGroup} from '@angular/forms';
import {EditRestaurantModel} from '../../model/edit.restaurant.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent implements OnInit {

  constructor(private restaurantService: RestaurantService,
              private router: Router) { }

  restaurant: RestaurantModel;

  restaurantForm = new FormGroup({
    cuisine: new FormControl(''),
    prices: new FormControl(''),
    accessForDisabled: new FormControl(('')),
    petFriendly: new FormControl(('')),
    name: new FormControl(('')),
    isServingAlcohol: new FormControl(('')),
    address:new FormControl(('')),
    phone:new FormControl(('')),
    webSite: new FormControl(('')),
    hasCarPark: new FormControl(('')),
    hasWifi: new FormControl(('')),
    hasBusinessHall: new FormControl(('')),
    hasPlayground: new FormControl(('')),
    hasLiveMusic: new FormControl(('')),
    hasSmokingPart: new FormControl(('')),
    description: new FormControl(('')),
    image: new FormControl(('')),
    lat: new FormControl(('')),
    lon:new FormControl(('')),
    musicGenre: new FormControl(('')),
    start: new FormControl(('')),
    end: new FormControl(('')),

  });

  lat: number = 0;
  lng: number = 0;

  ngOnInit(): void {
      this.setCurrentLocation();
  }

  create() {
    let dto : RestaurantModel = {
      end: this.restaurantForm.controls['end'].value,
      id: 0,
      image: null,
      start: this.restaurantForm.controls['start'].value,
      name: this.restaurantForm.controls['name'].value,
      cuisine: this.restaurantForm.controls['cuisine'].value,
      musicGenre: this.restaurantForm.controls['musicGenre'].value,
      prices: this.restaurantForm.controls['prices'].value,
      description: this.restaurantForm.controls['description'].value,
      webSite: this.restaurantForm.controls['webSite'].value,
      isServingAlcohol: this.restaurantForm.controls['isServingAlcohol'].value,
      hasCarPark: this.restaurantForm.controls['hasCarPark'].value,
      hasWifi: this.restaurantForm.controls['hasWifi'].value,
      hasBusinessHall: this.restaurantForm.controls['hasBusinessHall'].value,
      hasPlayground:  this.restaurantForm.controls['hasPlayground'].value,
      hasLiveMusic: this.restaurantForm.controls['hasLiveMusic'].value,
      hasSmokingPart: this.restaurantForm.controls['hasSmokingPart'].value,
      accessForDisabled: this.restaurantForm.controls['accessForDisabled'].value,
      petFriendly: this.restaurantForm.controls['petFriendly'].value,
      phone: this.restaurantForm.controls['phone'].value,
      address: this.restaurantForm.controls['address'].value,
      lat: this.restaurantForm.controls['lat'].value,
      lon: this.restaurantForm.controls['lon'].value,
      workingHours:'',
      recommendationCount:0,
      grade:0
    }
    this.restaurantService.create(dto).subscribe((res) => {
      Swal.fire({
        title: 'Restaurant added successfully!',
        icon: 'success',
        showConfirmButton: false,
        position: 'center',
        timer:1500,

      });
      this.router.navigate(['/restaurants']);
    },error => {
      Swal.fire({
        title: 'Unable to add restaurant.',
        icon: 'error',
        showConfirmButton: false,
        position: 'center',
        timer:1500,

      });
    })
  }
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
      });
    }
  }
  markerDragEnd($event: google.maps.MouseEvent) {
    console.log($event.latLng.lat());
    this.lng = $event.latLng.lng();
    this.lat = $event.latLng.lat();

  }

}
