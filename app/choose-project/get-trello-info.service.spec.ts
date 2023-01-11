import { TestBed } from '@angular/core/testing';

import { GetTrelloInfoService } from './get-trello-info.service';

describe('GetTrelloInfoService', () => {
  let service: GetTrelloInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetTrelloInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
