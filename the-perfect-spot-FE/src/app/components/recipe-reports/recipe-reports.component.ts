import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { RecipeAverageModel } from 'src/app/model/recipe.average.model';
import { RecipeModel } from 'src/app/model/recipe.model';
import { ReportService } from 'src/app/services/report.service';

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
  selector: 'app-recipe-reports',
  templateUrl: './recipe-reports.component.html',
  styleUrls: ['./recipe-reports.component.css']
})
export class RecipeReportsComponent implements OnInit {

  showRecipes: boolean = false;
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
  recipes: Array<RecipeModel> = []
  recipeAvgs: Array<RecipeAverageModel> = [];

  constructor(private reportService: ReportService,
              private dateAdapter: DateAdapter<Date>) {
    this.dateAdapter.setLocale('fr-CA');
  }

  ngOnInit(): void {
    this.showRecipes = false;

  }

  getReport() {
    switch (this.reportsForm.controls['report'].value) {
      case "mostRecomm":{
        this.reportService.getMostRecommendedRecipes().subscribe((res)=>{
          this.recipes = res;
          console.log(res);
          this.showRecipes = true;
        })
      }
      break;
      case "gradeRange":{
        this.reportService.getInGradeRangeRecipes(this.reportsForm.controls['minGrade'].value, this.reportsForm.controls['maxGrade'].value).subscribe((res)=>{
          this.recipes = res;
          console.log(res)
          this.showRecipes = true;
        })
      }
      break;
      case "best":{
        this.reportService.getBestGradedRecipes().subscribe((res)=>{
          this.recipes = res;
          console.log(res)
          this.showRecipes = true;
        })
      }
      break;
      case "bestLastMonth":{
        this.reportService.getBestGradedLastMonthRecipes().subscribe((res)=>{
          this.recipes = res;
          console.log(res)
          this.showRecipes = true;
        })
      }
      break;
      case "dateRange":{
        let f = new Intl.DateTimeFormat('fr-CA');
        this.reportService.getInDateRangeRecipes(f.format(this.range.controls['start'].value), f.format(this.range.controls['end'].value)).subscribe((res)=>{
          this.recipeAvgs = res;
          console.log(res)
          this.showRecipes = true;
        })
      }

    }
  }
}
