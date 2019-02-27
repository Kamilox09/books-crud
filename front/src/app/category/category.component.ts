import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../services/category.service";
import {Category} from "../models/category.model";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[];
  page: number = 1;
  size: number = 5;
  loading = false;
  totalElements: number;
  adding: boolean = false;
  categoryToEdit: Category;
  editing: boolean = false;

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    this.loading = true;
    this.categoryService.getCategories(this.page, this.size).subscribe(data => {
      this.categories = data['content'];
      this.totalElements = data['totalElements'];
      this.loading = false;
    });
  }

  goToPage(n: number): void {
    this.page = n;
    this.getCategories();
  }

  onNext(): void {
    this.page++;
    this.getCategories();
  }

  onPrev(): void {
    this.page--;
    this.getCategories();
  }

  onChangeSize(n: number): void {
    this.size = n;
    this.getCategories();
  }

  onAdded(): void {
    this.adding = false;
    this.getCategories();
  }

  onAdd(): void {
    this.adding = true;
  }

  setCategoryToEdit(category: Category){
    this.categoryToEdit = category;
    this.editing = true;
  }

  onEdited(): void {
    this.editing = false;
    this.categoryToEdit = null;
  }

  onDeleted(): void {
    this.getCategories();
  }

}
