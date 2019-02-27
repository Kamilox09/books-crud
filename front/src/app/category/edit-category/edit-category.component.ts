import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Category} from "../../models/category.model";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

  @Input()
  category: Category;
  error: boolean = false;
  @Output()
  edited = new EventEmitter<boolean>();


  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
  }

  editCategory(){
    this.categoryService.editCategory(this.category).subscribe(() => {
      this.error=false;
      this.edited.emit(true);
    }, () => {
      this.error=true;
    });
  }

}
