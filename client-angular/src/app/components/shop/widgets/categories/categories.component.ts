import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../../shared/services/category.service';
import {Category} from '../../../../modals/category';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.sass']
})
export class CategoriesComponent implements OnInit {
  categories : Observable<Category[]>;
  constructor( public categoryService: CategoryService) { }

  ngOnInit() {
    this.categories = this.categoryService.getCategoriesList();
  }

}
