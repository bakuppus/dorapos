import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IShopDevice, ShopDevice } from 'app/shared/model/shop-device.model';
import { ShopDeviceService } from './shop-device.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-shop-device-update',
  templateUrl: './shop-device-update.component.html'
})
export class ShopDeviceUpdateComponent implements OnInit {
  shopDevice: IShopDevice;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    deviceName: [],
    deviceModel: [],
    registeredDate: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected shopDeviceService: ShopDeviceService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shopDevice }) => {
      this.updateForm(shopDevice);
      this.shopDevice = shopDevice;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(shopDevice: IShopDevice) {
    this.editForm.patchValue({
      id: shopDevice.id,
      deviceName: shopDevice.deviceName,
      deviceModel: shopDevice.deviceModel,
      registeredDate: shopDevice.registeredDate != null ? shopDevice.registeredDate.format(DATE_TIME_FORMAT) : null,
      shopId: shopDevice.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shopDevice = this.createFromForm();
    if (shopDevice.id !== undefined) {
      this.subscribeToSaveResponse(this.shopDeviceService.update(shopDevice));
    } else {
      this.subscribeToSaveResponse(this.shopDeviceService.create(shopDevice));
    }
  }

  private createFromForm(): IShopDevice {
    const entity = {
      ...new ShopDevice(),
      id: this.editForm.get(['id']).value,
      deviceName: this.editForm.get(['deviceName']).value,
      deviceModel: this.editForm.get(['deviceModel']).value,
      registeredDate:
        this.editForm.get(['registeredDate']).value != null
          ? moment(this.editForm.get(['registeredDate']).value, DATE_TIME_FORMAT)
          : undefined,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopDevice>>) {
    result.subscribe((res: HttpResponse<IShopDevice>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
