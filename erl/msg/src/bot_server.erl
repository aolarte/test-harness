%%%----------------------------------------------------------------------------
%%% @doc An OTP gen_server example
%%% @author Hans Christian v. Stockhausen <hc at vst.io>
%%% @end
%%%----------------------------------------------------------------------------

-module(bot_server).         % Nothing new in this section except for the
                               %  next line where we tell the compiler that
-include_lib("eunit/include/eunit.hrl").
-behaviour(gen_server).        %  this module implements the gen_server
                               %  behaviour. The compiler will warn us if
-define(SERVER, ?MODULE).      %  we do not provide all callback functions
                               %  the behaviour announces. It knows what
-record(state, {count, subs}).       %  functions to expect by calling
                               %  gen_server:behaviour_info(callbacks). Try it.
%%-----------------------------------------------------------------------------
%% API Function Exports
%%-----------------------------------------------------------------------------

-export([                      % Here we define our API functions as before
  start_link/0,                % - starts and links the process in one step
  send/1
%%  stop/0.                      % - stops it
]).

%% ---------------------------------------------------------------------------
%% gen_server Function Exports
%% ---------------------------------------------------------------------------

-export([                      % The behaviour callbacks
  init/1,                      % - initializes our process
  handle_call/3,               % - handles synchronous calls (with response)
  handle_cast/2,               % - handles asynchronous calls  (no response)
  handle_info/2,               % - handles out of band messages (sent with !)
  terminate/2,                 % - is called on shut-down
  code_change/3]).             % - called to handle code changes

%% ---------------------------------------------------------------------------
%% API Function Definitions
%% ---------------------------------------------------------------------------

start_link() ->                % start_link spawns and links to a new
    io:fwrite("Start Bot Server!~n"),
    gen_server:start_link(     %  process in one atomic step. The parameters:
      {local, ?SERVER},        %  - name to register the process under locally
      ?MODULE,                 %  - the module to find the init/1 callback in
      [],                      %  - what parameters to pass to init/1
      []).                     %  - additional options to start_link
%%
%%stop() ->                      % Note that we do not use ! anymore. Instead
%%    gen_server:cast(           %  we use cast to send a message asynch. to
%%      ?SERVER,                 %  the registered name. It is asynchronous
%%      stop).                   %  because we do not expect a response.


send(Text) ->
  gen_server:cast(
    bot_server,
    {send,Text}).

%% ---------------------------------------------------------------------------
%% gen_server Function Definitions
%% ---------------------------------------------------------------------------

init([]) ->                    % these are the behaviour callbacks. init/1 is
    {ok, #state{count=0, subs=#{}}}.     % called in response to gen_server:start_link/4
                               % and we are expected to initialize state.


handle_call(Request, _, State) ->
    {reply,
      Request,                    % here we synchronously respond with Count
     State     % and also update state
    }.



handle_cast(stop, State) ->    % this is the first handle_case clause that
    {stop,                     % deals with the stop atom. We instruct the
     normal,                   % gen_server to stop normally and return
     State                     % the current State unchanged.
    };                         % Note: the semicolon here....

handle_cast({send, Text}, State) ->    % this is the first handle_case clause that
  io:format("Server bot says: Hello ~p !!", [Text]),
  {noreply,State}.



handle_info(Info, State) ->      % handle_info deals with out-of-band msgs, ie
    error_logger:info_msg("~p~n", [Info]), % msgs that weren't sent via cast
    {noreply, State}.          % or call. Here we simply log such messages.

terminate(_Reason, _State) ->  % terminate is invoked by the gen_server
    error_logger:info_msg("terminating~n"), % container on shutdown.
    ok.                        % we log it and acknowledge with ok.

code_change(_OldVsn, State, _Extra) -> % called during release up/down-
    {ok, State}.               % grade to update internal state.

%% ------------------------------------------------------------------
%% Internal Function Definitions
%% ------------------------------------------------------------------

% we don't have any.

%% ------------------------------------------------------------------
%% Unit Tests
%% ------------------------------------------------------------------
basic_test_() ->
       fun () -> ?assert(1 + 1 =:= 2) end.