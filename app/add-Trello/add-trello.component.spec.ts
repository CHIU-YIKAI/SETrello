import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTrelloComponent } from './add-trello.component';

describe('AddTrelloComponent', () => {
  let component: AddTrelloComponent;
  let fixture: ComponentFixture<AddTrelloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTrelloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTrelloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
