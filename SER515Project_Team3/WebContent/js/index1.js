/**
 * This file contains the javascript code for the application header
 * 
 * @author - akshaykumardileep
 * @date - 10/07/2019
 * 
 */
count = 1

  $(document).ready(function () {
    $('#btAdd').click(function () {
        AddText();
    });
    function AddText() {
      if(count<=10){
        count = count + 1
        $('<div id= "head'+count+'"><div id="heading'+count+'"><h3>Question' +count+'</h3></div><input type="text" placeholder="Question'+count+'" name="Question'+count+'"></br><textarea placeholder="Solution" name="Solution'+count+'" type="text"></textarea><hr></div></div></div>').appendTo('#wrapper1');
    }
  }
    $('#btRemove').click(function () {
      RemoveText();
});
    function RemoveText() {
      var val = "head"
      var value = val.concat(count);
      console.log(value);
      if(count>1){
      var element = document.getElementById(value);
      document.getElementById("wrapper1").removeChild(element)
      count = count - 1;
    }
  }
});