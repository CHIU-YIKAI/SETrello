import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trello-board',
  templateUrl: './trello-board.component.html',
  styleUrls: ['./trello-board.component.css']
})
export class TrelloBoardComponent implements OnInit {

  Trello_Board: string = "board_1"
  ListTrackButton: string = "";
  readonly list1 = [{card_id:"1",card_title : "PEP撰寫", card_content:"奕凱 : S1-Project Planning and Milestone Checking、S2-Personnel\n慕全 : S3-Resources、S5-Risk Management" },{card_id:"2",card_title : "SRS撰寫", card_content:"奕凱 : S1-Introduction、S2-System" },{card_id:"3",card_title : "SDD撰寫", card_content:"0、1、2 YK" }];
  readonly list2 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly list3 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly list4 = [{card_id:"1",card_title : "PEP撰寫", card_content:"test1516515616" },{card_id:"2",card_title : "SRS撰寫", card_content:"test" },{card_id:"3",card_title : "SDD撰寫", card_content:"test" }];
  readonly board = [{list_name: "文件撰寫", list_data: this.list1, list_ID: "jasoijfokwqemfoijnvoi"},{list_name:"程式撰寫",list_data:this.list2, list_ID: "uvpoizxvaslkmfw"},{list_name:"進度報告",list_data:this.list3, list_ID:"xcmvwpoijefvzdajqs"},{list_name:"問題討論",list_data:this.list4, list_ID: "1x6c1v6s41f61d68f4s"}];
  board_name
  test = ["123","456","789"]
  name = "jason"

  constructor() { }

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.getListTrack();


  }

  getListTrack() {
    //const list_data = JSON.stringify(this.board);

    //TODO
  }


  creatNewCard(value:any){
    console.log(value)
    var name = prompt("卡片名稱");
    var Data = prompt("卡片內容");

  }

  deleteCard(boardID: any, cardID :any){
    console.log(boardID)
    console.log(cardID)
  }
}
