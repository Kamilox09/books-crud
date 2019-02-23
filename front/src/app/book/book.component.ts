import {Component, OnInit} from '@angular/core';
import {BookService} from "../services/book.service";
import {Book} from "../models/book.model";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books: Array<Book>;
  page: number = 1;
  size: number = 5;
  totalElements: number;
  loading = false;
  adding: boolean = false;

  constructor(private bookService: BookService) {
  }

  ngOnInit() {
    this.getBooks();
  }

  getBooks() {
    this.loading = true;
    this.bookService.getBooks(this.size, this.page).subscribe(x => {
      this.books = x['content'];
      this.totalElements = x['totalElements'];
      this.loading = false;
    });
  }

  goToPage(n: number): void {
    this.page = n;
    this.getBooks();
  }

  onNext(): void {
    this.page++;
    this.getBooks();
  }

  onPrev(): void {
    this.page--;
    this.getBooks();
  }

  onChangeSize(n: number): void {
    this.size = n;
    this.getBooks();
  }

  onAdded(): void {
    this.adding = false;
  }

  onAdd(): void {
    this.adding = true;
  }

}
