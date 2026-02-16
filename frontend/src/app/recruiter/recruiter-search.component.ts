import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-recruiter-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
  <h2>Recruiter Candidate Search</h2>
  <div class="filters">
    <input [(ngModel)]="query" placeholder="Search by name, skill, qualification">
    <input [(ngModel)]="location" placeholder="Location">
    <input [(ngModel)]="experience" placeholder="Experience">
    <button (click)="search()">Search</button>
  </div>
  <table>
    <thead><tr><th>First Name</th><th>Last Name</th><th>Experience</th><th>Profession</th><th>Qualification</th><th>Location</th></tr></thead>
    <tbody>
      <tr *ngFor="let c of candidates"><td>{{c.fname}}</td><td>{{c.lname}}</td><td>{{c.experience}}</td><td>{{c.profession}}</td><td>{{c.qualification}}</td><td>{{c.location}}</td></tr>
    </tbody>
  </table>
  `
})
export class RecruiterSearchComponent {
  query = '';
  location = '';
  experience = '';
  candidates = [{ fname: 'John', lname: 'Doe', experience: '3', profession: 'Java Developer', qualification: 'B.Tech', location: 'Bangalore' }];

  search(): void {
    // API integration placeholder
  }
}
