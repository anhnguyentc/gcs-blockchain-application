/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayKhamSucKhoeComponent } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.component';
import { BlcGiayKhamSucKhoeService } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe.service';
import { BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

describe('Component Tests', () => {
  describe('BlcGiayKhamSucKhoe Management Component', () => {
    let comp: BlcGiayKhamSucKhoeComponent;
    let fixture: ComponentFixture<BlcGiayKhamSucKhoeComponent>;
    let service: BlcGiayKhamSucKhoeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayKhamSucKhoeComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(BlcGiayKhamSucKhoeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BlcGiayKhamSucKhoeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BlcGiayKhamSucKhoeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BlcGiayKhamSucKhoe(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.blcGiayKhamSucKhoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BlcGiayKhamSucKhoe(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.blcGiayKhamSucKhoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page is the page is the same as the previous page', () => {
      spyOn(service, 'query').and.callThrough();

      // WHEN
      comp.loadPage(0);

      // THEN
      expect(service.query).toHaveBeenCalledTimes(0);
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BlcGiayKhamSucKhoe(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.clear();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.blcGiayKhamSucKhoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
