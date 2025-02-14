import { NgModule } from '@angular/core';
import { Routes, RouterModule  } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserInfoComponent } from './user-info/user-info.component';
import { demoInterceptor } from './_helpers/demo.interceptor';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'app-user-info', component: UserInfoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
 // ,
 // providers: [demoInterceptor]
})
export class AppRoutingModule { }

