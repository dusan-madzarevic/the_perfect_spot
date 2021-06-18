import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ReportService} from '../../services/report.service';
import {RestaurantAverageModel} from '../../model/restaurant.average.model';
import {RestaurantModel} from '../../model/restaurant.model';
import {DateAdapter} from '@angular/material/core';


export const MY_FORMATS = {
  parse: {
    dateInput: 'LL'
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY'
  }
};

@Component({
  selector: 'app-restaurant-reports',
  templateUrl: './restaurant-reports.component.html',
  styleUrls: ['./restaurant-reports.component.css']
})
export class RestaurantReportsComponent implements OnInit {

  showRestaurants: boolean = false;
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });
  reportsForm = new FormGroup({
    report: new FormControl(''),
    minGrade: new FormControl(''),
    maxGrade: new FormControl(('')),
  });
  report: string = '';
  restaurants: Array<RestaurantModel> = []
  restaurantAvgs: Array<RestaurantAverageModel> = [];

  constructor(private reportService: ReportService,
              private dateAdapter: DateAdapter<Date>) {
    this.dateAdapter.setLocale('fr-CA');
  }

  ngOnInit(): void {
    this.showRestaurants = false;

  }

  getReport() {
    switch (this.reportsForm.controls['report'].value) {
      case "mostRecomm":{
        this.reportService.getMostRecommended().subscribe((res)=>{
          this.restaurants = res;
          this.showRestaurants = true;
        })
      }
      break;
      case "gradeRange":{
        this.reportService.getInGradeRange(this.reportsForm.controls['minGrade'].value, this.reportsForm.controls['maxGrade'].value).subscribe((res)=>{
          this.restaurants = res;
          console.log(res)
          this.showRestaurants = true;
        })
      }
      break;
      case "best":{
        this.reportService.getBestGraded().subscribe((res)=>{
          this.restaurants = res;
          console.log(res)
          this.showRestaurants = true;
        })
      }
      break;
      case "bestLastMonth":{
        this.reportService.getBestGradedLastMonth().subscribe((res)=>{
          this.restaurantAvgs = res;
          console.log(res)
          this.showRestaurants = true;
        })
      }
      break;
      case "dateRange":{
        let f = new Intl.DateTimeFormat('fr-CA');
        this.reportService.getInDateRange(f.format(this.range.controls['start'].value), f.format(this.range.controls['end'].value)).subscribe((res)=>{
          this.restaurantAvgs = res;
          console.log(res)
          this.showRestaurants = true;
        })
      }

    }
  }
}
