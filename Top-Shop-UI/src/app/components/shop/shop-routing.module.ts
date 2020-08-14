import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { ProductDetailsComponent } from "./products/product-details/product-details.component";
import { ProductLeftSidebarComponent } from "./products/product-left-sidebar/product-left-sidebar.component";
import { HomeThreeComponent } from "./home-three/home-three.component";
import { AddproductComponent } from "./products/product/AddProduct.component";

// Routes
const routes: Routes = [
  { path: "", component: HomeThreeComponent },
  { path: "main", component: HomeThreeComponent },
  { path: "products/:category", component: ProductLeftSidebarComponent },
  { path: "product/:id", component: ProductDetailsComponent },
  { path: "product/addNewProduct", component: AddproductComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ShopRoutingModule {}
