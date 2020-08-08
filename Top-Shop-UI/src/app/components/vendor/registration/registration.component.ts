// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-registration',
//   templateUrl: './registration.component.html',
//   styleUrls: ['./registration.component.sass']
// })
// export class RegistrationComponent implements OnInit {

//   constructor() { }

//   ngOnInit(): void {
//   }

// }

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {  Vendor } from '../../../model/vendor/registration';
import { UserService } from '../../../services/vendor/registration.service';
 
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class UserFormComponent {
 
  user: Vendor;
 
  constructor(private route: ActivatedRoute, 
              private router: Router, 
              private userService: UserService) {
    
                this.user = new User();
  }
 
  onSubmit() {
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }
 
  gotoUserList() {
    this.router.navigate(['/users']);
  }
}
