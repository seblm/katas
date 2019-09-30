interface InternalFrame {
    pins(pins: number)
    score()
}

class Strike implements InternalFrame {
    pins1: undefined | number;
    pins2: undefined | number;
    pins(pins: number) {
        if (this.pins1 == undefined) {
            this.pins1 = pins;
        } else if (this.pins2 == undefined) {
            this.pins2 = pins;
        }
    }
    score() {
        const score = 10;
        if (this.pins1 == undefined) {
            return score;
        } else if (this.pins2 == undefined) {
            return score + this.pins1;
        } else {
            return score + this.pins1 + this.pins2;
        }
    }
}

class Spare implements InternalFrame {
    pins1: undefined | number;
    constructor() {
        this.pins1 = undefined;
    }
    pins(pins: number) {
        if (this.pins1 == undefined) {
            this.pins1 = pins;
        }
    }
    score() {
        if (this.pins1 == undefined) {
            return 0;
        } else {
            return this.pins1;
        }
    }
}

class Simple implements InternalFrame {
    score_: number;
    constructor(score: number) {
        this.score_ = score;
    }
    pins(pins: number) {
    }
    score() {
        return this.score_;
    }
}

class Frame {
    pins1: undefined | number;
    internalFrame: undefined | InternalFrame;
    constructor(pins: number) {
        if (pins == 10) {
            this.internalFrame = new Strike();
        } else {
            this.pins1 = pins;
        }
    }
    // returns true if pins are consumed ; false if Frame is already full
    pins(pins: number) {
        if (this.internalFrame != undefined) {
            this.internalFrame.pins(pins);
            return false;
        } else if (this.pins1 + pins == 10) {
            this.internalFrame = new Spare();
            return true;
        } else {
            this.internalFrame = new Simple(this.pins1 + pins);
            return true;
        }
    }

}

export class Bowling {
    frames: Array<Frame>;
    score() {
        return 0;
    }
    pins(pins: number) {
            let newFrameRequired = false;
            for (let frame of this.frames) {
                newFrameRequired = frame.pins(pins);
            }
            if (newFrameRequired) {
                this.frames.push(new Frame(pins))
            }
    }
}