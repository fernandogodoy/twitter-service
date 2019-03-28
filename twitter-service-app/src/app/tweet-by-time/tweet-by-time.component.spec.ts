import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetByTimeComponent } from './tweet-by-time.component';

describe('TweetByTimeComponent', () => {
  let component: TweetByTimeComponent;
  let fixture: ComponentFixture<TweetByTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TweetByTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TweetByTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
