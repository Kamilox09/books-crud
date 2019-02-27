import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Category} from "../models/category.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url: string = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  public getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url + '/category');
  }

  public getCategories(page: number, size: number): Observable<Category[]> {
    let params = new HttpParams();
    params = params.set("page", page + '');
    params = params.set("size", size + '');
    return this.http.get<Category[]>(this.url + '/category', {params: params});
  }

  public addCategory(category: Category) {
    return this.http.post(this.url + '/category', category);
  }

  public editCategory(category: Category) {
    return this.http.put(this.url + '/category/' + category.id, category);
  }

  public deleteCategory(id: number) {
    return this.http.delete(this.url + '/category/' + id)
  }
}
