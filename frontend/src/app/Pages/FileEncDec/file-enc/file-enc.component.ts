import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

const BASE_URL='http://localhost:8080/cryptography'

@Component({
  selector: 'app-file-enc',
  templateUrl: './file-enc.component.html',
  styleUrl: './file-enc.component.css'
})
export class FileEncComponent {

  selectedFile: File | null = null;
  key: string = '';
  selectedAlgorithm: string = 'AES';
  downloadUrl: string | null = null;

  constructor(private http: HttpClient) {}

  // Handle file selection
  onFileChange(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onEncryptSubmit(): void {
    if (this.selectedFile && this.key) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('key', this.key);
      formData.append('algo', this.selectedAlgorithm);
      formData.append('operation', 'encrypt');

      this.http.post(BASE_URL + '/process', formData, {
        responseType: 'blob' // Expecting a file response
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
    } else {
      alert('Please fill in all fields');
    }
  }



}
