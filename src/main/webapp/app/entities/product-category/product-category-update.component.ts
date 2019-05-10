import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProductCategory, ProductCategory } from 'app/shared/model/product-category.model';
import { ProductCategoryService } from './product-category.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-product-category-update',
  templateUrl: './product-category-update.component.html'
})
export class ProductCategoryUpdateComponent implements OnInit {
  productCategory: IProductCategory;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    category: [],
    description: [],
    imageFull: [],
    imageFullContentType: [],
    imageFullUrl: [],
    imageThumb: [],
    imageThumbContentType: [],
    imageThumbUrl: [],
    shopId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected productCategoryService: ProductCategoryService,
    protected shopService: ShopService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productCategory }) => {
      this.updateForm(productCategory);
      this.productCategory = productCategory;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productCategory: IProductCategory) {
    this.editForm.patchValue({
      id: productCategory.id,
      category: productCategory.category,
      description: productCategory.description,
      imageFull: productCategory.imageFull,
      imageFullContentType: productCategory.imageFullContentType,
      imageFullUrl: productCategory.imageFullUrl,
      imageThumb: productCategory.imageThumb,
      imageThumbContentType: productCategory.imageThumbContentType,
      imageThumbUrl: productCategory.imageThumbUrl,
      shopId: productCategory.shopId
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
    const productCategory = this.createFromForm();
    if (productCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.productCategoryService.update(productCategory));
    } else {
      this.subscribeToSaveResponse(this.productCategoryService.create(productCategory));
    }
  }

  private createFromForm(): IProductCategory {
    const entity = {
      ...new ProductCategory(),
      id: this.editForm.get(['id']).value,
      category: this.editForm.get(['category']).value,
      description: this.editForm.get(['description']).value,
      imageFullContentType: this.editForm.get(['imageFullContentType']).value,
      imageFull: this.editForm.get(['imageFull']).value,
      imageFullUrl: this.editForm.get(['imageFullUrl']).value,
      imageThumbContentType: this.editForm.get(['imageThumbContentType']).value,
      imageThumb: this.editForm.get(['imageThumb']).value,
      imageThumbUrl: this.editForm.get(['imageThumbUrl']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductCategory>>) {
    result.subscribe((res: HttpResponse<IProductCategory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
