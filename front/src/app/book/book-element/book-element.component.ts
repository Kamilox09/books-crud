import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Book} from "../../models/book.model";

@Component({
  selector: '[app-book-element]',
  templateUrl: './book-element.component.html',
  styleUrls: ['./book-element.component.css']
})
export class BookElementComponent implements OnInit {

  @Input()
  book: Book;
  @Output()
  editBook = new EventEmitter<Book>();

  constructor() { }

  ngOnInit() {
  }

  onEditBook(book: Book): void {
    this.editBook.emit(book);
  }

}
