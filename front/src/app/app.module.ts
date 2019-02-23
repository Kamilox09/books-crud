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
import { AddBookComponent } from './book/add-book/add-book.component';
import {CategoryService} from "./services/category.service";

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    BookElementComponent,
    PaginationComponent,
    AddBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    BookService,
    CategoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
