import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IProfile, Profile } from 'app/shared/model/profile.model';
import { ProfileService } from './profile.service';
import { IUser, UserService } from 'app/core';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-profile-update',
  templateUrl: './profile-update.component.html'
})
export class ProfileUpdateComponent implements OnInit {
  profile: IProfile;
  isSaving: boolean;

  users: IUser[];

  shops: IShop[];
  dateOfBirthDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    email: [null, [Validators.required]],
    userType: [null, [Validators.required]],
    dateOfBirth: [],
    gender: [],
    registerationDate: [],
    lastAccess: [],
    profileStatus: [],
    telephone: [],
    mobile: [],
    hourlyPayRate: [],
    thumbnailPhoto: [],
    thumbnailPhotoContentType: [],
    thumbnailPhotoUrl: [],
    fullPhoto: [],
    fullPhotoContentType: [],
    fullPhotoUrl: [],
    active: [],
    shopChangeId: [],
    userId: [],
    shopId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected profileService: ProfileService,
    protected userService: UserService,
    protected shopService: ShopService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ profile }) => {
      this.updateForm(profile);
      this.profile = profile;
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(profile: IProfile) {
    this.editForm.patchValue({
      id: profile.id,
      firstName: profile.firstName,
      lastName: profile.lastName,
      email: profile.email,
      userType: profile.userType,
      dateOfBirth: profile.dateOfBirth,
      gender: profile.gender,
      registerationDate: profile.registerationDate != null ? profile.registerationDate.format(DATE_TIME_FORMAT) : null,
      lastAccess: profile.lastAccess != null ? profile.lastAccess.format(DATE_TIME_FORMAT) : null,
      profileStatus: profile.profileStatus,
      telephone: profile.telephone,
      mobile: profile.mobile,
      hourlyPayRate: profile.hourlyPayRate,
      thumbnailPhoto: profile.thumbnailPhoto,
      thumbnailPhotoContentType: profile.thumbnailPhotoContentType,
      thumbnailPhotoUrl: profile.thumbnailPhotoUrl,
      fullPhoto: profile.fullPhoto,
      fullPhotoContentType: profile.fullPhotoContentType,
      fullPhotoUrl: profile.fullPhotoUrl,
      active: profile.active,
      shopChangeId: profile.shopChangeId,
      userId: profile.userId,
      shopId: profile.shopId
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
    const profile = this.createFromForm();
    if (profile.id !== undefined) {
      this.subscribeToSaveResponse(this.profileService.update(profile));
    } else {
      this.subscribeToSaveResponse(this.profileService.create(profile));
    }
  }

  private createFromForm(): IProfile {
    const entity = {
      ...new Profile(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      email: this.editForm.get(['email']).value,
      userType: this.editForm.get(['userType']).value,
      dateOfBirth: this.editForm.get(['dateOfBirth']).value,
      gender: this.editForm.get(['gender']).value,
      registerationDate:
        this.editForm.get(['registerationDate']).value != null
          ? moment(this.editForm.get(['registerationDate']).value, DATE_TIME_FORMAT)
          : undefined,
      lastAccess:
        this.editForm.get(['lastAccess']).value != null ? moment(this.editForm.get(['lastAccess']).value, DATE_TIME_FORMAT) : undefined,
      profileStatus: this.editForm.get(['profileStatus']).value,
      telephone: this.editForm.get(['telephone']).value,
      mobile: this.editForm.get(['mobile']).value,
      hourlyPayRate: this.editForm.get(['hourlyPayRate']).value,
      thumbnailPhotoContentType: this.editForm.get(['thumbnailPhotoContentType']).value,
      thumbnailPhoto: this.editForm.get(['thumbnailPhoto']).value,
      thumbnailPhotoUrl: this.editForm.get(['thumbnailPhotoUrl']).value,
      fullPhotoContentType: this.editForm.get(['fullPhotoContentType']).value,
      fullPhoto: this.editForm.get(['fullPhoto']).value,
      fullPhotoUrl: this.editForm.get(['fullPhotoUrl']).value,
      active: this.editForm.get(['active']).value,
      shopChangeId: this.editForm.get(['shopChangeId']).value,
      userId: this.editForm.get(['userId']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProfile>>) {
    result.subscribe((res: HttpResponse<IProfile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackShopById(index: number, item: IShop) {
    return item.id;
  }
}
