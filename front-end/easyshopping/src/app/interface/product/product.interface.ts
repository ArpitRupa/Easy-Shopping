export interface ProductListing {
    name: string;
    description: string;
    price: number;
}

export interface ProductCreationRequest {
    productRequest: ProductListing;
    imageRequests: ProductImageRequest[];
}

export interface ProductImageRequest {
    productId: number;
    imageData: Uint8Array;
}
