import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProduct, Product } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { IProductType } from 'app/shared/model/product-type.model';
import { ProductTypeService } from 'app/entities/product-type';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';
import { IDiscount } from 'app/shared/model/discount.model';
import { DiscountService } from 'app/entities/discount';
import { ITax } from 'app/shared/model/tax.model';
import { TaxService } from 'app/entities/tax';
import { IProductCategory } from 'app/shared/model/product-category.model';
import { ProductCategoryService } from 'app/entities/product-category';

@Component({
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
  product: IProduct;
  isSaving: boolean;

  producttypes: IProductType[];

  shops: IShop[];

  discounts: IDiscount[];

  taxes: ITax[];

  productcategories: IProductCategory[];

  editForm = this.fb.group({
    id: [],
    productName: [null, [Validators.required, Validators.maxLength(30)]],
    productDescription: [null, [Validators.maxLength(99)]],
    price: [],
    quantity: [null, [Validators.required]],
    productImageFull: [],
    productImageFullContentType: [],
    productImageFullUrl: [],
    productImageThumb: [],
    productImageThumbContentType: [],
    productImageThumbUrl: [],
    dateCreated: [],
    barcode: [],
    serialCode: [],
    priorityPosition: [],
    active: [],
    isVariantProduct: [],
    productTypesId: [],
    shopId: [],
    discountsId: [],
    taxesId: [],
    categoryId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected productService: ProductService,
    protected productTypeService: ProductTypeService,
    protected shopService: ShopService,
    protected discountService: DiscountService,
    protected taxService: TaxService,
    protected productCategoryService: ProductCategoryService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ product }) => {
      this.updateForm(product);
      this.product = product;
    });
    this.productTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProductType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProductType[]>) => response.body)
      )
      .subscribe((res: IProductType[]) => (this.producttypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.discountService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDiscount[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDiscount[]>) => response.body)
      )
      .subscribe((res: IDiscount[]) => (this.discounts = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.taxService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITax[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITax[]>) => response.body)
      )
      .subscribe((res: ITax[]) => (this.taxes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.productCategoryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProductCategory[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProductCategory[]>) => response.body)
      )
      .subscribe((res: IProductCategory[]) => (this.productcategories = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(product: IProduct) {
    this.editForm.patchValue({
      id: product.id,
      productName: product.productName,
      productDescription: product.productDescription,
      price: product.price,
      quantity: product.quantity,
      productImageFull: product.productImageFull,
      productImageFullContentType: product.productImageFullContentType,
      productImageFullUrl: product.productImageFullUrl,
      productImageThumb: product.productImageThumb,
      productImageThumbContentType: product.productImageThumbContentType,
      productImageThumbUrl: product.productImageThumbUrl,
      dateCreated: product.dateCreated != null ? product.dateCreated.format(DATE_TIME_FORMAT) : null,
      barcode: product.barcode,
      serialCode: product.serialCode,
      priorityPosition: product.priorityPosition,
      active: product.active,
      isVariantProduct: product.isVariantProduct,
      productTypesId: product.productTypesId,
      shopId: product.shopId,
      discountsId: product.discountsId,
      taxesId: product.taxesId,
      categoryId: product.categoryId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const product = this.createFromForm();
    if (product.id !== undefined) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  private createFromForm(): IProduct {
    const entity = {
      ...new Product(),
      id: this.editForm.get(['id']).value,
      productName: this.editForm.get(['productName']).value,
      productDescription: this.editForm.get(['productDescription']).value,
      price: this.editForm.get(['price']).value,
      quantity: this.editForm.get(['quantity']).value,
      productImageFullContentType: this.editForm.get(['productImageFullContentType']).value,
      productImageFull: this.editForm.get(['productImageFull']).value,
      productImageFullUrl: this.editForm.get(['productImageFullUrl']).value,
      productImageThumbContentType: this.editForm.get(['productImageThumbContentType']).value,
      productImageThumb: this.editForm.get(['productImageThumb']).value,
      productImageThumbUrl: this.editForm.get(['productImageThumbUrl']).value,
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      barcode: this.editForm.get(['barcode']).value,
      serialCode: this.editForm.get(['serialCode']).value,
      priorityPosition: this.editForm.get(['priorityPosition']).value,
      active: this.editForm.get(['active']).value,
      isVariantProduct: this.editForm.get(['isVariantProduct']).value,
      productTypesId: this.editForm.get(['productTypesId']).value,
      shopId: this.editForm.get(['shopId']).value,
      discountsId: this.editForm.get(['discountsId']).value,
      taxesId: this.editForm.get(['taxesId']).value,
      categoryId: this.editForm.get(['categoryId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>) {
    result.subscribe((res: HttpResponse<IProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackProductTypeById(index: number, item: IProductType) {
    return item.id;
  }

  trackShopById(index: number, item: IShop) {
    return item.id;
  }

  trackDiscountById(index: number, item: IDiscount) {
    return item.id;
  }

  trackTaxById(index: number, item: ITax) {
    return item.id;
  }

  trackProductCategoryById(index: number, item: IProductCategory) {
    return item.id;
  }
}
