import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../_services/auth.service';
import { TokenStorageService } from '../../../_services/token-storage.service';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import {Order} from '../../../modals/order.model';
import {User} from '../../../modals/user';
import {CartService} from '../../shared/services/cart.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.sass']
})
export class MyAccountComponent implements OnInit {

  form: any = {};
  form2: any = {};
  roles: string[] = [];
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  user: User = new User();
  listOrder : Order[];
  displayedColumns: string[] = ["Id", "List Item","Amount", "Address", "Phone"];

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage2 = '';

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private cartService: CartService,
              private router: Router) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
    this.user = this.tokenStorage.getUser();
    this.cartService.getOrderByUser(this.user).subscribe(orders => this.listOrder = orders);
    console.log(this.listOrder)

  }
  signin(){
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this. isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigate(['']).then(() => {
          window.location.reload();
        });
      },
      err => {
        console.log(err);
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  signup(){
    this.authService.register(this.form2).subscribe(
      data => {
        console.log(data);
        alert("Đăng ký thành công");
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        window.location.reload();
      },
      err => {
        this.errorMessage2 = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
