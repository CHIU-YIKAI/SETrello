import { TestBed } from '@angular/core/testing';

import { CreateTrelloService } from './create-trello.service';

describe('CreateTrelloService', () => {
  let service: CreateTrelloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateTrelloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
