import { Component, OnInit } from '@angular/core';
import { Contact } from 'src/app/models/contact';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contactData: Contact=new Contact();
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  postComment(){
    this.userService.postComment(this.contactData)
    .subscribe(
      res=>{console.log(res),location.reload();},
      error=>{console.log(error)}
    )
  }


}
