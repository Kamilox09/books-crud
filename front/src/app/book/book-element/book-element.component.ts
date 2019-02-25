import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Book} from "../../models/book.model";
import {BookService} from "../../services/book.service";

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
  @Output()
  deleted = new EventEmitter<boolean>();

  constructor(private bookService: BookService) { }

  ngOnInit() {
  }

  onEditBook(book: Book): void {
    this.editBook.emit(book);
  }

  deleteBook(id: number): void {
    this.bookService.deleteBook(id).subscribe(()=>{
      this.deleted.emit(true);
    });
  }

}
