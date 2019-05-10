import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPaymentMethodConfig, PaymentMethodConfig } from 'app/shared/model/payment-method-config.model';
import { PaymentMethodConfigService } from './payment-method-config.service';
import { IPaymentMethod } from 'app/shared/model/payment-method.model';
import { PaymentMethodService } from 'app/entities/payment-method';

@Component({
  selector: 'jhi-payment-method-config-update',
  templateUrl: './payment-method-config-update.component.html'
})
export class PaymentMethodConfigUpdateComponent implements OnInit {
  paymentMethodConfig: IPaymentMethodConfig;
  isSaving: boolean;

  paymentmethods: IPaymentMethod[];

  editForm = this.fb.group({
    id: [],
    key: [],
    value: [],
    note: [],
    enabled: [],
    paymentMethodId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected paymentMethodConfigService: PaymentMethodConfigService,
    protected paymentMethodService: PaymentMethodService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ paymentMethodConfig }) => {
      this.updateForm(paymentMethodConfig);
      this.paymentMethodConfig = paymentMethodConfig;
    });
    this.paymentMethodService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPaymentMethod[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPaymentMethod[]>) => response.body)
      )
      .subscribe((res: IPaymentMethod[]) => (this.paymentmethods = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(paymentMethodConfig: IPaymentMethodConfig) {
    this.editForm.patchValue({
      id: paymentMethodConfig.id,
      key: paymentMethodConfig.key,
      value: paymentMethodConfig.value,
      note: paymentMethodConfig.note,
      enabled: paymentMethodConfig.enabled,
      paymentMethodId: paymentMethodConfig.paymentMethodId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const paymentMethodConfig = this.createFromForm();
    if (paymentMethodConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentMethodConfigService.update(paymentMethodConfig));
    } else {
      this.subscribeToSaveResponse(this.paymentMethodConfigService.create(paymentMethodConfig));
    }
  }

  private createFromForm(): IPaymentMethodConfig {
    const entity = {
      ...new PaymentMethodConfig(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      value: this.editForm.get(['value']).value,
      note: this.editForm.get(['note']).value,
      enabled: this.editForm.get(['enabled']).value,
      paymentMethodId: this.editForm.get(['paymentMethodId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentMethodConfig>>) {
    result.subscribe((res: HttpResponse<IPaymentMethodConfig>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
