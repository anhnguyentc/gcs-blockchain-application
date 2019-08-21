/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GcsBlockchainApplicationTestModule } from '../../../test.module';
import { BlcRegisterPrivateKeyDetailComponent } from 'app/entities/blc-register-private-key/blc-register-private-key-detail.component';
import { BlcRegisterPrivateKey } from 'app/shared/model/blc-register-private-key.model';

describe('Component Tests', () => {
  describe('BlcRegisterPrivateKey Management Detail Component', () => {
    let comp: BlcRegisterPrivateKeyDetailComponent;
    let fixture: ComponentFixture<BlcRegisterPrivateKeyDetailComponent>;
    const route = ({ data: of({ blcRegisterPrivateKey: new BlcRegisterPrivateKey(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GcsBlockchainApplicationTestModule],
        declarations: [BlcRegisterPrivateKeyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BlcRegisterPrivateKeyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlcRegisterPrivateKeyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blcRegisterPrivateKey).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
