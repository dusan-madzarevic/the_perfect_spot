import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantReportsComponent } from './restaurant-reports.component';

describe('RestaurantReportsComponent', () => {
  let component: RestaurantReportsComponent;
  let fixture: ComponentFixture<RestaurantReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
