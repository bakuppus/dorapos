import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IShopChange, ShopChange } from 'app/shared/model/shop-change.model';
import { ShopChangeService } from './shop-change.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';

@Component({
  selector: 'jhi-shop-change-update',
  templateUrl: './shop-change-update.component.html'
})
export class ShopChangeUpdateComponent implements OnInit {
  shopChange: IShopChange;
  isSaving: boolean;

  shops: IShop[];

  profiles: IProfile[];

  editForm = this.fb.group({
    id: [],
    change: [],
    changedEntity: [],
    note: [],
    changeDate: [],
    shopId: [],
    changedById: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected shopChangeService: ShopChangeService,
    protected shopService: ShopService,
    protected profileService: ProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shopChange }) => {
      this.updateForm(shopChange);
      this.shopChange = shopChange;
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
  }

  updateForm(shopChange: IShopChange) {
    this.editForm.patchValue({
      id: shopChange.id,
      change: shopChange.change,
      changedEntity: shopChange.changedEntity,
      note: shopChange.note,
      changeDate: shopChange.changeDate != null ? shopChange.changeDate.format(DATE_TIME_FORMAT) : null,
      shopId: shopChange.shopId,
      changedById: shopChange.changedById
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shopChange = this.createFromForm();
    if (shopChange.id !== undefined) {
      this.subscribeToSaveResponse(this.shopChangeService.update(shopChange));
    } else {
      this.subscribeToSaveResponse(this.shopChangeService.create(shopChange));
    }
  }

  private createFromForm(): IShopChange {
    const entity = {
      ...new ShopChange(),
      id: this.editForm.get(['id']).value,
      change: this.editForm.get(['change']).value,
      changedEntity: this.editForm.get(['changedEntity']).value,
      note: this.editForm.get(['note']).value,
      changeDate:
        this.editForm.get(['changeDate']).value != null ? moment(this.editForm.get(['changeDate']).value, DATE_TIME_FORMAT) : undefined,
      shopId: this.editForm.get(['shopId']).value,
      changedById: this.editForm.get(['changedById']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopChange>>) {
    result.subscribe((res: HttpResponse<IShopChange>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
