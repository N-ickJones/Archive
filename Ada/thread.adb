
--Purpose: TO Dev Threads for Client

with Ada.Text_IO; use Ada.Text_IO;
with GNAT.Sockets; use GNAT.Sockets;
with Ada.Streams; use Ada.Streams;

procedure thread is


	package intIO is new Integer_IO(Integer); use intIO;

	task Client is
		entry Connect;
		entry Messages;
		entry Disconnect;
	end Client;

	task body Client is
		Message: String(1..512);
		Length: Integer;
		Client  : Socket_Type;
  	Address : Sock_Addr_Type;
  	Channel : Stream_Access;

	begin
		accept Connect;
		Initialize;
  	Create_Socket(Client);
  	Address.Addr := Inet_Addr("127.0.0.1");
  	Address.Port := 4000;
  	Connect_Socket(Client, Address);
  	Channel := Stream(Client);
		put("Client has Connected!");new_line;
		accept Messages;
		loop
			get_line(Message, Length);
			if Message(Message'First..Message'First+Length-1) = "exit" then
				exit;
			end if;
			put(Message(Message'First..Message'First+Length-1));new_line;
		end loop;

		accept Disconnect;
		Close_Socket (Client);
		put("Client Disconnected!");new_line;
	end Client;

	task Server is
		entry Connect;
		entry AllowClients;
		entry Disconnect;
	end Server;

	task body Server is
		Message: String(1..512);
		Length: Integer;

	begin
		accept Connect;
		put("Server is Online!");new_line;

		accept AllowClients;
		loop
			get_line(Message, Length);
			if Message(Message'First..Message'First+Length-1) = "exit" then
				exit;
			end if;
			put(Message(Message'First..Message'First+Length-1));new_line;
		end loop;

		accept Disconnect;
		put("Server Disconnected!");new_line;
	end Server;

	procedure proc1 is
	begin
		Client.Connect;
		delay 2.0;
		Client.Messages;
		delay 5.0;
		Client.Disconnect;
	end proc1;

	procedure proc2 is
	begin
		Server.Connect;
		delay 2.0;
		Server.AllowClients;
		delay 5.0;
		Server.Disconnect;
	end proc2;

begin
	proc1;
	delay 5.0;
	proc2;

end thread;
