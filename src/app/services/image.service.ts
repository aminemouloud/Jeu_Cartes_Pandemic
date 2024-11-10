import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";

const httpOptions = {
    headers:new HttpHeaders({
      'Content-Type':'application/json',
  
    }),
   // observe: 'response' as 'response'
  };
  
  @Injectable({
    providedIn: 'root'
  })
  export class ImageService {
    private apiServerUrl=environment.apiBaseUrl;
    private imageData: {[key: string]: Uint8Array} = {};

    constructor() { 
    }
  
    setImageData(imageData: {[key: string]: Uint8Array}): void {
      this.imageData = imageData;
    }

    getAllImageData(): {[key: string]: Uint8Array}{
      return this.imageData;
    }
  
    getImageData(key: string): Uint8Array {
        return this.imageData[key];
    }
    
      getImageUrls(keys: string[]): { key: string, url: string }[] {
        const imageUrls: { key: string, url: string }[] = [];
        keys.forEach(key => {
          const imageData = this.imageData.imageData;
          if (imageData) {
            const blob = new Blob([imageData], { type: 'image/png' });
            const url = URL.createObjectURL(blob);
            imageUrls.push({ key: key, url: url });
          }
        });
        return imageUrls;
      }
      


  }