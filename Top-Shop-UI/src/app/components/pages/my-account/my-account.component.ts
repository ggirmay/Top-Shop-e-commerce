import { Component, OnInit } from '@angular/core';
import {AppService} from "../../../services/AppService";

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.sass']
})
export class MyAccountComponent implements OnInit {
  public isLoggedIn = false;


  constructor(
    private _service:AppService){}

  ngOnInit(){
    // this.isLoggedIn = this._service.checkCredentials();
    // let i = window.location.href.indexOf('code');
    // if(!this.isLoggedIn && i != -1){
    // }
  }

  login(element: HTMLFormElement) {
    console.log("user login",element);
    this._service.retrieveToken(null);
  }

  logout() {
    this._service.logout();
  }
}
