import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentInformation } from '../modals/payment-information';
import { BillingInformation } from '../modals/billing-information';
import * as _ from 'lodash';
import { UserDTO } from '../modals/dto/user-dto';
import { Observable, of } from 'rxjs';
import { catchError, timeout } from 'rxjs/operators';
import { ErrorService } from './error.service';
import { Order } from '../modals/order';
import { Product } from '../modals/product.model';
import { CartItem } from '../modals/cart-item';
import { CartService } from '../components/shared/services/cart.service';
import { OrderDetail } from '../modals/order-detail';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  user : UserDTO;

  amount : number;

  constructor(private http : HttpClient , private error : ErrorService, 
              private cartservice: CartService, private router : Router) { }

  public placeOrder(paymentInformation : PaymentInformation , billingInformation : BillingInformation) {
    let order = this.prepare(paymentInformation, billingInformation);
    this.saveOrder(order).subscribe((response) => {
      console.log("RESPONSE :" , response)
      let cart: CartItem[] = JSON.parse(localStorage.getItem('checkoutItem'))
      let currentCart : CartItem[] = JSON.parse(localStorage.getItem('cartItem'))

      for(let item of cart){
        for (let i = 0 ; i < currentCart.length ; i++){
          if( currentCart[i].product.id === item.product.id ){
            currentCart.splice(i , 1);
            break;
          }
        }
      }

      localStorage.setItem('cartItem' , JSON.stringify(currentCart))
      //localStorage.setItem('orderID' , response.id )

      this.router.navigate(['confirmation-page'])

    }, (err) => {
      console.error(err)
    })

  }

  private prepare(paymentInformation : PaymentInformation , billingInformation : BillingInformation) : Order {
    
    let payment = this.preparePaymentInformation(paymentInformation);

    this.cartservice.getNewTotalAmount().subscribe((response) => this.amount = response);
    this.getUser(payment , billingInformation).subscribe((response) => this.user = response);

    let order : Order = {
      amount: this.amount,
      createdDate: new Date(),
      status: "Payed",
      userId: this.user.id,
      orderDetails: this.getOrderDetails()
    }

    return order;

    /*
    TODO: fetch user from local storage

    if exist, 
      prepare request for place order
      check new address if same as in the localstorage
        save to the database
    
    else 
      save user to database,
      prepare request for place order with the new userID

    */

  }

  private getOrderDetails() : OrderDetail[] {
    let cartItem : CartItem[] = JSON.parse(localStorage.getItem("chekoutItem"));

    let orderDetails: OrderDetail[] = []

    for( let item of cartItem ){
      orderDetails.push({
        productId: item.product.id,
        productName: item.product.name,
        quantity: item.quantity,
        unitPrice: item.product.price,
        subtotalPrice: item.product.price * item.quantity
      })
    }

    return orderDetails;
  }

  public createCheckoutItem(items : CartItem[]){
    localStorage.setItem("checkoutItem" , JSON.stringify(items));
  }

  public deleteCheckoutItem(){
    localStorage.removeItem("checkoutItem");
  }

  private getUser(paymentInformation: PaymentInformation , billingInformation: BillingInformation) : Observable<UserDTO> {
    let id = localStorage.getItem("accountUser")
    if(id){
      let activeUser = new UserDTO();
      activeUser.id = id;
      return of(activeUser);
    }else{
      let userData: UserDTO = {
        role: "USER",
        firstName: billingInformation.firstName,
        lastName: billingInformation.lastName,
        addressList:[ {
          addressLineOne: billingInformation.address,
          addressLineTwo: billingInformation.country + " " + billingInformation.postcode,
          city: billingInformation.town,
          state: billingInformation.state
        }],
        paymentInformation: [ {...paymentInformation} ],
        user_role: "GUEST_USER"
      }

      return this.saveGuestUser(userData);
    }
  }

  private saveGuestUser(user : UserDTO) : Observable<UserDTO> {
    return this.http.post<UserDTO>("http://localhost:8086/api/user/create_guest" , user)
      .pipe(
        timeout(10000),
        catchError(this.error.handleError<UserDTO>('Create Guest User' , user))
      )
  }

  private saveOrder(order : Order) : Observable<Order> {
    return this.http.post<Order>("http://localhost:8087/order/placeorder" , order)
      .pipe(
        timeout(10000),
        catchError(this.error.handleError<Order>('Save order to the database' , order))
      )
  }
  
  private preparePaymentInformation(paymentInformation : PaymentInformation) : PaymentInformation {
    let cardNumber = paymentInformation.cardNumber;
    for(let i = 0 ; i < 3 ; i++){
      cardNumber = cardNumber.replace('-' , "");
    }

    let payment : PaymentInformation = { ...paymentInformation }
    payment.cardNumber = cardNumber;

    return payment;
  }

  public checkOrder( paymentInformation : PaymentInformation , billingInformation : BillingInformation ){

    return this.checkPaymentInformation(this.preparePaymentInformation(paymentInformation)) 
            && this.checkBillingInformation(billingInformation);
  }

  private checkPaymentInformation(paymentInformation : PaymentInformation) : boolean{
    if(paymentInformation.cardNumber.length === 16 && 
      paymentInformation.expDate.length === 7 &&
      paymentInformation.nameOnCard.trim().length > 0 &&
      paymentInformation.secDigit.length === 3){

        let expDateDetails = paymentInformation.expDate.split('/');
        let currentYear : number = new Date().getUTCFullYear();
        let year = parseInt(expDateDetails[1] , 10);

        if(parseInt(expDateDetails[0] , 10) > 12 || year < currentYear || year > currentYear + 30 ) {
          alert("Please make sure the Expiration date is not wrong")
          return false;
        }
        return true;
    } else {
      alert("Please make sure the payment information is valid");
      return false;
    }
  }

  private checkBillingInformation(billingInformation : BillingInformation) : boolean {
    if(billingInformation.address.length > 0 && 
      billingInformation.country.length > 0 &&
      billingInformation.postcode.length > 0 &&
      billingInformation.state.length > 0 && 
      billingInformation.phone.length > 0 &&
      billingInformation.email.length > 0 && 
      billingInformation.lastName.length > 0){
        return true;
    }else {
      alert("Please make sure the billing information is valid");
      return false;
    }
  }

}
