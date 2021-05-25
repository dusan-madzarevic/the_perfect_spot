import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "../../util/must-match-validator";
import Swal from 'sweetalert2';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signupForm = this.formBuilder.group({
      firstName: ['', [Validators.required, Validators.pattern("")]],
      lastName: ['', [Validators.required, Validators.pattern("")]],
      email: ['', [Validators.required, Validators.pattern("")]],
      password: ['', [Validators.required, Validators.pattern("")]],
      passConfirm: ['', Validators.required]
    },
    {
      validator: MustMatch('password', 'passConfirm')
    });


  constructor(
    private service: AuthService,
    private formBuilder: FormBuilder,
    private route: Router
  ) { }


  signUp(): void{
    let signUpDto = {
      "id": 0,
      "firstName": this.signupForm.value.firstName,
      "lastName": this.signupForm.value.lastName,
      "email": this.signupForm.value.email,
      "password": this.signupForm.value.password
    };

    this.service.signUp(JSON.stringify(signUpDto))
      .subscribe(response => {
        this.route.navigate(['login'])
      }, error => {
        Swal.fire({
          title: 'Error!',
          text: 'User with this email already exists!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'OK'
        });
      })
  }
  ngOnInit(): void {
  }
}
