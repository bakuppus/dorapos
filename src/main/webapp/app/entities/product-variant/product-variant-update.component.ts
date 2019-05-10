import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProductVariant, ProductVariant } from 'app/shared/model/product-variant.model';
import { ProductVariantService } from './product-variant.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
  selector: 'jhi-product-variant-update',
  templateUrl: './product-variant-update.component.html'
})
export class ProductVariantUpdateComponent implements OnInit {
  productVariant: IProductVariant;
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    variantName: [],
    description: [],
    percentage: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    thumbnailPhotoUrl: [],
    price: [null, [Validators.required]],
    productId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected productVariantService: ProductVariantService,
    protected productService: ProductService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productVariant }) => {
      this.updateForm(productVariant);
      this.productVariant = productVariant;
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productVariant: IProductVariant) {
    this.editForm.patchValue({
      id: productVariant.id,
      variantName: productVariant.variantName,
      description: productVariant.description,
      percentage: productVariant.percentage,
      fullPhoto: productVariant.fullPhoto,
      fullPhotoContentType: productVariant.fullPhotoContentType,
      fullPhotoUrl: productVariant.fullPhotoUrl,
      thumbnailPhoto: productVariant.thumbnailPhoto,
      thumbnailPhotoContentType: productVariant.thumbnailPhotoContentType,
      thumbnailPhotoUrl: productVariant.thumbnailPhotoUrl,
      price: productVariant.price,
      productId: productVariant.productId
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
    const productVariant = this.createFromForm();
    if (productVariant.id !== undefined) {
      this.subscribeToSaveResponse(this.productVariantService.update(productVariant));
    } else {
      this.subscribeToSaveResponse(this.productVariantService.create(productVariant));
    }
  }

  private createFromForm(): IProductVariant {
    const entity = {
      ...new ProductVariant(),
      id: this.editForm.get(['id']).value,
      variantName: this.editForm.get(['variantName']).value,
      description: this.editForm.get(['description']).value,
      percentage: this.editForm.get(['percentage']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      price: this.editForm.get(['price']).value,
      productId: this.editForm.get(['productId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductVariant>>) {
    result.subscribe((res: HttpResponse<IProductVariant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackProductById(index: number, item: IProduct) {
    return item.id;
  }
}
