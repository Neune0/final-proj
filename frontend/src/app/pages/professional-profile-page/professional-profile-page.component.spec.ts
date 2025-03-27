import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalProfilePageComponent } from './professional-profile-page.component';

describe('ProfessionalProfilePageComponent', () => {
  let component: ProfessionalProfilePageComponent;
  let fixture: ComponentFixture<ProfessionalProfilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfessionalProfilePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfessionalProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
