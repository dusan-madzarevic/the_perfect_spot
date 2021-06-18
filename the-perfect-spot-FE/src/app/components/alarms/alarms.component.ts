import { Component, OnInit } from '@angular/core';
import {PageEvent} from '@angular/material/paginator';
import {AlarmService} from '../../services/alarm.service';
import {AlarmModel} from '../../model/alarm.model';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css']
})
export class AlarmsComponent implements OnInit {
  p_length = 0;
  pageSize = 12;
  pageIndex = 0;
  showFirstLastButtons = true;
  alarms: Array<AlarmModel> = [];

  constructor(private alarmService: AlarmService) { }

  ngOnInit(): void {
    this.alarmService.getAll(this.pageIndex).subscribe((response) => {
      console.log(response.content)
      this.alarms = response.content;
      this.p_length = response.totalElements;
    })
  }
  handlePageEvent(event: PageEvent) {
    this.p_length = event.length;
    this.pageIndex = event.pageIndex;

    this.alarmService.getAll(this.pageIndex).subscribe((response) => {
      this.alarms = response.content;
      this.p_length = response.totalElements;
    })}
}
