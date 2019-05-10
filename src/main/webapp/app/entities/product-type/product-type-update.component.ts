import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProductType, ProductType } from 'app/shared/model/product-type.model';
import { ProductTypeService } from './product-type.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-product-type-update',
  templateUrl: './product-type-update.component.html'
})
export class ProductTypeUpdateComponent implements OnInit {
  productType: IProductType;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    productType: [null, [Validators.required]],
    productTypeDescription: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productTypeService: ProductTypeService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productType }) => {
      this.updateForm(productType);
      this.productType = productType;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productType: IProductType) {
    this.editForm.patchValue({
      id: productType.id,
      productType: productType.productType,
      productTypeDescription: productType.productTypeDescription,
      shopId: productType.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productType = this.createFromForm();
    if (productType.id !== undefined) {
      this.subscribeToSaveResponse(this.productTypeService.update(productType));
    } else {
      this.subscribeToSaveResponse(this.productTypeService.create(productType));
    }
  }

  private createFromForm(): IProductType {
    const entity = {
      ...new ProductType(),
      id: this.editForm.get(['id']).value,
      productType: this.editForm.get(['productType']).value,
      productTypeDescription: this.editForm.get(['productTypeDescription']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductType>>) {
    result.subscribe((res: HttpResponse<IProductType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackShopById(index: number, item: IShop) {
    return item.id;
  }
}
