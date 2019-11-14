//JS file to inject the toolbox element

/*
 *@title : JS file to inject the toolbox element
 *@Authors : Mahendra Rao, Kushagr Jolly, Anurag Mishra, Amanjot Singh, Akshay Kumar Dileep
 *@Project - SER515 Team 3 "DragOn"

 * @license
 * Visual Blocks Editor
 *
 * Copyright 2018 Google Inc.
 * https://developers.google.com/blockly/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use any of these files except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var count = 1
var selections = []; 
var solution = [];
var size = document.getElementById("questions").children.length
console.log(size)
size = size - 2
var val = "problem"
var ans = "answer"

	if(count == 1){
		          $('#prev').hide();
		$('#submit').hide();
		        } else if(count > 1){
		          
		          $('#prev').hide();
		          $('#next').show();
		        }
console.log(size);
for(i = 1;i<size;i++){
	var value = val.concat(i);
	$('#'+value+'').hide();
}
for(i=0;i<size;i++){
	var answer = ans.concat(i);
	solution[i] = document.getElementById(answer).getAttribute('value');
}
console.log(selections[count-1])
if (isNaN(selections[count-1])) {
	console.log("chicken")
      
    } 

console.log(selections[0])
function nextQuestion() {
var val = "problem"
	choose();
console.log(size)
// If no user selection, progress is stopped
console.log("lolololol")
console.log(selections[count-1])
    if (selections[count-1] != solution[count-1]) {
      alert('Wrong Answer!');
    } 
else {
      
    	
if(count<size){
	
var value = val.concat(count-1);
console.log(value)
$('#'+value+'').hide();

var value1 = val.concat(count);
console.log(count)
$('#'+value1+'').show();
count = count + 1
console.log(count)
}
if(count == 1){
	          $('#prev').hide();
	$('#submit').hide();
	        } else{
	          
	          $('#prev').show();
	          $('#next').show();
			$('#submit').hide();
	        }
if(count == size){
	$('#prev').show();
	$('#next').hide();
	$('#submit').show();
}
}
}
function calculate() {
	
	choose();
	if (selections[count-1] != solution[count-1]) {
		      alert('Wrong Answer!');
		    } 
		else {
			alert('Quiz completed redirecting to Student Landing Page')
			location.replace("http://localhost:8080/SER515Project_Team3/login")
		}
} 
function choose() {
		var val = "solution"
		var valu = val.concat(count-1);
	    selections[count-1] = document.getElementById(valu).value;
		console.log("goodyear")
		console.log(count)
		console.log(selections[0])
	  }

function prevQuestion() {
	var val = "problem"
		
	if(count>=1){
		count = count - 1
	if(count>=1){
	var value = val.concat(count-1);
	console.log(value)
	$('#'+value+'').show();
	var value1 = val.concat(count);
	$('#'+value1+'').hide();
	}
	else{
		count = count + 1
	}
	}
	if(count <= 1){
		          $('#prev').hide();
					$('#next').show();
		        } else if(count > 1){
		          
		          $('#prev').hide();
		          $('#next').show();
		        }
	
	}
'use strict';
var demoWorkspace = Blockly.inject('blocklyDiv', {
	media : './lib/blockly-master/media/',
	toolbox : document.getElementById('toolbox')
});

Blockly.Xml.domToWorkspace(document.getElementById('startBlocks'),
		demoWorkspace);

function checkCode(x){
	clearConsole();
	document.getElementById("check").innerHTML = "Checking...";
	var array = x.replace("window.alert(","").replace(");","").replace(/\s+/g, '');
	//array = array.replace(");","").replace(/\s+/g, '');
	//array = array.replace(/\s+/g, '');
	array = array.split('+').join(',').split('-').join(',').split('(').join(',').split(')').join(',').split(',');
	console.log(array);
	for(var c=0; c<array.length; c++){
			if(array[c] == 0 & array[c] != ''){
				document.getElementById("error").innerHTML += "Blocks aren't correct"
				return 0;
			}
	}
	document.getElementById("error").innerHTML += "Blocks are correct"
	return 1;
}

function runCode() {
	// Generate JavaScript code and run it.

	var answer;
	window.LoopTrap = 1000;
	Blockly.JavaScript.INFINITE_LOOP_TRAP = 'if (--window.LoopTrap == 0) throw "Infinite loop.";\n';
	var code = Blockly.JavaScript.workspaceToCode(demoWorkspace);
	answer = checkCode(code);
	if(answer != 0){
		Blockly.JavaScript.INFINITE_LOOP_TRAP = null;
		try {
			if ((eval(code.substring(code.indexOf("(") + 1, code.length - 3))) >= 0)
				document.getElementById("console").innerHTML += code.substring(code.indexOf("(") + 1, code.length - 3)+ ' = ' + eval(code.substring(code.indexOf("(") + 1, code.length - 3));
			else
				document.getElementById("error").innerHTML += "You can't subtract a larger number from a smaller one!"
		} catch (e) {
			alert(e);
		}
	}
}

function clearConsole() {
	//to clear the console when the button is clicked
  document.getElementById("console").innerHTML="";
	document.getElementById("check").innerHTML="";
	document.getElementById("error").innerHTML="";
}
