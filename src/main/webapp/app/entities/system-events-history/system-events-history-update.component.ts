import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISystemEventsHistory, SystemEventsHistory } from 'app/shared/model/system-events-history.model';
import { SystemEventsHistoryService } from './system-events-history.service';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';

@Component({
  selector: 'jhi-system-events-history-update',
  templateUrl: './system-events-history-update.component.html'
})
export class SystemEventsHistoryUpdateComponent implements OnInit {
  systemEventsHistory: ISystemEventsHistory;
  isSaving: boolean;

  profiles: IProfile[];

  editForm = this.fb.group({
    id: [],
    eventName: [null, [Validators.required]],
    eventDate: [],
    eventApi: [],
    eventNote: [],
    eventEntityName: [],
    eventEntityId: [],
    triggedById: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected systemEventsHistoryService: SystemEventsHistoryService,
    protected profileService: ProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ systemEventsHistory }) => {
      this.updateForm(systemEventsHistory);
      this.systemEventsHistory = systemEventsHistory;
    });
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(systemEventsHistory: ISystemEventsHistory) {
    this.editForm.patchValue({
      id: systemEventsHistory.id,
      eventName: systemEventsHistory.eventName,
      eventDate: systemEventsHistory.eventDate != null ? systemEventsHistory.eventDate.format(DATE_TIME_FORMAT) : null,
      eventApi: systemEventsHistory.eventApi,
      eventNote: systemEventsHistory.eventNote,
      eventEntityName: systemEventsHistory.eventEntityName,
      eventEntityId: systemEventsHistory.eventEntityId,
      triggedById: systemEventsHistory.triggedById
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const systemEventsHistory = this.createFromForm();
    if (systemEventsHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.systemEventsHistoryService.update(systemEventsHistory));
    } else {
      this.subscribeToSaveResponse(this.systemEventsHistoryService.create(systemEventsHistory));
    }
  }

  private createFromForm(): ISystemEventsHistory {
    const entity = {
      ...new SystemEventsHistory(),
      id: this.editForm.get(['id']).value,
      eventName: this.editForm.get(['eventName']).value,
      eventDate:
        this.editForm.get(['eventDate']).value != null ? moment(this.editForm.get(['eventDate']).value, DATE_TIME_FORMAT) : undefined,
      eventApi: this.editForm.get(['eventApi']).value,
      eventNote: this.editForm.get(['eventNote']).value,
      eventEntityName: this.editForm.get(['eventEntityName']).value,
      eventEntityId: this.editForm.get(['eventEntityId']).value,
      triggedById: this.editForm.get(['triggedById']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISystemEventsHistory>>) {
    result.subscribe((res: HttpResponse<ISystemEventsHistory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
