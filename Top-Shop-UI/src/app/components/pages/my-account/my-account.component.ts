import { Component, OnInit } from '@angular/core';
import {AppService} from "../../../services/AppService";
import {NgForm} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.sass']
})
export class MyAccountComponent implements OnInit {
  public isLoggedIn = false;
  public username;
  public password;


  constructor(private _service:AppService,private _snackBar:MatSnackBar){}

  ngOnInit(){
    this.isLoggedIn = this._service.checkCredentials();
    let i = window.location.href.indexOf('code');
    if(!this.isLoggedIn && i != -1){
    }
  }

  login(element: NgForm) {
    this.username = element.value.username;
    this.password = element.value.password;
    if(element.valid)
    this._service.retrieveToken(this.username,this.password);

  }

  logout() {
    this._service.logout();
  }
}
