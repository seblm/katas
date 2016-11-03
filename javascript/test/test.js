var assert = require("assert");
var CoffeeMachine = require("../index.js");

describe("Coffee machine", function () {
    it("should do something", function () {
        var coffeeMachine = new CoffeeMachine();

        var result = coffeeMachine.doSomething();

        assert.equal(result, "result");
    });
});
