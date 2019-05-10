import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPayment, Payment } from 'app/shared/model/payment.model';
import { PaymentService } from './payment.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from 'app/entities/payment-method';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders';

@Component({
  selector: 'jhi-payment-update',
  templateUrl: './payment-update.component.html'
})
export class PaymentUpdateComponent implements OnInit {
  payment: IPayment;
  isSaving: boolean;

  shops: IShop[];

  profiles: IProfile[];

  paymentmethods: IPaymentMethod[];

  orders: IOrders[];

  editForm = this.fb.group({
    id: [],
    paymentDate: [],
    paymentProvider: [],
    amount: [],
    paymentStatus: [],
    curency: [],
    customerName: [],
    shopId: [],
    processedById: [],
    paymentMethodId: [],
    orderId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected paymentService: PaymentService,
    protected shopService: ShopService,
    protected profileService: ProfileService,
    protected paymentMethodService: PaymentMethodService,
    protected ordersService: OrdersService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ payment }) => {
      this.updateForm(payment);
      this.payment = payment;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.paymentMethodService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPaymentMethod[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPaymentMethod[]>) => response.body)
      )
      .subscribe((res: IPaymentMethod[]) => (this.paymentmethods = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.ordersService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrders[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrders[]>) => response.body)
      )
      .subscribe((res: IOrders[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(payment: IPayment) {
    this.editForm.patchValue({
      id: payment.id,
      paymentDate: payment.paymentDate != null ? payment.paymentDate.format(DATE_TIME_FORMAT) : null,
      paymentProvider: payment.paymentProvider,
      amount: payment.amount,
      paymentStatus: payment.paymentStatus,
      curency: payment.curency,
      customerName: payment.customerName,
      shopId: payment.shopId,
      processedById: payment.processedById,
      paymentMethodId: payment.paymentMethodId,
      orderId: payment.orderId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const payment = this.createFromForm();
    if (payment.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentService.update(payment));
    } else {
      this.subscribeToSaveResponse(this.paymentService.create(payment));
    }
  }

  private createFromForm(): IPayment {
    const entity = {
      ...new Payment(),
      id: this.editForm.get(['id']).value,
      paymentDate:
        this.editForm.get(['paymentDate']).value != null ? moment(this.editForm.get(['paymentDate']).value, DATE_TIME_FORMAT) : undefined,
      paymentProvider: this.editForm.get(['paymentProvider']).value,
      amount: this.editForm.get(['amount']).value,
      paymentStatus: this.editForm.get(['paymentStatus']).value,
      curency: this.editForm.get(['curency']).value,
      customerName: this.editForm.get(['customerName']).value,
      shopId: this.editForm.get(['shopId']).value,
      processedById: this.editForm.get(['processedById']).value,
      paymentMethodId: this.editForm.get(['paymentMethodId']).value,
      orderId: this.editForm.get(['orderId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayment>>) {
    result.subscribe((res: HttpResponse<IPayment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackProfileById(index: number, item: IProfile) {
    return item.id;
  }

  trackPaymentMethodById(index: number, item: IPaymentMethod) {
    return item.id;
  }

  trackOrdersById(index: number, item: IOrders) {
    return item.id;
  }
}
