import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {RestaurantService} from '../../services/restaurant.service';
import {PreferencesModel} from '../../model/preferences.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {

  recommendForm = new FormGroup({
    cuisine: new FormControl(''),
    prices: new FormControl(''),
    lat: new FormControl(('')),
    lon: new FormControl(('')),
    goingByCar: new FormControl(('')),
    occassion: new FormControl(('')),
    accessForDisabled: new FormControl(('')),
    petFriendly: new FormControl(('')),
    locationMatters: new FormControl(('')),

  });
  constructor(private restaurantService: RestaurantService) { }

  ngOnInit(): void {
  }

  recommendRestaurant() {
    let preferences : PreferencesModel = {
      prices: this.recommendForm.controls["prices"].value,
      petFriendly: this.recommendForm.controls['petFriendly'].value,
      occassion: this.recommendForm.controls['occassion'].value,
      lat: this.recommendForm.controls['lat'].value,
      goingByCar: this.recommendForm.controls["goingByCar"].value,
      cuisine: this.recommendForm.controls['cuisine'].value,
      accessForDisabled: this.recommendForm.controls['accessForDisabled'].value,
      lon: this.recommendForm.controls['lon'].value

    }

    this.restaurantService.recommendRestaurant(preferences).subscribe((res)=>{
      Swal.fire({
        position: 'center',
        title: 'WOHOOOOOOOOOOOOOOOOOOO',
        icon: 'success',
        showConfirmButton: false,
        timer: 1500
      })
    },error => {
      Swal.fire({
        title: 'Error!',
        text: 'Old password is wrong.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'OK'
      })
    })
  }
}
