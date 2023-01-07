import { TestBed } from '@angular/core/testing';

import { TrelloBoardService } from './trello-board.service';

describe('TrelloBoardService', () => {
  let service: TrelloBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrelloBoardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
