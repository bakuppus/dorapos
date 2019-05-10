import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IOrdersLineExtra, OrdersLineExtra } from 'app/shared/model/orders-line-extra.model';
import { OrdersLineExtraService } from './orders-line-extra.service';
import { IOrdersLineVariant } from 'app/shared/model/orders-line-variant.model';
import { OrdersLineVariantService } from 'app/entities/orders-line-variant';

@Component({
  selector: 'jhi-orders-line-extra-update',
  templateUrl: './orders-line-extra-update.component.html'
})
export class OrdersLineExtraUpdateComponent implements OnInit {
  ordersLineExtra: IOrdersLineExtra;
  isSaving: boolean;

  orderslinevariants: IOrdersLineVariant[];

  editForm = this.fb.group({
    id: [],
    ordersLineExtraName: [null, [Validators.required]],
    ordersLineExtraValue: [null, [Validators.required]],
    ordersLineExtraPrice: [],
    ordersOptionDescription: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    thumbnailPhotoUrl: [],
    ordersLineVariantId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected ordersLineExtraService: OrdersLineExtraService,
    protected ordersLineVariantService: OrdersLineVariantService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ordersLineExtra }) => {
      this.updateForm(ordersLineExtra);
      this.ordersLineExtra = ordersLineExtra;
    });
    this.ordersLineVariantService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrdersLineVariant[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrdersLineVariant[]>) => response.body)
      )
      .subscribe((res: IOrdersLineVariant[]) => (this.orderslinevariants = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ordersLineExtra: IOrdersLineExtra) {
    this.editForm.patchValue({
      id: ordersLineExtra.id,
      ordersLineExtraName: ordersLineExtra.ordersLineExtraName,
      ordersLineExtraValue: ordersLineExtra.ordersLineExtraValue,
      ordersLineExtraPrice: ordersLineExtra.ordersLineExtraPrice,
      ordersOptionDescription: ordersLineExtra.ordersOptionDescription,
      fullPhoto: ordersLineExtra.fullPhoto,
      fullPhotoContentType: ordersLineExtra.fullPhotoContentType,
      fullPhotoUrl: ordersLineExtra.fullPhotoUrl,
      thumbnailPhoto: ordersLineExtra.thumbnailPhoto,
      thumbnailPhotoContentType: ordersLineExtra.thumbnailPhotoContentType,
      thumbnailPhotoUrl: ordersLineExtra.thumbnailPhotoUrl,
      ordersLineVariantId: ordersLineExtra.ordersLineVariantId
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
    const ordersLineExtra = this.createFromForm();
    if (ordersLineExtra.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersLineExtraService.update(ordersLineExtra));
    } else {
      this.subscribeToSaveResponse(this.ordersLineExtraService.create(ordersLineExtra));
    }
  }

  private createFromForm(): IOrdersLineExtra {
    const entity = {
      ...new OrdersLineExtra(),
      id: this.editForm.get(['id']).value,
      ordersLineExtraName: this.editForm.get(['ordersLineExtraName']).value,
      ordersLineExtraValue: this.editForm.get(['ordersLineExtraValue']).value,
      ordersLineExtraPrice: this.editForm.get(['ordersLineExtraPrice']).value,
      ordersOptionDescription: this.editForm.get(['ordersOptionDescription']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      ordersLineVariantId: this.editForm.get(['ordersLineVariantId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdersLineExtra>>) {
    result.subscribe((res: HttpResponse<IOrdersLineExtra>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackOrdersLineVariantById(index: number, item: IOrdersLineVariant) {
    return item.id;
  }
}
