export interface Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: ProductInvotoryStatus;
  rating: number;
  createdAt: number;
  updatedAt: number;
}

export enum ProductInvotoryStatus {
  INSTOCK ='INSTOCK',
  LOWSTOCK = 'LOWSTOCK',
  OUTOFSTOCK = 'OUTOFSTOCK',
}
