import {Component, Input, OnInit} from '@angular/core';
import { ProductService } from '../../shared/services/product.service';
import { Product } from 'src/app/modals/product.model';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {
  products: Product[];
  public banners = [];

  cin ;

  public slides = [
    { title: 'Huge sale', subtitle: 'Up to 70%', image: 'assets/images/carousel/banner1.jpg' },
    { title: 'Biggest discount', subtitle: 'Check the promotion', image: 'assets/images/carousel/banner2.jpg' },
    { title: 'Biggest sale', subtitle: 'Dont miss it', image: 'assets/images/carousel/banner3.jpg' },
    { title: 'Our best products', subtitle: 'Special selection', image: 'assets/images/carousel/banner4.jpg' },
    { title: 'Massive sale', subtitle: 'Only for today', image: 'assets/images/carousel/banner5.jpg' }
  ];

  constructor(private productService: ProductService, private httpClient:HttpClient, private router :Router) { }

  ngOnInit() {
    this.productService.getBanners()
    .subscribe(
      data => this.banners = data
    );

 this.productService.getProducts()
 .subscribe(
   (product: Product[]) => {
     this.products = product
   }
 )

  }
// =============================================================

  @Input()
  product: Product;
  private selectedFile;
  imgURL: any;


  public onFileChanged(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };

  }

  saveProduct() {

    const uploadData = new FormData();
    uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.selectedFile.imageName = this.selectedFile.name;

    this.httpClient.post('http://localhost:8083/product/upload', uploadData, { observe: 'response' })
      .subscribe((response) => {
          if (response.status === 200) {
            this.productService.addProduct(this.product).subscribe(
              (book) => {
                console.log('Image uploaded successfully');
              }
            );
            console.log('Image uploaded successfully');
          } else {
            console.log('Image not uploaded successfully');
          }
        }
      );
  }
  filesToUpload: File;





  upload() {
    const formData: any = new FormData();

   const files: File = this.filesToUpload;
    console.log(files);


    let headers = new HttpHeaders({
      'Content-Type': undefined});
    let options = { headers: headers };

    formData.append("image",this.selectedFile,this.selectedFile.name);
     formData.append("product",{})
    this.httpClient.post('http://localhost:8083/product/uploadImage', formData,options).subscribe(
     data=>{
       console.log("Mulie"+data);
       },
     error => {
       console.log(error);
     }
    )
    console.log('form data variable :   '+ formData);


  }

  fileChangeEvent(fileInput: any) {
    console.log(this.cin)
    this.filesToUpload = fileInput
    //this.product.photo = fileInput.target.files[0]['name'];
  }
}
