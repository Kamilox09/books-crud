import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { BookComponent } from './book/book.component';
import {BookService} from "./services/book.service";
import { BookElementComponent } from './book/book-element/book-element.component';
import { PaginationComponent } from './shared/pagination/pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    BookElementComponent,
    PaginationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
