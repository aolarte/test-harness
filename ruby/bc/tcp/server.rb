require 'socket'


class Server

  def run
    server = TCPServer.new(9292)
    loop do
      puts "Waiting for connection..."
      connection = server.accept
      puts "Got " + connection.gets

      load 'responder.rb' #this will reload the code on every iteration

      Responder.new().respond(connection)
      puts "New connection received."
      connection.close
    end
  end
end



Server.new().run