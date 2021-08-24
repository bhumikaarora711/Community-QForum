import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  users: any;
  data:any;
  params:any;
  count: any = 0;
  questions :any;
  quecount: any = 0;
  constructor(private userService: UserService,private _router: Router, private router: ActivatedRoute) { }
  
  ngOnInit(): void {
    this.router.queryParams.subscribe(
      params => { this.username(params),console.log(params.id) },
    );
    this.getUsersCount();
    this.getQueCount();
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

  getQueCount(){
    this.userService.getQuestionCount()
    .subscribe(
      res=>{this.quecount=res},
      error=>{console.log(error); }
    )
  }
}
