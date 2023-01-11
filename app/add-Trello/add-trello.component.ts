import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { CreateTrelloService } from './create-trello.service';

@Component({
  selector: 'app-add-trello',
  templateUrl: './add-trello.component.html',
  styleUrls: ['./add-trello.component.css']
})
export class AddTrelloComponent implements OnInit {
  boardImportMsg = '';

  isSuccessful = false;
  ErrorMsg = '';

  datas: any;
  NameofBoard = '';
  DescriptionOfBoard= '';
  UserID = '';
  IDofBoard:'';
  ProjectOverviewpageurl = "choose-project";

  InputTrelloKey = '';
  InputTrelloToken = '';

  constructor(private router: Router, private createtrelloservice: CreateTrelloService ,private activerouter:ActivatedRoute ) {

  }
  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.UserID = window.sessionStorage.getItem('UserID');
  }
  CreateProject() {
    if (this.NameofBoard == '')
      this.ErrorMsg = "Board Name不得為空";
    else if (this.InputTrelloKey == '')
      this.ErrorMsg = "Trello Key不得為空";
    else if (this.InputTrelloToken == '')
      this.ErrorMsg = "Trello Token不得為空";
    else{
      this.ErrorMsg = '';
      const CreateUserBoardData = {
        UserID:undefined,
        BoardName:undefined,
        description:undefined,
        UserKey:undefined,
        UserToken:undefined
      };
      CreateUserBoardData.UserID  =  this.UserID.toString();
      CreateUserBoardData.BoardName  =  this.NameofBoard.toString();
      CreateUserBoardData.description = this.DescriptionOfBoard.toString();
      CreateUserBoardData.UserKey  = this.InputTrelloKey.toString();
      CreateUserBoardData.UserToken  = this.InputTrelloToken.toString();

      const data = JSON.stringify(CreateUserBoardData);
      this.createtrelloservice.createBoard(data).subscribe(
        request => {
          this.datas = request;
          console.log(this.datas);
          this.isSuccessful = this.datas.isSuccessful;
          this.boardImportMsg = this.datas.status;
          // this.boardImportMsg = "success"
          if (this.isSuccessful){
            console.log("CreateBoardSuccess",this.boardImportMsg);
            this.ErrorMsg = "CreateBoardSuccess"; //temp
            this.router.navigate([this.ProjectOverviewpageurl]);
          }
          else if (this.boardImportMsg == "invalid credential"){
            this.ErrorMsg = "無效的Trello APIKey或Token，請重新輸入";
            this.InputTrelloKey = '';
            this.InputTrelloToken = '';
          }
          else if (this.boardImportMsg == "Invalid Token"){
            this.ErrorMsg = "無效的Trello Token，請重新輸入";
            this.InputTrelloToken = '';
          }
          else if (this.boardImportMsg == "No matching Board Name"){
            this.ErrorMsg = "無效的看板名稱，請重新輸入";
            this.NameofBoard= '';
          }
          else{
            this.ErrorMsg = this.boardImportMsg;
          }
        }
      );
    }
  }
}
