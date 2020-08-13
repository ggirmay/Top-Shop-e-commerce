import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { MainComponent } from "./components/main/main.component";
import { HomeComponent } from "./components/shop/home/home.component";
import { DemoComponent } from "./components/demo/demo.component";
import { HomeThreeComponent } from "./components/shop/home-three/home-three.component";
import { ConfirmationPageComponent } from "./components/pages/checkout/confirmation-page/confirmation-page.component";
import { VendorComponent } from './components/pages/vendor/vendor.component';


const appRoutes: Routes = [
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full",
  },
  {
    path: "home",
    component: HomeThreeComponent,
  },
  {
    path: "",
    component: MainComponent,
    children: [
      {
        path: "home",
        loadChildren: () =>
          import("./components/shop/shop.module").then((m) => m.ShopModule),
      },
      {
        path: "pages",
        loadChildren: () =>
          import("./components/pages/pages.module").then((m) => m.PagesModule),
      },
      {
        path: "blog",
        loadChildren: () =>
          import("./components/blog/blog.module").then((m) => m.BlogModule),
      },
    ],
  },
  {
    path: "**",
    redirectTo: "home/one",
  },
  {path: 'confirmation-page' , component: ConfirmationPageComponent }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
