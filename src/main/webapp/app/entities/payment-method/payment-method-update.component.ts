import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPaymentMethod, PaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from './payment-method.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-payment-method-update',
  templateUrl: './payment-method-update.component.html'
})
export class PaymentMethodUpdateComponent implements OnInit {
  paymentMethod: IPaymentMethod;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    paymentMethod: [null, [Validators.required]],
    description: [],
    active: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected paymentMethodService: PaymentMethodService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ paymentMethod }) => {
      this.updateForm(paymentMethod);
      this.paymentMethod = paymentMethod;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(paymentMethod: IPaymentMethod) {
    this.editForm.patchValue({
      id: paymentMethod.id,
      paymentMethod: paymentMethod.paymentMethod,
      description: paymentMethod.description,
      active: paymentMethod.active,
      shopId: paymentMethod.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const paymentMethod = this.createFromForm();
    if (paymentMethod.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentMethodService.update(paymentMethod));
    } else {
      this.subscribeToSaveResponse(this.paymentMethodService.create(paymentMethod));
    }
  }

  private createFromForm(): IPaymentMethod {
    const entity = {
      ...new PaymentMethod(),
      id: this.editForm.get(['id']).value,
      paymentMethod: this.editForm.get(['paymentMethod']).value,
      description: this.editForm.get(['description']).value,
      active: this.editForm.get(['active']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentMethod>>) {
    result.subscribe((res: HttpResponse<IPaymentMethod>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
