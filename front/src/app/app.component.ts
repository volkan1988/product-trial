import {
  Component, inject,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import {BadgeModule} from "primeng/badge";
import {OverlayPanelModule} from "primeng/overlaypanel";
import {CurrencyPipe} from "@angular/common";
import {DividerModule} from "primeng/divider";
import {ButtonDirective} from "primeng/button";
import {CartService} from "./shared/services/cart.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, BadgeModule, OverlayPanelModule, CurrencyPipe, DividerModule, ButtonDirective],
})
export class AppComponent {
  title = "ALTEN SHOP";

  protected readonly cartService = inject(CartService);

  public readonly cart = this.cartService.products;
  public readonly cartCount = this.cartService.count;
  public readonly cartTotal = this.cartService.total;
}
