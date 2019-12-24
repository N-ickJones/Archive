-- Author:  Nicholas Jones
-- Purpose: To Establish Multi-Client to Server Connection using Sockets
-- Date:    04/29/2019
-- Version: 1.0

with Ada.Text_IO; use Ada.Text_IO;
with Ada.IO_Exceptions;
with GNAT.Sockets; use GNAT.Sockets;

procedure Chat_Server is

	type MultiCast is record
		Connection: Socket_Type;
		Client:     Sock_Addr_Type;
		Channel:    Stream_Access;
	end record;

  Client_Count : constant := 3;
  type Client_List is array (1..Client_Count) of Integer;
	type Client_Group is array(1..Client_Count) of MultiCast;
  subtype Stack_Count is Integer range 0..Client_Count;
	Client_Names: array(1..Client_Count) of String(1..8);
	Client_Num: Integer := 1;

  protected type Client_Stack is
		procedure Init;
    procedure Push (aClient: in Integer);
    entry Pop(aClient: out Integer);
  private
    Stack: Client_List;
    Top: Stack_Count := 0;
  end Client_Stack;

  protected body Client_Stack is
		procedure Init is
    begin
      for i in 1..Client_Count loop
        Push(i);
      end loop;
    end Init;

    procedure Push (aClient: Integer) is
    begin
      Top := Top + 1;
      Stack(Top) := aClient;
    end;

    entry Pop(aClient : out Integer) when Top /= 0 is
    begin
      aClient := Stack(Top);
      Top := Top - 1;
    end;

  end Client_Stack;

  Client_Nodes: Client_Stack;

	protected type Group_Stack is
    procedure Push( Connection: Socket_Type;
										Client:     Sock_Addr_Type;
                		Channel:    Stream_Access);
		procedure Send_All( Client:    Sock_Addr_Type;
		 									  Message:   String;
												Length:    Integer;
												Chat_Name: String);
    entry Pop(aClient: out MultiCast);
  private
    Stack: Client_Group;
    Top: Stack_Count := 0;
  end Group_Stack;

  protected body Group_Stack is

    procedure Push( Connection: Socket_Type;
										Client:     Sock_Addr_Type;
                		Channel:    Stream_Access) is
    begin
      Top := Top + 1;
      Stack(Top) := MultiCast'(Connection, Client, Channel);
    end Push;

		procedure Send_All( Client:    Sock_Addr_Type;
												Message:   String;
												Length:    Integer;
												Chat_Name: String) is
		Begin
			for i in 1..Top loop
				if Client /= Stack(i).Client then
					String'Output(Stack(i).Channel, "[" & Chat_Name & "] " & Message);
					Integer'Output(Stack(i).Channel, Length + Chat_Name'Length + 3);
				end if;
			end loop;
		end Send_All;

    entry Pop(aClient: out MultiCast) when Top /= 0 is
    begin
      aClient := Stack(Top);
      Top := Top -  1;
    end Pop;
  end Group_Stack;

	Group: Group_Stack;

  task type This_Socket is
    entry Setup( Connection: Socket_Type;
		 						 Client: Sock_Addr_Type;
                 Channel: Stream_Access;
								 Task_Index: Integer);
    entry Connect;
  end This_Socket;

  task body This_Socket is
    This_Connection: Socket_Type;
    This_Client: Sock_Addr_Type;
    Stream: Stream_Access;
    This_Index: Integer;
		This_Client_Num: Integer := Client_Num;
  begin
		Client_Num := Client_Num + 1;
    loop
      accept Setup(Connection : Socket_Type; Client  : Sock_Addr_Type;
                   Channel : Stream_Access; Task_Index: Integer) do
        This_Connection := Connection;
        This_Client     := Client;
        Stream          := Channel;
        This_Index      := Task_Index;
      end;

      accept Connect;
			declare
				Exit_Status: Boolean := False;
      begin
				-- Begin Connection
        loop
					String'Output(Stream, "You have connected Successfully.");
					Integer'Output(Stream, 31);
					String'Output(Stream, "Enter a Username");
					Integer'Output(Stream, 16);
					--Accept Username
					declare
					  Message: String := String'Input(Stream);
					  Length: Integer := Integer'Input(Stream);
					  Str: String(1..8);
					begin
					  Str(1..Length) := Message;
					  Client_Names(This_Client_Num) := Str;
					  String'Output(Stream, "Username is now " & Message);
					  Integer'Output(Stream, 16+Length);
					end;
					--Begin Recieving Messages
					loop
					 	declare
					 		Message: String := String'Input(Stream);
					    Length: Integer := Integer'Input(Stream);
					  begin
							Group.Send_All(This_Client, Message, Length, Client_Names(This_Client_Num));
					 		put_line(Message & " [" & Client_Names(This_Client_Num) & "]");
								if Message = "/quit" then
					     		Exit_Status := True;
									exit;
								end if;
					  end;
						if Exit_Status = True then
							exit;
						end if;
					end loop;
					-- End Recieving Messages
					if Exit_Status = True then
						exit;
					end if;
        end loop;
				--End Connection
      end;
        Close_Socket(This_Connection);
        Client_Nodes.Push(This_Index);
      end loop;
  end This_Socket;

  task type Build_Server(This_Port: Port_Type) is
    entry Listen;
  end Build_Server;

  task body Build_Server is
   	Receiver: Socket_Type;
   	Connection: Socket_Type;
   	Client: Sock_Addr_Type;
   	Channel: Stream_Access;
   	Processor: array (1..Client_Count) of This_Socket;
   	aTask: Integer;

   begin
      accept Listen;
      Create_Socket (Socket => Receiver);
      Set_Socket_Option
        (Socket => Receiver,
         Level  => Socket_Level,
         Option => (Name => Reuse_Address, Enabled => True));
      Bind_Socket
        (Socket  => Receiver,
         Address => (Family => Family_Inet,
                     Addr   => Inet_Addr ("127.0.0.1"),
                     Port   => This_Port));

      Listen_Socket (Socket => Receiver);
      Client_Nodes.Init;
			loop
        Accept_Socket
          (Server  => Receiver,
           Socket  => Connection,
           Address => Client);
        Ada.Text_IO.Put_Line (Image(Client) & " Has Connected!");
        Channel := Stream(Connection);
        Client_Nodes.Pop(aTask);
				Group.Push(Connection, Client, Channel);
        Processor(aTask).Setup(Connection, Client, Channel, aTask);
        Processor(aTask).Connect;
    	end loop;
  end Build_Server;

AServer : Build_Server(This_Port => 4000);

begin
  AServer.Listen;
end Chat_Server;
