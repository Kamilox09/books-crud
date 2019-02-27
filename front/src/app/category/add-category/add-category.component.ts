import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Category} from "../../models/category.model";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  newCategory: Category;
  error: boolean = false;
  @Output()
  added = new EventEmitter<boolean>();

  constructor(private categoryService: CategoryService) {
    this.newCategory = new Category();
  }

  ngOnInit() {
  }

  addCategory(){
    this.categoryService.addCategory(this.newCategory).subscribe(x =>{
        this.added.emit(true);
        this.error = false;
      },
      error =>{
        this.error=true;
    });
  }

}
