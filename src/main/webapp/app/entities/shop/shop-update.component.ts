import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IShop, Shop } from 'app/shared/model/shop.model';
import { ShopService } from './shop.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';

@Component({
  selector: 'jhi-shop-update',
  templateUrl: './shop-update.component.html'
})
export class ShopUpdateComponent implements OnInit {
  shop: IShop;
  isSaving: boolean;

  companies: ICompany[];

  profiles: IProfile[];

  editForm = this.fb.group({
    id: [],
    shopName: [null, [Validators.required]],
    shopAccountType: [],
    approvalDate: [],
    address: [],
    email: [],
    description: [],
    note: [],
    landland: [],
    mobile: [],
    createdDate: [],
    shopLogo: [],
    shopLogoContentType: [],
    shopLogoUrl: [],
    active: [],
    currency: [],
    companyId: [],
    approvedById: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected shopService: ShopService,
    protected companyService: CompanyService,
    protected profileService: ProfileService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shop }) => {
      this.updateForm(shop);
      this.shop = shop;
    });
    this.companyService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICompany[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICompany[]>) => response.body)
      )
      .subscribe((res: ICompany[]) => (this.companies = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(shop: IShop) {
    this.editForm.patchValue({
      id: shop.id,
      shopName: shop.shopName,
      shopAccountType: shop.shopAccountType,
      approvalDate: shop.approvalDate != null ? shop.approvalDate.format(DATE_TIME_FORMAT) : null,
      address: shop.address,
      email: shop.email,
      description: shop.description,
      note: shop.note,
      landland: shop.landland,
      mobile: shop.mobile,
      createdDate: shop.createdDate != null ? shop.createdDate.format(DATE_TIME_FORMAT) : null,
      shopLogo: shop.shopLogo,
      shopLogoContentType: shop.shopLogoContentType,
      shopLogoUrl: shop.shopLogoUrl,
      active: shop.active,
      currency: shop.currency,
      companyId: shop.companyId,
      approvedById: shop.approvedById
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shop = this.createFromForm();
    if (shop.id !== undefined) {
      this.subscribeToSaveResponse(this.shopService.update(shop));
    } else {
      this.subscribeToSaveResponse(this.shopService.create(shop));
    }
  }

  private createFromForm(): IShop {
    const entity = {
      ...new Shop(),
      id: this.editForm.get(['id']).value,
      shopName: this.editForm.get(['shopName']).value,
      shopAccountType: this.editForm.get(['shopAccountType']).value,
      approvalDate:
        this.editForm.get(['approvalDate']).value != null ? moment(this.editForm.get(['approvalDate']).value, DATE_TIME_FORMAT) : undefined,
      address: this.editForm.get(['address']).value,
      email: this.editForm.get(['email']).value,
      description: this.editForm.get(['description']).value,
      note: this.editForm.get(['note']).value,
      landland: this.editForm.get(['landland']).value,
      mobile: this.editForm.get(['mobile']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      shopLogoContentType: this.editForm.get(['shopLogoContentType']).value,
      shopLogo: this.editForm.get(['shopLogo']).value,
      shopLogoUrl: this.editForm.get(['shopLogoUrl']).value,
      active: this.editForm.get(['active']).value,
      currency: this.editForm.get(['currency']).value,
      companyId: this.editForm.get(['companyId']).value,
      approvedById: this.editForm.get(['approvedById']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShop>>) {
    result.subscribe((res: HttpResponse<IShop>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackCompanyById(index: number, item: ICompany) {
    return item.id;
  }

  trackProfileById(index: number, item: IProfile) {
    return item.id;
  }
}
