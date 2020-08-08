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
import { RegistrationComponent } from './components/vendor/registration/registration.component';
import { ManagementComponent } from './components/vendor/management/management.component';
import { ValidationComponent } from './components/vendor/validation/validation.component';


@NgModule({
  declarations: [
    AppComponent,
    DemoComponent,
    MainComponent,
    RegistrationComponent,
    ManagementComponent,
    ValidationComponent



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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
