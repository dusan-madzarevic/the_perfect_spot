import { Component, OnInit } from '@angular/core';
import {RestaurantService} from '../../services/restaurant.service';
import {RestaurantModel} from '../../model/restaurant.model';
import {FormControl, FormGroup} from '@angular/forms';
import {ImageModel} from '../../model/image.model';
import {ActivatedRoute} from '@angular/router';
import {EditRestaurantModel} from '../../model/edit.restaurant.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.css']
})
export class EditRestaurantComponent implements OnInit {

  constructor(private restaurantService: RestaurantService,
              private route: ActivatedRoute,) { }
  id: string = '';
  restaurant: RestaurantModel;
  restaurantForm = new FormGroup({
    cuisine: new FormControl(''),
    prices: new FormControl(''),
    accessForDisabled: new FormControl(('')),
    petFriendly: new FormControl(('')),
    name: new FormControl(('')),
    isServingAlcohol: new FormControl(('')),
    recommendationCount: new FormControl(('')),
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
    this.id = this.route.snapshot.paramMap.get('id');
    this.restaurantService.getOneById(this.id).subscribe((res)=>{
      this.restaurant = res;
      console.log(res)
      this.restaurantForm.controls['name'].setValue(res.name);
      this.restaurantForm.controls['cuisine'].setValue(res.cuisine);
      this.restaurantForm.controls['musicGenre'].setValue(res.musicGenre);
      this.restaurantForm.controls['prices'].setValue(res.prices);
      this.restaurantForm.controls['description'].setValue(res.description);
      this.restaurantForm.controls['webSite'].setValue(res.webSite);
      this.restaurantForm.controls['isServingAlcohol'].setValue(res.isServingAlcohol);
      this.restaurantForm.controls['hasCarPark'].setValue(res.hasCarPark);
      this.restaurantForm.controls['hasWifi'].setValue(res.hasWifi);
      this.restaurantForm.controls['hasBusinessHall'].setValue(res.hasBusinessHall);
      this.restaurantForm.controls['hasPlayground'].setValue(res.hasPlayground);
      this.restaurantForm.controls['hasLiveMusic'].setValue(res.hasLiveMusic);
      this.restaurantForm.controls['hasSmokingPart'].setValue(res.hasSmokingPart);
      this.restaurantForm.controls['accessForDisabled'].setValue(res.accessForDisabled);
      this.restaurantForm.controls['petFriendly'].setValue(res.petFriendly);
      this.restaurantForm.controls['phone'].setValue(res.phone);
      this.restaurantForm.controls['address'].setValue(res.address);
      this.restaurantForm.controls['lat'].setValue(res.lat);
      this.restaurantForm.controls['lon'].setValue(res.lon);
      this.restaurantForm.controls['start'].setValue(res.start);
      this.restaurantForm.controls['end'].setValue(res.end);
      this.lat = res.lat;
      this.lng = res.lon;
    })
  }

  edit() {
    let dto : EditRestaurantModel = {
      end: this.restaurantForm.controls['end'].value,
      id: this.restaurant.id,
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
      lon: this.restaurantForm.controls['lon'].value
    }
    this.restaurantService.update(dto,this.id).subscribe((res) => {
      Swal.fire({
        title: 'Restaurant updated successfully!',
        icon: 'success',
        showConfirmButton: false,
        position: 'center',
       timer:1500,

      });
      window.location.reload();
    },error => {
      Swal.fire({
        title: 'Unable to update restaurant.',
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
