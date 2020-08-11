import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import {UserService} from "../../../services/UserService";



@Component({ templateUrl: 'registration.component.html' })
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
  ) {
    // redirect to home if already logged in

  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userAccount: this.formBuilder.group({
          firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
        billing_address: this.formBuilder.group({ // make a nested group
          city: '',
          state: ['', [Validators.required]],
          addressLineOne: ['', [Validators.required]],
          addressLineTwo: [Validators.required],
        }),
        shipping_address: this.formBuilder.group({ // make a nested group
          city: '',
          state: ['', [Validators.required]],
          addressLineOne: ['', [Validators.required]],
          addressLineTwo: [Validators.required],
        })
      })

    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.userService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(" I am in user service");
          this.router.navigate(['/login']);
        },
        error => {
          console.log("I am error");
          this.loading = false;
        });
  }
}
