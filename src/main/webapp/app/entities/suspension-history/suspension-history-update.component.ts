import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ISuspensionHistory, SuspensionHistory } from 'app/shared/model/suspension-history.model';
import { SuspensionHistoryService } from './suspension-history.service';
import { IProfile } from 'app/shared/model/profile.model';
import { ProfileService } from 'app/entities/profile';

@Component({
  selector: 'jhi-suspension-history-update',
  templateUrl: './suspension-history-update.component.html'
})
export class SuspensionHistoryUpdateComponent implements OnInit {
  suspensionHistory: ISuspensionHistory;
  isSaving: boolean;

  profiles: IProfile[];

  editForm = this.fb.group({
    id: [],
    suspendedDate: [],
    suspensionType: [],
    suspendedReason: [],
    resolutionNote: [],
    unsuspensionDate: [],
    profileId: [],
    suspendedById: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected suspensionHistoryService: SuspensionHistoryService,
    protected profileService: ProfileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ suspensionHistory }) => {
      this.updateForm(suspensionHistory);
      this.suspensionHistory = suspensionHistory;
    });
    this.profileService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProfile[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProfile[]>) => response.body)
      )
      .subscribe((res: IProfile[]) => (this.profiles = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(suspensionHistory: ISuspensionHistory) {
    this.editForm.patchValue({
      id: suspensionHistory.id,
      suspendedDate: suspensionHistory.suspendedDate != null ? suspensionHistory.suspendedDate.format(DATE_TIME_FORMAT) : null,
      suspensionType: suspensionHistory.suspensionType,
      suspendedReason: suspensionHistory.suspendedReason,
      resolutionNote: suspensionHistory.resolutionNote,
      unsuspensionDate: suspensionHistory.unsuspensionDate != null ? suspensionHistory.unsuspensionDate.format(DATE_TIME_FORMAT) : null,
      profileId: suspensionHistory.profileId,
      suspendedById: suspensionHistory.suspendedById
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const suspensionHistory = this.createFromForm();
    if (suspensionHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.suspensionHistoryService.update(suspensionHistory));
    } else {
      this.subscribeToSaveResponse(this.suspensionHistoryService.create(suspensionHistory));
    }
  }

  private createFromForm(): ISuspensionHistory {
    const entity = {
      ...new SuspensionHistory(),
      id: this.editForm.get(['id']).value,
      suspendedDate:
        this.editForm.get(['suspendedDate']).value != null
          ? moment(this.editForm.get(['suspendedDate']).value, DATE_TIME_FORMAT)
          : undefined,
      suspensionType: this.editForm.get(['suspensionType']).value,
      suspendedReason: this.editForm.get(['suspendedReason']).value,
      resolutionNote: this.editForm.get(['resolutionNote']).value,
      unsuspensionDate:
        this.editForm.get(['unsuspensionDate']).value != null
          ? moment(this.editForm.get(['unsuspensionDate']).value, DATE_TIME_FORMAT)
          : undefined,
      profileId: this.editForm.get(['profileId']).value,
      suspendedById: this.editForm.get(['suspendedById']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISuspensionHistory>>) {
    result.subscribe((res: HttpResponse<ISuspensionHistory>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
