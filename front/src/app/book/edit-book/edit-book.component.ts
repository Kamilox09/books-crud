import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {BookService} from "../../services/book.service";
import {Category} from "../../models/category.model";
import {Book} from "../../models/book.model";

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  categories: Category[];
  @Input()
  book: Book;
  error: boolean = false;
  @Output()
  edited = new EventEmitter<boolean>();

  constructor(private categoryService: CategoryService,
              private bookService: BookService) { }

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categories = data['content'];
    });
  }

  editBook(){
    this.bookService.editBook(this.book).subscribe(() => {
        this.error=false;
        this.edited.emit(true);
      }, () => {
      this.error=true;
    });
  }

}
