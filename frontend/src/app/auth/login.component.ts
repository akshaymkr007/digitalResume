import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
  <section class="login-card">
    <h2>Digital Resume Portal</h2>
    <form [formGroup]="loginForm" (ngSubmit)="login()">
      <label>Email</label>
      <input formControlName="email" type="email" placeholder="name@example.com">
      <label>Password</label>
      <input formControlName="password" type="password" placeholder="********">
      <button type="submit">Login</button>
    </form>
    <button class="google" (click)="googleLogin()">Continue with Google</button>
  </section>
  `,
  styles: [`.login-card{max-width:420px;margin:2rem auto;padding:1rem;border:1px solid #ddd;border-radius:8px;display:grid;gap:.75rem}.google{background:#fff;border:1px solid #555}`]
})
export class LoginComponent {
  readonly loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  constructor(private readonly fb: FormBuilder, private readonly router: Router) {}

  login(): void {
    if (this.loginForm.valid) {
      this.router.navigate(['/home']);
    }
  }

  googleLogin(): void {
    window.location.href = '/oauth2/authorization/google';
  }
}
