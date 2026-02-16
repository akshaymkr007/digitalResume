import { Component } from '@angular/core';
import { ResumeUploadComponent } from '../resume/resume-upload.component';
import { DigitalResumeComponent } from '../resume/digital-resume.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ResumeUploadComponent, DigitalResumeComponent],
  template: `
  <app-resume-upload (resumeUploaded)="uploaded = true" />
  <app-digital-resume *ngIf="uploaded" />
  `
})
export class HomeComponent {
  uploaded = false;
}
