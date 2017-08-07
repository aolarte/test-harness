class Responder
  def respond(connection)
    connection.puts("HTTP/1.0 200 OK\r\n")
    connection.puts("Content-type: text/html\r\n")
    connection.puts("\r\n")
    connection.puts("<html><body><p>Hello World!</p></body></html>")
  end
end