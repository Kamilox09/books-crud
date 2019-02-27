import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Category} from "../../models/category.model";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: '[app-category-element]',
  templateUrl: './category-element.component.html',
  styleUrls: ['./category-element.component.css']
})
export class CategoryElementComponent implements OnInit {
  @Input()
  category: Category;
  @Output()
  editCategory = new EventEmitter<Category>();
  @Output()
  deleted = new EventEmitter<boolean>();

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
  }


  onEditCategory(category: Category): void {
    this.editCategory.emit(category);
  }

  deleteCategory(id: number): void {
    this.categoryService.deleteCategory(id).subscribe(()=>{
      this.deleted.emit(true);
    });
  }
}
