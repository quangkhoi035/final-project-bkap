import {Component, OnInit} from '@angular/core';
import {CartService} from '../../shared/services/cart.service';
import {Observable, of} from 'rxjs';
import {CartItem} from 'src/app/modals/cart-item';
import {ProductService} from '../../shared/services/product.service';
import {Order} from '../../../modals/order.model';
import {Address} from '../../../modals/address';
import { Router } from '@angular/router';
import {User} from '../../../modals/user';
import {TokenStorageService} from '../../../_services/token-storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.sass']
})
export class CheckoutComponent implements OnInit {

  public cartItems: Observable<CartItem[]> = of([]);
  public buyProducts: CartItem[];
  order: Order = new Order();
  user: User = new User();
  address2: Address = new Address();
  amount: number;
  payments: string[] = ['Create an Account?', 'Flat Rate'];
  paymantWay: string[] = ['Direct Bank Transfer', 'PayPal'];

  constructor(private cartService: CartService,
              public productService: ProductService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.cartItems = this.cartService.getItems();
    this.cartItems.subscribe(products => this.buyProducts = products);
    this.getTotal().subscribe(amount => this.amount = amount);
    this.user = this.tokenStorageService.getUser();

  }

  public getTotal(): Observable<number> {
    return this.cartService.getTotalAmount();
  }
  onSubmit() {
    this.order.listCartItem = this.buyProducts;

    this.order.amount = this.amount;
    this.order.address = this.address2;
    this.order.user = this.user;
    this.cartService.createOrder(this.order).subscribe(data => {
        console.log(data);
        this.cartService.removeAllCartItem();
        this.router.navigate(['']).then(() => {
          window.location.reload();
        });
      },
      error => console.log('Loi them order')
    );
  }
}
