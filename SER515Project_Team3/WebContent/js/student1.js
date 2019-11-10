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
	//console.log(x);
	var array = x.replace("window.alert(","").replace(");","").replace(/\s+/g, '');
	//array = array.replace(");","").replace(/\s+/g, '');
	//array = array.replace(/\s+/g, '');
	array = array.split('+').join(',').split('(').join(',').split(')').join(',').split(';').join(',').split(',');
	console.log(array);
	//document.getElementById("console").innerHTML += array;
	for(var c=0; c<array.length; c++){
			if(array[c] == 0 & array[c] != ''){
				document.getElementById("error1").innerHTML += "Blocks aren't correct"
				return 0;
			}
			var eq = array[c];
			if(eq[(eq.length)-1] == '-')
				eq = eq.substring(0,(eq.length-1));
			console.log(eq);
			if (eval(eq) < 0){
				document.getElementById("error1").innerHTML += "Blocks are correct"
				document.getElementById("error2").innerHTML += "You can't subtract a larger number from a smaller one!"
				return 0;
			}
	}
	document.getElementById("error1").innerHTML += "Blocks are correct"
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
			if ((eval(code)) >= 0)
				document.getElementById("console").innerHTML = code.substring(0, code.length - 2) + ' = ' + eval(code);
				//document.getElementById("console").innerHTML += code.substring(code.indexOf("(") + 1, code.length - 2)+ ' = ' + eval(code.substring(code.indexOf("(") + 1, code.length - 2));
			else
				document.getElementById("error2").innerHTML += "You can't subtract a larger number from a smaller one!"
		} catch (e) {
			alert(e);
		}
	}
}

function clearConsole() {
	//to clear the console when the button is clicked
  document.getElementById("console").innerHTML="";
	document.getElementById("check").innerHTML="";
	document.getElementById("error1").innerHTML="";
	document.getElementById("error2").innerHTML="";
}
