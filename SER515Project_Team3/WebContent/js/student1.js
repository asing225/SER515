//JS file to inject the toolbox element
'use strict';
  var demoWorkspace = Blockly.inject('blocklyDiv',
      {media: './lib/blockly-master/media/',
       toolbox: document.getElementById('toolbox')});

       Blockly.Xml.domToWorkspace(document.getElementById('startBlocks'),
                                  demoWorkspace);


       function runCode() {
         // Generate JavaScript code and run it.

         var answer;
         window.LoopTrap = 1000;
         Blockly.JavaScript.INFINITE_LOOP_TRAP =
             'if (--window.LoopTrap == 0) throw "Infinite loop.";\n';
         var code = Blockly.JavaScript.workspaceToCode(demoWorkspace);
         Blockly.JavaScript.INFINITE_LOOP_TRAP = null;
         try {
           eval(code);
         } catch (e) {
           alert(e);
           //document.getElementById("textbox3").value = answer;
         }
       }
