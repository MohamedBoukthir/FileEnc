import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService : AuthenticationService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: [''],
      password: ['']
    })
  }

  login() {
    if (this.loginForm.valid) {
      const loginReq = this.loginForm.value;
      console.log('login req : ', loginReq)

      this.authService.login(loginReq).subscribe({
        next: (res) => {
          console.log('Login successful:', res.message)
          localStorage.setItem('user', JSON.stringify(res))
          this.router.navigate(['/fileEnc'])
        },
        error: (err) => {
          console.log('Login failed', err)
        }
      });
    }

  }

  get email() {
    return this.loginForm.get('email')
  }

  get password() {
    return this.loginForm.get('password')
  }

}
