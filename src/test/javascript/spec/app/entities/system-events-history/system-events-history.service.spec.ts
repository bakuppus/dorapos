/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SystemEventsHistoryService } from 'app/entities/system-events-history/system-events-history.service';
import { ISystemEventsHistory, SystemEventsHistory } from 'app/shared/model/system-events-history.model';

describe('Service Tests', () => {
  describe('SystemEventsHistory Service', () => {
    let injector: TestBed;
    let service: SystemEventsHistoryService;
    let httpMock: HttpTestingController;
    let elemDefault: ISystemEventsHistory;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SystemEventsHistoryService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SystemEventsHistory(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            eventDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a SystemEventsHistory', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            eventDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            eventDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new SystemEventsHistory(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a SystemEventsHistory', async () => {
        const returnedFromService = Object.assign(
          {
            eventName: 'BBBBBB',
            eventDate: currentDate.format(DATE_TIME_FORMAT),
            eventApi: 'BBBBBB',
            eventNote: 'BBBBBB',
            eventEntityName: 'BBBBBB',
            eventEntityId: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            eventDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of SystemEventsHistory', async () => {
        const returnedFromService = Object.assign(
          {
            eventName: 'BBBBBB',
            eventDate: currentDate.format(DATE_TIME_FORMAT),
            eventApi: 'BBBBBB',
            eventNote: 'BBBBBB',
            eventEntityName: 'BBBBBB',
            eventEntityId: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            eventDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SystemEventsHistory', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
