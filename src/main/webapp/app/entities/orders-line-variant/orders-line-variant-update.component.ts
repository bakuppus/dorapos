import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IOrdersLineVariant, OrdersLineVariant } from 'app/shared/model/orders-line-variant.model';
import { OrdersLineVariantService } from './orders-line-variant.service';
import { IOrdersLine } from 'app/shared/model/orders-line.model';
import { OrdersLineService } from 'app/entities/orders-line';

@Component({
  selector: 'jhi-orders-line-variant-update',
  templateUrl: './orders-line-variant-update.component.html'
})
export class OrdersLineVariantUpdateComponent implements OnInit {
  ordersLineVariant: IOrdersLineVariant;
  isSaving: boolean;

  orderslines: IOrdersLine[];

  editForm = this.fb.group({
    id: [],
    variantName: [],
    variantValue: [],
    description: [],
    percentage: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    thumbnailPhotoUrl: [],
    price: [null, [Validators.required]],
    ordersLineId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected ordersLineVariantService: OrdersLineVariantService,
    protected ordersLineService: OrdersLineService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ordersLineVariant }) => {
      this.updateForm(ordersLineVariant);
      this.ordersLineVariant = ordersLineVariant;
    });
    this.ordersLineService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrdersLine[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrdersLine[]>) => response.body)
      )
      .subscribe((res: IOrdersLine[]) => (this.orderslines = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ordersLineVariant: IOrdersLineVariant) {
    this.editForm.patchValue({
      id: ordersLineVariant.id,
      variantName: ordersLineVariant.variantName,
      variantValue: ordersLineVariant.variantValue,
      description: ordersLineVariant.description,
      percentage: ordersLineVariant.percentage,
      fullPhoto: ordersLineVariant.fullPhoto,
      fullPhotoContentType: ordersLineVariant.fullPhotoContentType,
      fullPhotoUrl: ordersLineVariant.fullPhotoUrl,
      thumbnailPhoto: ordersLineVariant.thumbnailPhoto,
      thumbnailPhotoContentType: ordersLineVariant.thumbnailPhotoContentType,
      thumbnailPhotoUrl: ordersLineVariant.thumbnailPhotoUrl,
      price: ordersLineVariant.price,
      ordersLineId: ordersLineVariant.ordersLineId
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
    const ordersLineVariant = this.createFromForm();
    if (ordersLineVariant.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersLineVariantService.update(ordersLineVariant));
    } else {
      this.subscribeToSaveResponse(this.ordersLineVariantService.create(ordersLineVariant));
    }
  }

  private createFromForm(): IOrdersLineVariant {
    const entity = {
      ...new OrdersLineVariant(),
      id: this.editForm.get(['id']).value,
      variantName: this.editForm.get(['variantName']).value,
      variantValue: this.editForm.get(['variantValue']).value,
      description: this.editForm.get(['description']).value,
      percentage: this.editForm.get(['percentage']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      price: this.editForm.get(['price']).value,
      ordersLineId: this.editForm.get(['ordersLineId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdersLineVariant>>) {
    result.subscribe((res: HttpResponse<IOrdersLineVariant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackOrdersLineById(index: number, item: IOrdersLine) {
    return item.id;
  }
}
