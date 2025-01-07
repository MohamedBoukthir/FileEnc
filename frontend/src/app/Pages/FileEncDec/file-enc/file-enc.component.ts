import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-file-enc',
  templateUrl: './file-enc.component.html',
  styleUrls: ['./file-enc.component.css']
})
export class FileEncComponent {
  selectedFile: File | null = null;
  key: string = '';
  selectedAlgorithm: string = 'AES';
  downloadUrl: string | null = null;

  constructor(private http: HttpClient) {}

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onEncryptSubmit(): void {
    if (!this.selectedFile || !this.key || !this.selectedAlgorithm) {
      alert('All fields are required!');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('key', this.key);
    formData.append('algo', this.selectedAlgorithm);
    formData.append('operation', 'encrypt');

    this.http.post('http://localhost:8080/cryptography/process', formData, {
      responseType: 'blob'
    }).subscribe({
      next: (response: Blob) => {
        const file = new Blob([response], { type: 'application/octet-stream' });
        this.downloadUrl = URL.createObjectURL(file);
      },
      error: (error) => {
        console.error('Error:', error);
        alert('Error processing the file');
      }
    });
  }
}
