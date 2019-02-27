import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Book} from "../models/book.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private url: string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  public getBooks(size: number, page: number): Observable<Array<Book>>{
    let params = new HttpParams();
    params = params.set('size', size+'');
    params = params.set('page', page+'');
    return this.http.get<Array<Book>>(this.url +'/books', {params: params});

  }

  public addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.url +'/books', book);
  }

  public editBook(book: Book) {
    return this.http.put(this.url + '/books/'+book.id, book);
  }

  public deleteBook(id: number) {
    return this.http.delete(this.url+'/books/' + id);
  }
}
