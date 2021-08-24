import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { UserService } from 'src/app/services/user.service';
import { Answer } from 'src/app/models/answer';

@Component({
  selector: 'app-question-details',
  templateUrl: './question-details.component.html',
  styleUrls: ['./question-details.component.css']
})
export class QuestionDetailsComponent implements OnInit {
  params:any;
  question: Question = new Question;
  data:any;
  answer:any;
  postans: Answer=new Answer();
  users:any;
  subject:any;
  constructor(private userService: UserService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {this.params=params;console.log(params.id+params.que_id) },
    );
    this.userService.getQue()
    .subscribe(
      res=>{console.log(res),this.data=res},
      error=>{console.log(error)}
    )
    this.getAns();
    this.getUsers();
  }
  getAns(){
    this.userService.getAns()
    .subscribe(
      data=>{console.log(data),
        this.answer=data
    },
      error=>{console.log(error)}
    )
  }
  postAns(){
    this.postans.question=Number(this.params.que_id);
    this.users.forEach((element:any) => {
      if(element.email==this.params.id){
        this.postans.author=element.firstname+' '+element.lastname;
      }
    });
    console.log(this.postans);
    this.userService.postAns(this.postans)
    .subscribe(
      res=>{console.log(res), location.reload();},
      error=>{console.log(error)}
    )
  }
  getUsers(){
    this.userService.getUsers()
    .subscribe(
      users=>{this.users=users,console.log(users)},
      error=>{console.log(error)}
    )
  }
  calculateDiff(dateSent:Date){
    let currentDate = new Date();
    dateSent = new Date(dateSent);
 
     return Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) ) /(1000 * 60 * 60 * 24));
   }
}
