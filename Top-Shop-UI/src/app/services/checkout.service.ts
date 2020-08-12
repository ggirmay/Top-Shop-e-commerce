import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentInformation } from '../modals/payment-information';
import { BillingInformation } from '../modals/billing-information';
import * as _ from 'lodash';
import { UserDTO } from '../modals/dto/user-dto';
import { Observable } from 'rxjs';
import { catchError, timeout } from 'rxjs/operators';
import { ErrorService } from './error.service';
import { Order } from '../modals/order';
import { Product } from '../modals/product.model';
import { CartItem } from '../modals/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http : HttpClient , private error : ErrorService) { }

  public placeOrder(paymentInformation : PaymentInformation , billingInformation : BillingInformation) {

  }

  /*private prepare(paymentInformation : PaymentInformation , billingInformation : BillingInformation) : Order {
    
    let payment = this.preparePaymentInformation(paymentInformation);
    let user = this.getUser();



    // let order : Order = {
    //   amount: ,
    //   createdDate: new Date(),
    //   status: "Payed",
    //   userId: user.id,
    //   orderDetails: []
    // }

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

  /*}*/

  public createCheckoutItem(items : CartItem[]){
    localStorage.setItem("checkoutItem" , JSON.stringify(items));
  }

  public deleteCheckoutItem(){
    localStorage.removeItem("checkoutItem");
  }

  private getUser() : UserDTO {
    return new UserDTO();
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
