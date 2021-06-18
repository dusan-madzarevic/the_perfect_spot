import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StarRatingRecipeComponent } from './star-rating-recipe.component';

describe('StarRatingRecipeComponent', () => {
  let component: StarRatingRecipeComponent;
  let fixture: ComponentFixture<StarRatingRecipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StarRatingRecipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StarRatingRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
