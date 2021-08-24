import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-questions',
  templateUrl: './user-questions.component.html',
  styleUrls: ['./user-questions.component.css']
})
export class UserQuestionsComponent implements OnInit {

  users: any;
  data:any;
  params:any;
  count: any = 0;
  questions :any;
  quecount: any = 0;
  answer:any;
  flag:any=0;
  constructor(private userService: UserService,private _router: Router, private router: ActivatedRoute) { }
  
  ngOnInit(): void {
    this.router.queryParams.subscribe(
      params => { this.username(params),console.log(params.id) },
    );
    this.getUsers();
    this.getQue();
    this.getAns();
  }

  username(params: any) {
    this.params = params
  }

  getUsers(){
    this.userService.getUsers()
    .subscribe(
      data=>{
        this.addUser(data);
      },
      error=>{console.log(error)}
    )
  }
  
  addUser(data:any){
    data.forEach((element: any) => {
      this.count++;
    });
    this.users=data;
  }

  getQue(){
    this.userService.getQue()
    .subscribe(
      res=>{
        this.questions=res;
        console.log(res)
        this.questions.forEach((element: any) => {
          this.quecount++;
        });
        this.questions.forEach((element: any) => {
          if(element.author==this.params.author){
            this.flag++;
          }
        });
      },
      error=>{
        console.log(error);
      }
    )
  }

  getAns(){
    this.userService.getAns()
    .subscribe(
      res=>{this.answer=res;},
      error=>{console.log(error);}
    )
  }

  closedQue(id:any){
    this.userService.closedQue(id)
    .subscribe(
      data=>{location.reload();}
    );
  }

  markAnsCorrect(id:any){
    this.userService.markAnsCorrect(id)
    .subscribe(
      data=>{console.log(data),location.reload()},
      error=>{console.log(error)}
    )
  }
}
