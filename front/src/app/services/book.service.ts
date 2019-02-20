import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Book} from "../models/book.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  public getBooks(size: number, page: number): Observable<Array<Book>>{
    let params = new HttpParams();
    params = params.set('size', size+'');
    params = params.set('page', page+'');
    return this.http.get<Array<Book>>('http://localhost:8081/books', {params: params});

  }
}
