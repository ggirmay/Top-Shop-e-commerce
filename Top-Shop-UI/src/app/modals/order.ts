import { OrderDetail } from './order-detail';

export class Order {

    id? : number;
    userId? : string;
    userName? : string;
    createdDate? : Date;
    status? : string;
    amount? : number;
    orderDetails? : OrderDetail[];

    constructor(userId : string, userName : string, createdDate : Date, 
                status : string, amount : number, orderDetails : OrderDetail[]){
        this.userId = userId;
        this.userName = userName;
        this.createdDate = createdDate;
        this.status = status;
        this.amount = amount;
        this.orderDetails = orderDetails;
    }

}