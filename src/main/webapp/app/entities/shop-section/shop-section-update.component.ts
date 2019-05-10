import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IShopSection, ShopSection } from 'app/shared/model/shop-section.model';
import { ShopSectionService } from './shop-section.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-shop-section-update',
  templateUrl: './shop-section-update.component.html'
})
export class ShopSectionUpdateComponent implements OnInit {
  shopSection: IShopSection;
  isSaving: boolean;

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    sectionName: [],
    description: [],
    surchargePercentage: [],
    surchargeFlatAmount: [],
    usePercentage: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected shopSectionService: ShopSectionService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shopSection }) => {
      this.updateForm(shopSection);
      this.shopSection = shopSection;
    });
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(shopSection: IShopSection) {
    this.editForm.patchValue({
      id: shopSection.id,
      sectionName: shopSection.sectionName,
      description: shopSection.description,
      surchargePercentage: shopSection.surchargePercentage,
      surchargeFlatAmount: shopSection.surchargeFlatAmount,
      usePercentage: shopSection.usePercentage,
      shopId: shopSection.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shopSection = this.createFromForm();
    if (shopSection.id !== undefined) {
      this.subscribeToSaveResponse(this.shopSectionService.update(shopSection));
    } else {
      this.subscribeToSaveResponse(this.shopSectionService.create(shopSection));
    }
  }

  private createFromForm(): IShopSection {
    const entity = {
      ...new ShopSection(),
      id: this.editForm.get(['id']).value,
      sectionName: this.editForm.get(['sectionName']).value,
      description: this.editForm.get(['description']).value,
      surchargePercentage: this.editForm.get(['surchargePercentage']).value,
      surchargeFlatAmount: this.editForm.get(['surchargeFlatAmount']).value,
      usePercentage: this.editForm.get(['usePercentage']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopSection>>) {
    result.subscribe((res: HttpResponse<IShopSection>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
