import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-users',
  standalone: true,
  imports: [CommonModule],
  template: `
  <h2>Admin - User Management</h2>
  <table>
    <thead><tr><th>Email</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>
    <tbody>
      <tr *ngFor="let user of users">
        <td>{{user.email}}</td><td>{{user.role}}</td><td>{{user.status}}</td>
        <td>
          <button>Enable</button>
          <button>Disable</button>
          <button>Ban</button>
          <button>Delete</button>
        </td>
      </tr>
    </tbody>
  </table>
  `
})
export class AdminUsersComponent {
  users = [{ email: 'candidate@demo.com', role: 'CANDIDATE', status: 'ACTIVE' }];
}
