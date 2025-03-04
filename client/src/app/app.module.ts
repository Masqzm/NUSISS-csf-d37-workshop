import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UploadComponent } from './components/upload.component';
import { ViewImageComponent } from './components/view-image.component';
import { MaterialModule } from './material.module';
import { provideHttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { FileUploadService } from './services/fileupload.service';
import { CitiesService } from './services/cities.service';

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    ViewImageComponent
  ],
  imports: [
    BrowserModule, 
    MaterialModule, 
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [ FileUploadService, CitiesService, provideHttpClient() ],
  bootstrap: [AppComponent]
})
export class AppModule { }
