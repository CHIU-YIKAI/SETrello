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
        userId:undefined,
        boardName:undefined,
        boardDescription:undefined,
        trelloKey:undefined,
        trelloToken:undefined
      };
      CreateUserBoardData.userId  =  this.UserID.toString();
      CreateUserBoardData.boardName  =  this.NameofBoard.toString();
      CreateUserBoardData.boardDescription = this.DescriptionOfBoard.toString();
      CreateUserBoardData.trelloKey  = this.InputTrelloKey.toString();
      CreateUserBoardData.trelloToken  = this.InputTrelloToken.toString();

      const data = JSON.stringify(CreateUserBoardData);
      this.createtrelloservice.createBoard(data).subscribe(
        request => {
          this.datas = request;
          console.log(this.datas);
  //         this.boardImportMsg = this.datas.status;
          this.boardImportMsg = "success"
          if (this.boardImportMsg == ""){
            this.ErrorMsg = "something error";
          }
          else if (this.boardImportMsg == "Invalid Key"){
            this.ErrorMsg = "無效的Trello Key，請重新輸入";
            this.InputTrelloKey = '';
          }
          else if (this.boardImportMsg == "Invalid Token"){
            this.ErrorMsg = "無效的Trello Token，請重新輸入";
            this.InputTrelloToken = '';
          }
          else if (this.boardImportMsg == "Invalid Board Name"){
            this.ErrorMsg = "無效的看板名稱，請重新輸入";
            this.NameofBoard= '';
          }
          else{
            console.log("CreateBoardSuccess",this.boardImportMsg);
            this.ErrorMsg = "CreateBoardSuccess"; //temp
            this.router.navigate([this.ProjectOverviewpageurl]);
          }
        }
      );
    }
  }
}
