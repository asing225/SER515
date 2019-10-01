//JS file to inject the toolbox element
'use strict';
  var demoWorkspace = Blockly.inject('blocklyDiv',
      {media: './lib/blockly-master/media/',
       toolbox: document.getElementById('toolbox')});
