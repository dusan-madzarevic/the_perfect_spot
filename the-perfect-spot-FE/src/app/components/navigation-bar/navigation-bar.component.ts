import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(private service: AuthService) { }




  ngOnInit(): void {

  }

  logOut() {
    this.service.logout();
  }

  isUser() {
    return this.service.isUser();
  }
}
