import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn=false;
  users: any;
  data:any;
  constructor(private userService: UserService,public _router: Router, private router: ActivatedRoute) { }
  params:any;
  ngOnInit(): void {
    this.isLoggedIn=this.userService.isLoggedIn();
    this.router.queryParams.subscribe(
      params => { this.addusername(params),console.log(params.id) },
    );
    this.userService.getUsers()
    .subscribe(
      data=>{
        this.addUsers(data);
      },
      error=>{console.log(error)}
    )
  }
  addusername(params: any) {
    this.params = params
  }

  getUsers(){
    this.userService.getUsers()
    .subscribe(
      data=>{
        this.addUsers(data);
      },
      error=>{console.log(error)}
    )
  }
  addUsers(data:any){
    this.users=data;
  }
  
  logout(){
    this.userService.logout();
    location.reload();
   window.location.href="/home";
  }

}
