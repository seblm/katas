import {Bowling} from "../src/Bowling";

const assert = require('assert');

describe('Bowling', function() {
    it('score start at 0', function() {
        const bowling = new Bowling();
        assert.strictEqual(bowling.score(), 0);
    });
    it('score one throw', function() {
        const bowling = new Bowling();
        bowling.pins(3);
        assert.strictEqual(bowling.score(), 3);
    });
    it('score one simple frame', function() {
        const bowling = new Bowling();
        bowling.pins(3);
        bowling.pins(4);
        assert.strictEqual(bowling.score(), 7);
    });
});
