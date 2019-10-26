var assert = require('../node_modules/chai').assert;
var setup_check = require('../setup_check');

describe('Setup', function () {
  it('This functions checks if the test setup is working correctly with the required dependencies', function () {
    assert.equal(setup_check(), 'hello world')
  });
});
