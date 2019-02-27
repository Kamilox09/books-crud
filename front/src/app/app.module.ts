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
import { EditBookComponent } from './book/edit-book/edit-book.component';
import { CategoryComponent } from './category/category.component';
import { CategoryElementComponent } from './category/category-element/category-element.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    BookElementComponent,
    PaginationComponent,
    AddBookComponent,
    EditBookComponent,
    CategoryComponent,
    CategoryElementComponent,
    AddCategoryComponent,
    EditCategoryComponent
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
