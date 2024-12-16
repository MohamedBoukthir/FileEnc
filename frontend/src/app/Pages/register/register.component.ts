import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

  registerForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService : AuthenticationService
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: [''],
      password: ['']
    })
  }

  register() {
    if (this.registerForm.valid) {
      const registerReq = this.registerForm.value;
      this.authService.register(registerReq).subscribe({
        next: (res) => {
          console.log('Register successful:', res.message)
          this.router.navigate(['/'])
        },
        error: (err) => {
          console.log('Register failed', err)
        }
      });
    }

  }

  get email() {
    return this.registerForm.get('email')
  }

  get password() {
    return this.registerForm.get('password')
  }

}
