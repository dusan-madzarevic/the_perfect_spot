import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeReportsComponent } from './recipe-reports.component';

describe('RecipeReportsComponent', () => {
  let component: RecipeReportsComponent;
  let fixture: ComponentFixture<RecipeReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
