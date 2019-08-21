/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { DonViHanhChinhDetailComponent } from 'app/entities/don-vi-hanh-chinh/don-vi-hanh-chinh-detail.component';
import { DonViHanhChinh } from 'app/shared/model/don-vi-hanh-chinh.model';

describe('Component Tests', () => {
  describe('DonViHanhChinh Management Detail Component', () => {
    let comp: DonViHanhChinhDetailComponent;
    let fixture: ComponentFixture<DonViHanhChinhDetailComponent>;
    const route = ({ data: of({ donViHanhChinh: new DonViHanhChinh(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [DonViHanhChinhDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DonViHanhChinhDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DonViHanhChinhDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.donViHanhChinh).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
