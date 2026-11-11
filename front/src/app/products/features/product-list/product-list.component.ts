import {Component, OnInit, inject, signal, computed} from "@angular/core";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import {CurrencyPipe} from "@angular/common";
import {ProductsService} from "../../../shared/services/products.service";
import {CartService} from "../../../shared/services/cart.service";
import {Product, ProductInvotoryStatus} from "../../../shared/models/product.model";
import {InputTextModule} from "primeng/inputtext";
import { PaginatorModule } from 'primeng/paginator';

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: ProductInvotoryStatus.INSTOCK,
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, CurrencyPipe, InputTextModule, PaginatorModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  protected readonly cartService = inject(CartService);

  public readonly products = this.productsService.products;
  public quantities = signal<Record<number, number>>({});

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);
  public readonly search = signal('');
  public readonly first = signal(0);
  public readonly rows = signal(5);

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  protected onAdd(product: any) {
    this.cartService.add(product, this.getQuantity(product.id));
  }

  public onSearch(value: string): void {
    this.search.set(value);
    this.first.set(0);
  }

  public readonly filteredProducts = computed(() => {
    const term = this.search().trim().toLowerCase();

    return this.productsService.products().filter(product =>
      product.name.toLowerCase().includes(term) ||
      product.description.toLowerCase().includes(term) ||
      product.category.toLowerCase().includes(term) ||
      product.code.toLowerCase().includes(term)
    );
  });

  public readonly paginatedProducts = computed(() => {
    return this.filteredProducts().slice(
      this.first(),
      this.first() + this.rows()
    );
  });

  protected onPageChange(event: any) {
    this.first.set(event.first ?? 0);
    this.rows.set(event.rows ?? 5);
  }

  protected readonly ProductInvotoryStatus = ProductInvotoryStatus;


  protected getQuantity(id: number) {
    return this.quantities()[id] ?? 1;
  }

  protected setQuantity(id: number, quantity: number) {
    this.quantities.update(values => ({
      ...values,
      [id]: quantity
    }));
  }
}
