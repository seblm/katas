interface InternalFrame {
    pins(pins: number): void

    score(): number
}

class Strike implements InternalFrame {
    nextFirstPins: undefined | number;
    nextSecondPins: undefined | number;

    constructor() {
        console.log("new Strike()")
    }

    pins(pins: number): void {
        if (this.nextFirstPins == undefined) {
            this.nextFirstPins = pins;
        } else if (this.nextSecondPins == undefined) {
            this.nextSecondPins = pins;
        }
    }

    score(): number {
        const score = 10;
        if (this.nextFirstPins == undefined) {
            return score;
        } else if (this.nextSecondPins == undefined) {
            return score + this.nextFirstPins;
        } else {
            return score + this.nextFirstPins + this.nextSecondPins;
        }
    }
}

class Spare implements InternalFrame {
    nextPin: undefined | number;

    constructor() {
        console.log("new Spare()")
        this.nextPin = undefined;
    }

    pins(pins: number): void {
        if (this.nextPin == undefined) {
            this.nextPin = pins;
        }
    }

    score(): number {
        if (this.nextPin == undefined) {
            return 10;
        } else {
            return 10 + this.nextPin;
        }
    }
}

class Simple implements InternalFrame {
    score_: number;

    constructor(score: number) {
        console.log("new Simple(" + score + ")");
        this.score_ = score;
    }

    pins(pins: number): void {
    }

    score(): number {
        return this.score_;
    }
}

class Frame {
    pins1: undefined | number;
    internalFrame: undefined | InternalFrame;

    pins(pins: number): void {
        if (this.internalFrame != undefined) {
            this.internalFrame.pins(pins);
        } else if (pins == 10) {
            this.internalFrame = new Strike();
        } else if (this.pins1 == undefined) {
            this.pins1 = pins;
        } else if (this.pins1 + pins == 10) {
            this.internalFrame = new Spare();
        } else {
            this.internalFrame = new Simple(this.pins1 + pins);
        }
    }

    full(): boolean {
        return this.internalFrame != undefined
    }

    score(): number {
        if (this.internalFrame == undefined) {
            console.log("Frame.score(): this.internalFrame is undefined; this.pins1=" + this.pins1);
            return this.pins1;
        } else {
            console.log("Frame.score(): this.internalFrame defined; this.internalFrame.score()=" + this.internalFrame.score());
            return this.internalFrame.score();
        }
    }

}

export class Bowling {
    frames: Array<Frame> = [];

    score(): number {
        let score: number = 0;
        for (let frame of this.frames) {
            const score1 = frame.score();
            console.log("frame.score=" + score1);
            score += score1
        }
        return score;
    }

    pins(pins: number): void {
        for (let frame of this.frames) {
            frame.pins(pins);
        }
        if (this.frames.length == 0 || this.frames[this.frames.length - 1].full()) {
            let newFrame = new Frame();
            this.frames.push(newFrame);
            newFrame.pins(pins);
            if (newFrame.full()) {
                this.frames.push(new Frame())
            }
        }
    }
}