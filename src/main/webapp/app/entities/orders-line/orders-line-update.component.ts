import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IOrdersLine, OrdersLine } from 'app/shared/model/orders-line.model';
import { OrdersLineService } from './orders-line.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product';

@Component({
  selector: 'jhi-orders-line-update',
  templateUrl: './orders-line-update.component.html'
})
export class OrdersLineUpdateComponent implements OnInit {
  ordersLine: IOrdersLine;
  isSaving: boolean;

  orders: IOrders[];

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    ordersLineName: [null, [Validators.required]],
    ordersLineValue: [null, [Validators.required]],
    ordersLinePrice: [],
    ordersLineDescription: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    thumbnailPhotoUrl: [],
    ordersId: [],
    productId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected ordersLineService: OrdersLineService,
    protected ordersService: OrdersService,
    protected productService: ProductService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ ordersLine }) => {
      this.updateForm(ordersLine);
      this.ordersLine = ordersLine;
    });
    this.ordersService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrders[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrders[]>) => response.body)
      )
      .subscribe((res: IOrders[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(ordersLine: IOrdersLine) {
    this.editForm.patchValue({
      id: ordersLine.id,
      ordersLineName: ordersLine.ordersLineName,
      ordersLineValue: ordersLine.ordersLineValue,
      ordersLinePrice: ordersLine.ordersLinePrice,
      ordersLineDescription: ordersLine.ordersLineDescription,
      thumbnailPhoto: ordersLine.thumbnailPhoto,
      thumbnailPhotoContentType: ordersLine.thumbnailPhotoContentType,
      fullPhoto: ordersLine.fullPhoto,
      fullPhotoContentType: ordersLine.fullPhotoContentType,
      fullPhotoUrl: ordersLine.fullPhotoUrl,
      thumbnailPhotoUrl: ordersLine.thumbnailPhotoUrl,
      ordersId: ordersLine.ordersId,
      productId: ordersLine.productId
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
    const ordersLine = this.createFromForm();
    if (ordersLine.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersLineService.update(ordersLine));
    } else {
      this.subscribeToSaveResponse(this.ordersLineService.create(ordersLine));
    }
  }

  private createFromForm(): IOrdersLine {
    const entity = {
      ...new OrdersLine(),
      id: this.editForm.get(['id']).value,
      ordersLineName: this.editForm.get(['ordersLineName']).value,
      ordersLineValue: this.editForm.get(['ordersLineValue']).value,
      ordersLinePrice: this.editForm.get(['ordersLinePrice']).value,
      ordersLineDescription: this.editForm.get(['ordersLineDescription']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      ordersId: this.editForm.get(['ordersId']).value,
      productId: this.editForm.get(['productId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdersLine>>) {
    result.subscribe((res: HttpResponse<IOrdersLine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackOrdersById(index: number, item: IOrders) {
    return item.id;
  }

  trackProductById(index: number, item: IProduct) {
    return item.id;
  }
}
