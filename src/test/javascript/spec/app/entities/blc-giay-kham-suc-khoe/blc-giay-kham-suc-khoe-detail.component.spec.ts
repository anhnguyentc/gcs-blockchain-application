/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcGiayKhamSucKhoeDetailComponent } from 'app/entities/blc-giay-kham-suc-khoe/blc-giay-kham-suc-khoe-detail.component';
import { BlcGiayKhamSucKhoe } from 'app/shared/model/blc-giay-kham-suc-khoe.model';

describe('Component Tests', () => {
  describe('BlcGiayKhamSucKhoe Management Detail Component', () => {
    let comp: BlcGiayKhamSucKhoeDetailComponent;
    let fixture: ComponentFixture<BlcGiayKhamSucKhoeDetailComponent>;
    const route = ({ data: of({ blcGiayKhamSucKhoe: new BlcGiayKhamSucKhoe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcGiayKhamSucKhoeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlcGiayKhamSucKhoeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcGiayKhamSucKhoeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blcGiayKhamSucKhoe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
