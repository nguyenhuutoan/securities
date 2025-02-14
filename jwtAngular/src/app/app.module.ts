//import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app.routes';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';


//import { demoInterceptor } from './_helpers/demo.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule//,
    //HttpClientModule
  ],
  //providers: [demoInterceptor],
  bootstrap: [AppComponent]
})
export class AppModule { }
