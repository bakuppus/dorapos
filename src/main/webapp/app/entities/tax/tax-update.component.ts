import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITax, Tax } from 'app/shared/model/tax.model';
import { TaxService } from './tax.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-tax-update',
  templateUrl: './tax-update.component.html'
})
export class TaxUpdateComponent implements OnInit {
  tax: ITax;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required]],
    percentage: [null, [Validators.required]],
    amount: [null, [Validators.required]],
    active: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taxService: TaxService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tax }) => {
      this.updateForm(tax);
      this.tax = tax;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(tax: ITax) {
    this.editForm.patchValue({
      id: tax.id,
      description: tax.description,
      percentage: tax.percentage,
      amount: tax.amount,
      active: tax.active,
      shopId: tax.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tax = this.createFromForm();
    if (tax.id !== undefined) {
      this.subscribeToSaveResponse(this.taxService.update(tax));
    } else {
      this.subscribeToSaveResponse(this.taxService.create(tax));
    }
  }

  private createFromForm(): ITax {
    const entity = {
      ...new Tax(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      percentage: this.editForm.get(['percentage']).value,
      amount: this.editForm.get(['amount']).value,
      active: this.editForm.get(['active']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITax>>) {
    result.subscribe((res: HttpResponse<ITax>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
