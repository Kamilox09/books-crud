import { Component, OnInit } from '@angular/core';
import {BookService} from "../services/book.service";
import {Book} from "../models/book.model";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books: Array<Book>;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getBooks(10,1).subscribe(x => {
      this.books = x;
    });
  }

}
