%%%-------------------------------------------------------------------
%%% @author aolarte
%%% @copyright (C) 2017, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 17. Jun 2017 9:11 PM
%%%-------------------------------------------------------------------
-module(simple_bot).
-author("aolarte").

%% API
-export([chat/0,start/0]).

start() ->
  spawn(simple_bot,chat,[]).

chat() ->
  receive
    finished ->
      io:format("Chat bot finished~n", []);
    {send, Text} ->
      io:format("Hello ~p !!", [Text]),
      chat()
  end.
