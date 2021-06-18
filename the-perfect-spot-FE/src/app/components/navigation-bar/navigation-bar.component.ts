import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(private service: AuthService,
              private route: Router) { }




  ngOnInit(): void {

  }

  logOut() {
    this.service.logout();
    this.route.navigate(['/restaurants'])
  }

  isUser() {
    return this.service.isUser();
  }

  isAdmin(){
    return this.service.isAdmin();
  }

  isLoggedIn() {
    return !(this.isAdmin() || this.isUser())
  }
}
