import { Component, OnInit } from '@angular/core';
import { CitiesService } from './services/cities.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private citiesSvc:CitiesService) {
    console.log("AppComponent Init")
    this.citiesSvc.initCitiesToIndexDB()
  }
}
