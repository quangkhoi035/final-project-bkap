import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RoleServices {
  private baseUrl = 'http://localhost:8090/api/roles';

  constructor(private http: HttpClient) { }

  getRole(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // tslint:disable-next-line:ban-types
  createRole(role: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, role);
  }

  // tslint:disable-next-line:ban-types
  updateRole(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteRole(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getRolesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
