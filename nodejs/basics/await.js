function doAlert(res) {
    console.log("Value: " + res);
}

async function firstAsync() {
    return 27;
}
  
function test1() {
    firstAsync().then(doAlert);    
}


async function test2() {
    //Using await is only supported inside an async function.
    doAlert(await (firstAsync())); 
}


test1();
test2();
