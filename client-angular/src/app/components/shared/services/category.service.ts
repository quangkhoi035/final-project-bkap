import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from 'src/app/modals/category';

@Injectable({
  providedIn: 'root'
})

export class CategoryService {
  private baseUrl = 'http://localhost:8075/api/v1/categories';

  constructor(private http: HttpClient) { }

  // getUser(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/${id}`);
  // }
  //
  // // tslint:disable-next-line:ban-types
  // createUser(user: Object): Observable<Object> {
  //   return this.http.post(`${this.baseUrl}`, user);
  // }
  //
  // // tslint:disable-next-line:ban-types
  // updateUser(id: number, value: any): Observable<Object> {
  //   return this.http.put(`${this.baseUrl}/${id}`, value);
  // }
  //
  // deleteUser(id: number): Observable<any> {
  //   return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  // }

  getCategoriesList(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}`);
  }

  // getMenusListByUserName(username: string): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/menu/${username}`);
  // }

}
