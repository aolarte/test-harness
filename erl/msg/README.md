msg
=====

An OTP application

Build
-----

    $ rebar3 compile


Unit tests
----------

    $ rebar3 eunit

Execute
-------

    $ cd _build\default\lib\msg\ebin
    $ erl
    Eshell V8.2  (abort with ^G)
    1> application:start(msg).
    Start App!
    Start Supervisor!
    Supervisor Init!
    ok
    2> supervisor:start_child(msg_sup,[]).
    Start Server!
    {ok,<0.62.0>}
    3> msg_server:get_count().
    0
    4> msg_server:get_count().
    1
    5> msg_server:say_hello().
    Hello
    ok
    6> application:stop(msg).
    Stop App!
    ok
    7>
    =INFO REPORT==== 26-May-2017::12:39:49 ===
        application: msg
        exited: stopped
        type: temporary
  
    

Simple bot
----------

    4> Pid = simple_bot:start().
    <0.64.0>
    5> msg_server:register("simple", Pid).
    Register
    ok
    6> msg_server:send("simple", "hello").
    
   
    
Pool App
--------

This is the non OTP version in the pool directory:

    C:\Users\aolarte\per\erl\pool>erl
    Eshell V8.2  (abort with ^G)
    1> ppool:start_link().
    {ok,<0.57.0>}
    2> ppool:start_pool(nagger, 2, {ppool_nagger, start_link, []}).
    {ok,<0.59.0>}
    3> ppool:run(nagger, ["finish the chapter!", 10000, 10, self()]).
    {ok,<0.63.0>}
    4> flush().
    Shell got {<0.63.0>,"finish the chapter!"}
    Shell got {<0.63.0>,"finish the chapter!"}
    Shell got {<0.63.0>,"finish the chapter!"}
    Shell got {<0.63.0>,"finish the chapter!"}
    ok
    5>