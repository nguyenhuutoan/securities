import { Component, OnInit } from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements  OnInit{

  ngOnInit(): void {
  }

  constructor(private authService: AuthService) {
  }

  form: any = {
    email: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  onSubmit(): void {
    const {email, password} = this.form;
    this.authService.login(email, password).subscribe({
        next: data => {
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          //this.roles =
         // this.reloadPage();

          console.log(data);
        },
        error: err => {
            this.errorMessage = err.error.message();
            this.isLoginFailed = false;
        }
    });
  }

  reloadPage(): void {
    console.log('gggggg');
    window.location.reload();
  }

}
