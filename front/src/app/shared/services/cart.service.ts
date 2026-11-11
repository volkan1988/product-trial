
import { Injectable, computed, signal } from "@angular/core";
import {Product} from "../models/product.model";

export interface CartItem {
  product: Product;
  quantity: number;
}

@Injectable({
  providedIn: "root"
})
export class CartService {

  private readonly _products = signal<CartItem[]>([]);

  public readonly products = this._products.asReadonly();

  public readonly count = computed(() => this._products().reduce((sum, item) => sum + item.quantity, 0));

  public readonly total = computed(() =>
    this._products().reduce(
      (sum, item) => sum + item.product.price * item.quantity,
      0
    )
  );

  public add(product: Product): void {
    this._products.update(items => {
      const existing = items.some(item => item.product.id === product.id);

      if (existing) {
        return items.map(item =>
          item.product.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }

      return [...items, { product, quantity: 1 }];
    });
  }

  public remove(productId: number): void {
    this._products.update(items =>
      items.filter(item => item.product.id !== productId)
    );
  }

  public decrease(productId: number): void {
    this._products.update(items =>
      items
        .map(item =>
          item.product.id === productId
            ? { ...item, quantity: item.quantity - 1 }
            : item
        )
        .filter(item => item.quantity > 0)
    );
  }

  public clear(): void {
    this._products.set([]);
  }
}
