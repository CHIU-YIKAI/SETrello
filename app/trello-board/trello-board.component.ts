import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { TrelloBoardService } from './trello-board.service';

@Component({
  selector: 'app-trello-board',
  templateUrl: './trello-board.component.html',
  styleUrls: ['./trello-board.component.css']
})
export class TrelloBoardComponent implements OnInit {

  Trello_Board_Name: string = "board_1"

  Trello_Board_ID: string = "jhlkvjoiaj4oiewjfomweflo"
  ListTrackButton: string = "";
  UserID = "";
  projectID = "";
  BoardID="";
  cardID="";
  projectName = "";
  lists :any ;
  trelloList: any;
  status: any;
  listID="";
  listId = "";
  listName="";
  cardId = "";
  cardName="";
  cardDescription = "";
  cards = [];
  card_txt: string =  "Click";

  readonly list1 = [{card_id:"1",card_title : "PEP撰寫", card_content:"奕凱 : S1-Project Planning and Milestone Checking、S2-Personnel\n慕全 : S3-Resources、S5-Risk Management" },{card_id:"2",card_title : "SRS撰寫", card_content:"奕凱 : S1-Introduction、S2-System" },{card_id:"3",card_title : "SDD撰寫", card_content:"0、1、2 YK" }];
  readonly list2 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly list3 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly list4 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly board = [{list_name: "文件撰寫", list_data: this.list1, list_ID: "jasoijfokwqemfoijnvoi"},{list_name:"程式撰寫",list_data:this.list2, list_ID: "uvpoizxvaslkmfw"},{list_name:"進度報告",list_data:this.list3, list_ID:"xcmvwpoijefvzdajqs"},{list_name:"問題討論",list_data:this.list4, list_ID: "1x6c1v6s41f61d68f4s"}];



  constructor(private router: Router, private TrelloBoardService: TrelloBoardService ,private activerouter:ActivatedRoute ) { }

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.UserID = window.sessionStorage.getItem('UserID');
    this.projectID = window.sessionStorage.getItem("ChosenProjectID");
    this.projectName = window.sessionStorage.getItem('projectName');
    console.log(this.UserID,this.projectID,this.projectName);
    this.getListTrack();
  }

  reload()
  {
    console.log("reload");
    this.getListTrack();
  }

  getListTrack() {
    const RequestData = {
      userId:undefined,
      boardId:undefined
    };
    this.BoardID = window.sessionStorage.getItem('boardId');

    RequestData.userId  =  this.UserID.toString();
    RequestData.boardId  =  this.BoardID.toString();
    const data = JSON.stringify(RequestData);
      this.TrelloBoardService.RequestTrelloBoard(data).subscribe(
        request => {
          this.lists = request;
          for(let trelloList of this.lists){
            console.log(trelloList.listName);
          }
          console.log(this.lists);


        }
      );

  }


  createNewCard(listID:any){
    //const data = JSON.stringify(CreateUserBoardData);
    this.listID = listID;
    console.log(listID)
    var name = prompt("卡片名稱");
    var Data = prompt("卡片內容");
    console.log(name,Data);
    //TODO
    const CreatCard = {
      userId:undefined,
      listId:undefined,
      cardName:undefined,
      desc:undefined

    };
    CreatCard.userId  =  this.UserID.toString();
    CreatCard.listId = listID.toString();
    CreatCard.cardName  =  name.toString();
    CreatCard.desc  =  Data.toString();
    const data = JSON.stringify(CreatCard);
    console.log(data);
    this.TrelloBoardService.addTrelloCard(data).subscribe(
      request => {
        this.status = request;

        console.log(this.status);
      }
    );

    window.location.reload();
  }

  BackToCHooseProject(){
    this.router.navigate(['choose-project']);
  }
  deleteCard(cardID :any){
    console.log(cardID);
    var check = confirm("確定要刪除?");
    console.log(check);
    if(check){
      //TODO
      const RequestData = {
        userId:undefined,
        cardId:undefined
      };
      this.cardID = cardID;
      RequestData.userId  =  this.UserID.toString();
      RequestData.cardId  =  cardID.toString();
      console.log(RequestData);
      const data = JSON.stringify(RequestData);
      this.TrelloBoardService.delTrelloCard(data).subscribe(
        request => {
          this.status = request;

          console.log(this.status);
        }
      );
      window.location.reload();
    }
  }

  archiveList(listID: any){
    console.log(listID);
    var check = confirm("確定要封存?");
    console.log(check);
    if(check){
      //TODO

      window.location.reload();
    }
  }

  createNewList(){
    console.log(this.Trello_Board_ID);
    var name = prompt("清單名稱");
    console.log(name);
    //TODO
    const RequestData = {
      userId:undefined,
      boardId:undefined,
      listName: undefined
    };

    RequestData.userId  =  this.UserID.toString();
    RequestData.boardId  =  this.BoardID.toString();
    RequestData.listName  =  name.toString();
    const data = JSON.stringify(RequestData);
    this.TrelloBoardService.addTrelloList(data).subscribe(
      request => {
        this.status = request;

        console.log(this.status);
      }
    );
    window.location.reload();
  }




}
