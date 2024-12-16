import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./Pages/login/login.component";
import {RegisterComponent} from "./Pages/register/register.component";
import {FileEncComponent} from "./Pages/FileEncDec/file-enc/file-enc.component";

const routes: Routes = [
  {path: '' , component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path:'fileEnc' , component: FileEncComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
