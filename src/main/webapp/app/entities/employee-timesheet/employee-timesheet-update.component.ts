import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEmployeeTimesheet, EmployeeTimesheet } from 'app/shared/model/employee-timesheet.model';
import { EmployeeTimesheetService } from './employee-timesheet.service';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop';

@Component({
  selector: 'jhi-employee-timesheet-update',
  templateUrl: './employee-timesheet-update.component.html'
})
export class EmployeeTimesheetUpdateComponent implements OnInit {
  employeeTimesheet: IEmployeeTimesheet;
  isSaving: boolean;

  profiles: IProfile[];

  shops: IShop[];

  editForm = this.fb.group({
    id: [],
    checkinTime: [],
    checkOutTime: [],
    regularHoursWorked: [],
    overTimeHoursWorked: [],
    pay: [],
    profileId: [],
    shopId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected employeeTimesheetService: EmployeeTimesheetService,
    protected profileService: ProfileService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ employeeTimesheet }) => {
      this.updateForm(employeeTimesheet);
      this.employeeTimesheet = employeeTimesheet;
    });
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.shopService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShop[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShop[]>) => response.body)
      )
      .subscribe((res: IShop[]) => (this.shops = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(employeeTimesheet: IEmployeeTimesheet) {
    this.editForm.patchValue({
      id: employeeTimesheet.id,
      checkinTime: employeeTimesheet.checkinTime != null ? employeeTimesheet.checkinTime.format(DATE_TIME_FORMAT) : null,
      checkOutTime: employeeTimesheet.checkOutTime != null ? employeeTimesheet.checkOutTime.format(DATE_TIME_FORMAT) : null,
      regularHoursWorked: employeeTimesheet.regularHoursWorked,
      overTimeHoursWorked: employeeTimesheet.overTimeHoursWorked,
      pay: employeeTimesheet.pay,
      profileId: employeeTimesheet.profileId,
      shopId: employeeTimesheet.shopId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const employeeTimesheet = this.createFromForm();
    if (employeeTimesheet.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeTimesheetService.update(employeeTimesheet));
    } else {
      this.subscribeToSaveResponse(this.employeeTimesheetService.create(employeeTimesheet));
    }
  }

  private createFromForm(): IEmployeeTimesheet {
    const entity = {
      ...new EmployeeTimesheet(),
      id: this.editForm.get(['id']).value,
      checkinTime:
        this.editForm.get(['checkinTime']).value != null ? moment(this.editForm.get(['checkinTime']).value, DATE_TIME_FORMAT) : undefined,
      checkOutTime:
        this.editForm.get(['checkOutTime']).value != null ? moment(this.editForm.get(['checkOutTime']).value, DATE_TIME_FORMAT) : undefined,
      regularHoursWorked: this.editForm.get(['regularHoursWorked']).value,
      overTimeHoursWorked: this.editForm.get(['overTimeHoursWorked']).value,
      pay: this.editForm.get(['pay']).value,
      profileId: this.editForm.get(['profileId']).value,
      shopId: this.editForm.get(['shopId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeeTimesheet>>) {
    result.subscribe((res: HttpResponse<IEmployeeTimesheet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackProfileById(index: number, item: IProfile) {
    return item.id;
  }

  trackShopById(index: number, item: IShop) {
    return item.id;
  }
}
