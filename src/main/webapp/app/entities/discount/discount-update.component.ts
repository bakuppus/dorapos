import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDiscount, Discount } from 'app/shared/model/discount.model';
import { DiscountService } from './discount.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-discount-update',
  templateUrl: './discount-update.component.html'
})
export class DiscountUpdateComponent implements OnInit {
  discount: IDiscount;
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
    protected discountService: DiscountService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ discount }) => {
      this.updateForm(discount);
      this.discount = discount;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(discount: IDiscount) {
    this.editForm.patchValue({
      id: discount.id,
      description: discount.description,
      percentage: discount.percentage,
      amount: discount.amount,
      active: discount.active,
      shopId: discount.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const discount = this.createFromForm();
    if (discount.id !== undefined) {
      this.subscribeToSaveResponse(this.discountService.update(discount));
    } else {
      this.subscribeToSaveResponse(this.discountService.create(discount));
    }
  }

  private createFromForm(): IDiscount {
    const entity = {
      ...new Discount(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      percentage: this.editForm.get(['percentage']).value,
      amount: this.editForm.get(['amount']).value,
      active: this.editForm.get(['active']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDiscount>>) {
    result.subscribe((res: HttpResponse<IDiscount>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
