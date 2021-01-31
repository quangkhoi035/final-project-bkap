import {CartItem} from './cart-item';
import {Address} from './address';
import {User} from './user';


export class Order {
  id: number;
  listCartItem: CartItem[];
  amount: number;
  address: Address;
  user : User;
  state: string;
}
