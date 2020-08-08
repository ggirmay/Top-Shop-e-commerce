import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemoComponent } from './components/demo/demo.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { NgxImgZoomModule } from 'ngx-img-zoom';


import { MainComponent } from './components/main/main.component';



import { AppRoutingModule } from './app-routing.module';
import { ShopModule } from './components/shop/shop.module';
import { SharedModule } from './components/shared/shared.module';
<<<<<<< HEAD
import { RegistrationComponent } from './components/vendor/registration/registration.component';
import { ManagementComponent } from './components/vendor/management/management.component';
import { ValidationComponent } from './components/vendor/validation/validation.component';
=======
import {AppService} from "./services/AppService";
>>>>>>> d4c4d7b43ec3c09428f4f8d97ebd40a52bb219ae


@NgModule({
  declarations: [
    AppComponent,
    DemoComponent,
<<<<<<< HEAD
    MainComponent,
    RegistrationComponent,
    ManagementComponent,
    ValidationComponent



=======
    MainComponent
>>>>>>> d4c4d7b43ec3c09428f4f8d97ebd40a52bb219ae
  ],
  imports: [
    NgxSpinnerModule,
    BrowserModule,
    SharedModule,
    ShopModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgxImgZoomModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
