import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {BookService} from "../../services/book.service";
import {Category} from "../../models/category.model";
import {Book} from "../../models/book.model";

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  categories: Category[];
  newBook: Book;
  error: boolean = false;
  @Output()
  added = new EventEmitter<boolean>();

  constructor(private categoryService: CategoryService,
              private bookService: BookService) {
    this.newBook = new Book();
  }

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categories = data['content'];
    });
  }

  addBook() {
    this.bookService.addBook(this.newBook).subscribe(x => {
      this.added.emit(true);
      this.error = false;
    },
      error =>{
      this.error=true;
      });
  }

}
