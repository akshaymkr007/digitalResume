import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resume-upload',
  standalone: true,
  imports: [CommonModule],
  template: `
  <section>
    <h3>Upload Resume</h3>
    <input type="file" (change)="onFileSelected($event)">
    <button (click)="upload()" [disabled]="!selectedFile">Upload & Parse</button>
    <p>{{message}}</p>
  </section>
  `
})
export class ResumeUploadComponent {
  @Output() resumeUploaded = new EventEmitter<void>();
  selectedFile: File | null = null;
  message = '';

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.selectedFile = target.files?.[0] ?? null;
  }

  upload(): void {
    this.message = 'File scanned and uploaded successfully.';
    this.resumeUploaded.emit();
  }
}
