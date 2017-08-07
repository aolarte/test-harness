%%%-------------------------------------------------------------------
%% @doc msg top level supervisor.
%% @end
%%%-------------------------------------------------------------------

-module(msg_sup).

-behaviour(supervisor).

%% API
-export([start_link/0]).

%% Supervisor callbacks
-export([init/1]).

-define(SERVER, ?MODULE).

%%====================================================================
%% API functions
%%====================================================================

start_link() ->
	io:fwrite("Start Supervisor!~n"),
    supervisor:start_link({local, ?SERVER}, ?MODULE, []).

%%====================================================================
%% Supervisor callbacks
%%====================================================================

%% Child :: {Id,StartFunc,Restart,Shutdown,Type,Modules}
init([]) ->
    io:fwrite("Supervisor Init!~n"),
%%    {ok, { {one_for_all, 0, 1}, []} }.
     RestartStrategy = {simple_one_for_one, 10, 60},
     ChildSpec = {msg_server, {msg_server, start_link, []},
          permanent, brutal_kill, worker, [msg_server]},
     Children = [ChildSpec],
     {ok, {RestartStrategy, Children}}.

%%====================================================================
%% Internal functions
%%====================================================================
