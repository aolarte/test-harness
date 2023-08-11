import { ConsoleRunner } from "./console";

const runner = new ConsoleRunner();

runner.run().then( result => {
    console.log('Runner is Done: ' + result)
})
