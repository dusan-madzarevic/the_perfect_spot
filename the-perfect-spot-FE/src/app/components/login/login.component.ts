import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginModel} from '../../model/login.model';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    "email": new FormControl('', [Validators.required, Validators.email]),
    "password": new FormControl('', Validators.required)
  });



  constructor(
    private service: AuthService,
    private route: Router
  ) {}

  loginUser(): void {
    let loginDto = {
      "email": this.loginForm.value.email,
      "password": this.loginForm.value.password
    };

    this.service.login(loginDto)
      .subscribe(response => {
        localStorage.setItem("accessToken", response.authenticationToken);
        this.route.navigate(['/']);
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'There is no user with these credentials!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        })
      })
  }

  ngOnInit(): void {
  }
}
