import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISystemConfig, SystemConfig } from 'app/shared/model/system-config.model';
import { SystemConfigService } from './system-config.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-system-config-update',
  templateUrl: './system-config-update.component.html'
})
export class SystemConfigUpdateComponent implements OnInit {
  systemConfig: ISystemConfig;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    key: [],
    value: [],
    configurationType: [],
    note: [],
    enabled: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected systemConfigService: SystemConfigService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ systemConfig }) => {
      this.updateForm(systemConfig);
      this.systemConfig = systemConfig;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(systemConfig: ISystemConfig) {
    this.editForm.patchValue({
      id: systemConfig.id,
      key: systemConfig.key,
      value: systemConfig.value,
      configurationType: systemConfig.configurationType,
      note: systemConfig.note,
      enabled: systemConfig.enabled,
      shopId: systemConfig.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const systemConfig = this.createFromForm();
    if (systemConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.systemConfigService.update(systemConfig));
    } else {
      this.subscribeToSaveResponse(this.systemConfigService.create(systemConfig));
    }
  }

  private createFromForm(): ISystemConfig {
    const entity = {
      ...new SystemConfig(),
      id: this.editForm.get(['id']).value,
      key: this.editForm.get(['key']).value,
      value: this.editForm.get(['value']).value,
      configurationType: this.editForm.get(['configurationType']).value,
      note: this.editForm.get(['note']).value,
      enabled: this.editForm.get(['enabled']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemConfig>>) {
    result.subscribe((res: HttpResponse<ISystemConfig>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
