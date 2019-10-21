# Changes made to original library

## Removed folder /blockly-master/demos
This contained demo Blockly applications which were not useful to our project.

## Added custom JS files in blockly-master/blocks
The blockly-master/blocks/math.js file has all mathematical operators.
Custom file was added to remove some of them for Student 1 profile.

## Guide to blockly-master/blocks
This directory contains the scripts for all the blocks that can be used in the sandbox by default(1).
We can customize any of the scripts that are in this directory to suit our needs. They are:
### math.js
### colour.js"
### math_student1.js"
### logic.js"
### loops.js"
### list.js"
### procedures.js"
### text.js"
### variables_dynamic.js"
### variables.js"

(1) By default, Blockly plugs blocks_compressed.js which is generated using a python script to
compress all the scripts in "blocks" into a single script file. For the project, we have not plugged the compressed file in.
Instead we have made plugged in each individual script in Blocks directory to accommodate the custom JS files.

## Guide to generate blocks_compressed.js from all scripts in blockly-master/blocks
``cd <where you put your github prjoects>``
``git clone https://github.com/google/closure-library.git``
``git clone https://github.com/LLK/scratch-blocks.git``
``cd scratch-blocks``
``python build.py``

## What build.py does
This script generates two versions of Blockly's core files:
###  blockly_compressed.js
###  blockly_uncompressed.js
 The compressed file is a concatenation of all of Blockly's core files which
 have been run through Google's Closure Compiler.  This is done using the
 online API (which takes a few seconds and requires an Internet connection).
 The uncompressed file is a script that loads in each of Blockly's core files
 one by one.  This takes much longer for a browser to load, but is useful
 when debugging code since line numbers are meaningful and variables haven't
 been renamed.  The uncompressed file also allows for a faster development
 cycle since there is no need to rebuild or recompile, just reload.
##
 This script also generates:
###  blocks_compressed.js: The compressed Blockly language blocks.
###   javascript_compressed.js: The compressed JavaScript generator.
###   python_compressed.js: The compressed Python generator.
###   php_compressed.js: The compressed PHP generator.
###  lua_compressed.js: The compressed Lua generator.
###  dart_compressed.js: The compressed Dart generator.
###  msg/js/<LANG>.js for every language <LANG> defined in msg/js/<LANG>.json.
