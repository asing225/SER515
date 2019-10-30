### Before you proceed to execute the test scripts, please make a note of the below:

##### Install Node JS and npm
Install globally on your system from : https://nodejs.org/en/download/
The node package contains npm as well.
The repo contains node-v12.13.0.pkg which is the installation file for 64bit MacOS. You can use the appropriate file from the link above.

##### Install mocha and chai JS

`npm install --global mocha`

`npm install --save-dev mocha`

##### package.json
This file is generated when you download Node JS and npm. It is specific to each system/directory.
It is recommended you use the file generated from the installations instead of the copy on the repository, which is the one generated on the developer's system. The same goes for the Node_modules directory.
Add the below to your package.json file:

`"scripts": {`
    `"test": "mocha"`
  `}`

so that you can successfully execute:

`npm run test`
---

### Setup

Unit Testing  


    | - - - -> node-v12.13.0.pkg

    |

    | - - - -> first.js

    |

    | - - - -> package.json

    |

    | - - - -> node_modules

    |

    |- - - -> test
                |

                |- - - -> firsttest.js

Do not change directory name for "test"

Further testing software documentation:
https://tree.taiga.io/project/mahendrarrao-ser515-fall-2019-team-3/wiki/front-end-testing-software
