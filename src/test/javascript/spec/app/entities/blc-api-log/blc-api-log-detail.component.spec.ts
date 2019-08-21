/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcAPILogDetailComponent } from 'app/entities/blc-api-log/blc-api-log-detail.component';
import { BlcAPILog } from 'app/shared/model/blc-api-log.model';

describe('Component Tests', () => {
  describe('BlcAPILog Management Detail Component', () => {
    let comp: BlcAPILogDetailComponent;
    let fixture: ComponentFixture<BlcAPILogDetailComponent>;
    const route = ({ data: of({ blcAPILog: new BlcAPILog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcAPILogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlcAPILogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcAPILogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blcAPILog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
