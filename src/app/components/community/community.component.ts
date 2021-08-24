import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from 'src/app/models/answer';
import { Category } from 'src/app/models/category';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {
  dateToday: number = Date.now();
  users: any;
  data: any;
  params: any;
  count: any = 0;
  questions: any;
  quecount: any = 0;
  answer: any;
  anscount: any = 0;
  Searched = false;
  title = '';
  date:any;
  searchque: any;
  msg = "No results found!!!";
  searchquecount: any = 0;
  c = 0;
  category:any;
  categoryData: Category=new Category();

  constructor(private userService: UserService, private _router: Router, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.router.queryParams.subscribe(
      params => { this.username(params), console.log(params.id) },
    );
    this.getQue();
    this.getAns();
    this.getUsersCount()
    this.getCategory();
  }

  username(params: any) {
    this.params = params
  }

  getUsersCount() {
    this.userService.getUserCount()
      .subscribe(
        data => { this.count=data,console.log(data)},
        error => { console.log(error) }
      )
  }

  getQue() {
    this.userService.getQue()
      .subscribe(
        res => {
          this.questions = res;
          this.questions.forEach((element: any) => {
            this.quecount++;
          });
        },
        error => {
          console.log(error);
        }
      )
  }

  getAns() {
    this.userService.getAns()
      .subscribe(
        data => {
          console.log(data),
          this.answer = data
        },
        error => { console.log(error) }
      )
  }
  getSearchQue() {
    if (this.title != '' && this.title != null) {
      this.Searched = true;
      this.c = 0;
    }
    else {
      this.Searched = false;
      this.c;
    }
    console.log(this.title);
    this.userService.findQueByTitle(this.title)
      .subscribe(
        searchque => {
          console.log(searchque),
            this.searchque = searchque;
          if (this.searchque == null) {
            this.c++;
            console.log(this.c);
            console.log(this.msg);
          }
          this.searchque.forEach((element: any) => {
            this.searchquecount++;
          });
          if (this.searchquecount == this.quecount) {
            this.Searched = false;
          }
        },
        error => { console.log(error) }
      )
  }

   ansCount(id:Number){
    this.anscount=0;
    this.answer.forEach((element:any) => {
      if(element.question==id){
        this.anscount=this.anscount+1;
      }
    });
    return this.anscount;
  }
  calculateDiff(dateSent:Date){
    let currentDate = new Date();
    dateSent = new Date(dateSent);
 
     return Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) ) /(1000 * 60 * 60 * 24));
   }
   getCategory(){
     this.userService.getCategory()
     .subscribe(
       data=>{this.category=data},
       error=>{console.log(error)}
     )
   }
   postCategory(){
    this.userService.postCategory(this.categoryData)
    .subscribe(
      data=>{console.log(data),location.reload()},
      error=>{console.log(error)}
    ) 
  }
  
}
