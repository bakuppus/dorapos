import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IOrders, Orders } from 'app/shared/model/orders.model';
import { OrdersService } from './orders.service';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from 'app/entities/payment-method';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';
import { IShopDevice } from 'app/shared/model/shop-device.model';
import { ShopDeviceService } from 'app/entities/shop-device';
import { ISectionTable } from 'app/shared/model/section-table.model';
import { SectionTableService } from 'app/entities/section-table';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-orders-update',
  templateUrl: './orders-update.component.html'
})
export class OrdersUpdateComponent implements OnInit {
  orders: IOrders;
  isSaving: boolean;

  paymentmethods: IPaymentMethod[];

  profiles: IProfile[];

  shopdevices: IShopDevice[];

  sectiontables: ISectionTable[];

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    description: [],
    customerName: [],
    totalPrice: [null, [Validators.required]],
    quantity: [null, [Validators.required]],
    discountPercentage: [],
    discountAmount: [],
    taxPercentage: [],
    taxAmount: [],
    orderStatus: [],
    note: [],
    orderDate: [],
    isVariantOrder: [],
    paymentMethodId: [],
    soldById: [],
    preparedById: [],
    shopDeviceId: [],
    sectionTableId: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected ordersService: OrdersService,
    protected paymentMethodService: PaymentMethodService,
    protected profileService: ProfileService,
    protected shopDeviceService: ShopDeviceService,
    protected sectionTableService: SectionTableService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orders }) => {
      this.updateForm(orders);
      this.orders = orders;
    });
    this.paymentMethodService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPaymentMethod[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPaymentMethod[]>) => response.body)
      )
      .subscribe((res: IPaymentMethod[]) => (this.paymentmethods = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.shopDeviceService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShopDevice[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShopDevice[]>) => response.body)
      )
      .subscribe((res: IShopDevice[]) => (this.shopdevices = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.sectionTableService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ISectionTable[]>) => mayBeOk.ok),
        map((response: HttpResponse<ISectionTable[]>) => response.body)
      )
      .subscribe((res: ISectionTable[]) => (this.sectiontables = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(orders: IOrders) {
    this.editForm.patchValue({
      id: orders.id,
      description: orders.description,
      customerName: orders.customerName,
      totalPrice: orders.totalPrice,
      quantity: orders.quantity,
      discountPercentage: orders.discountPercentage,
      discountAmount: orders.discountAmount,
      taxPercentage: orders.taxPercentage,
      taxAmount: orders.taxAmount,
      orderStatus: orders.orderStatus,
      note: orders.note,
      orderDate: orders.orderDate != null ? orders.orderDate.format(DATE_TIME_FORMAT) : null,
      isVariantOrder: orders.isVariantOrder,
      paymentMethodId: orders.paymentMethodId,
      soldById: orders.soldById,
      preparedById: orders.preparedById,
      shopDeviceId: orders.shopDeviceId,
      sectionTableId: orders.sectionTableId,
      shopId: orders.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orders = this.createFromForm();
    if (orders.id !== undefined) {
      this.subscribeToSaveResponse(this.ordersService.update(orders));
    } else {
      this.subscribeToSaveResponse(this.ordersService.create(orders));
    }
  }

  private createFromForm(): IOrders {
    const entity = {
      ...new Orders(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      customerName: this.editForm.get(['customerName']).value,
      totalPrice: this.editForm.get(['totalPrice']).value,
      quantity: this.editForm.get(['quantity']).value,
      discountPercentage: this.editForm.get(['discountPercentage']).value,
      discountAmount: this.editForm.get(['discountAmount']).value,
      taxPercentage: this.editForm.get(['taxPercentage']).value,
      taxAmount: this.editForm.get(['taxAmount']).value,
      orderStatus: this.editForm.get(['orderStatus']).value,
      note: this.editForm.get(['note']).value,
      orderDate:
        this.editForm.get(['orderDate']).value != null ? moment(this.editForm.get(['orderDate']).value, DATE_TIME_FORMAT) : undefined,
      isVariantOrder: this.editForm.get(['isVariantOrder']).value,
      paymentMethodId: this.editForm.get(['paymentMethodId']).value,
      soldById: this.editForm.get(['soldById']).value,
      preparedById: this.editForm.get(['preparedById']).value,
      shopDeviceId: this.editForm.get(['shopDeviceId']).value,
      sectionTableId: this.editForm.get(['sectionTableId']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrders>>) {
    result.subscribe((res: HttpResponse<IOrders>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackPaymentMethodById(index: number, item: IPaymentMethod) {
    return item.id;
  }

  trackProfileById(index: number, item: IProfile) {
    return item.id;
  }

  trackShopDeviceById(index: number, item: IShopDevice) {
    return item.id;
  }

  trackSectionTableById(index: number, item: ISectionTable) {
    return item.id;
  }

  trackShopById(index: number, item: IShop) {
    return item.id;
  }
}
