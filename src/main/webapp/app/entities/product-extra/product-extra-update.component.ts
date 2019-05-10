import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProductExtra, ProductExtra } from 'app/shared/model/product-extra.model';
import { ProductExtraService } from './product-extra.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
  selector: 'jhi-product-extra-update',
  templateUrl: './product-extra-update.component.html'
})
export class ProductExtraUpdateComponent implements OnInit {
  productExtra: IProductExtra;
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    extraName: [],
    description: [],
    extraValue: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    thumbnailPhotoUrl: [],
    productId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected productExtraService: ProductExtraService,
    protected productService: ProductService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productExtra }) => {
      this.updateForm(productExtra);
      this.productExtra = productExtra;
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productExtra: IProductExtra) {
    this.editForm.patchValue({
      id: productExtra.id,
      extraName: productExtra.extraName,
      description: productExtra.description,
      extraValue: productExtra.extraValue,
      fullPhoto: productExtra.fullPhoto,
      fullPhotoContentType: productExtra.fullPhotoContentType,
      fullPhotoUrl: productExtra.fullPhotoUrl,
      thumbnailPhoto: productExtra.thumbnailPhoto,
      thumbnailPhotoContentType: productExtra.thumbnailPhotoContentType,
      thumbnailPhotoUrl: productExtra.thumbnailPhotoUrl,
      productId: productExtra.productId
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
    const productExtra = this.createFromForm();
    if (productExtra.id !== undefined) {
      this.subscribeToSaveResponse(this.productExtraService.update(productExtra));
    } else {
      this.subscribeToSaveResponse(this.productExtraService.create(productExtra));
    }
  }

  private createFromForm(): IProductExtra {
    const entity = {
      ...new ProductExtra(),
      id: this.editForm.get(['id']).value,
      extraName: this.editForm.get(['extraName']).value,
      description: this.editForm.get(['description']).value,
      extraValue: this.editForm.get(['extraValue']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      productId: this.editForm.get(['productId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductExtra>>) {
    result.subscribe((res: HttpResponse<IProductExtra>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
