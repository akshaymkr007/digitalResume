import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-digital-resume',
  standalone: true,
  imports: [CommonModule],
  template: `
  <nav>
    <a href="#home">Home</a>
    <a href="#about">About</a>
    <a href="#education">Education</a>
    <a href="#experience">Experience</a>
    <a href="#skills">Skill Set</a>
    <a href="#contact">Contact</a>
    <a href="#hobbies">Hobbies</a>
    <div class="avatar" (click)="toggleImageOptions = !toggleImageOptions">👤</div>
  </nav>

  <div *ngIf="toggleImageOptions" class="image-actions">
    <button>Upload New Image</button>
    <button>Remove Image</button>
  </div>

  <section id="about"><h3>Summary <button>Edit</button></h3><p>Professional summary...</p></section>

  <section id="education">
    <h3>Education <button>Add</button></h3>
    <details *ngFor="let edu of educationList">
      <summary>{{edu.title}} ({{edu.startDate}} - {{edu.endDate}}) <button>Edit</button></summary>
      <p>{{edu.description}}</p>
      <p>Percentage: {{edu.percentage}} | Branch: {{edu.branch}}</p>
    </details>
  </section>

  <section id="experience">
    <h3>Experience <button>Add</button></h3>
    <details *ngFor="let exp of experienceList">
      <summary>{{exp.company}} - {{exp.projectTitle}} ({{exp.startDate}} - {{exp.endDate}}) <button>Edit</button></summary>
      <p>{{exp.description}}</p>
    </details>
  </section>

  <section id="contact">
    <h3>Contact</h3>
    <input placeholder="Email">
    <input placeholder="Contact Number">
    <textarea placeholder="Reason for contact"></textarea>
    <button>Send</button>
  </section>
  `,
  styles: [`nav{display:flex;gap:.8rem;align-items:center}.avatar{margin-left:auto;cursor:pointer;border-radius:50%;width:40px;height:40px;background:#ddd;display:grid;place-items:center}`]
})
export class DigitalResumeComponent {
  toggleImageOptions = false;

  educationList = [
    { title: 'B.Tech', startDate: '2017', endDate: '2021', description: 'Computer Science', percentage: '84', branch: 'CSE' }
  ];

  experienceList = [
    { company: 'ABC Pvt Ltd', startDate: '2021', endDate: '2024', projectTitle: 'Resume Platform', description: 'Built and scaled candidate portal.' }
  ];
}
