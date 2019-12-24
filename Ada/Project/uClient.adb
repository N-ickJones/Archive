-- Author:  Nicholas Jones
-- Purpose: Connect and communicate with server
-- Date:    04/29/2019
-- Version: 1.0

with Ada.Text_IO;use Ada.Text_IO;
with GNAT.Sockets;use GNAT.Sockets;
with Ada.Streams;use type Ada.Streams.Stream_Element_Count;

procedure uClient is
  Last: Integer;
  Client_Message: String(1..256);
  Client_Socket : Socket_Type;
  Address: Sock_Addr_Type;
  Channel: Stream_Access;

  Task Client is
    entry Setup( Client_Socket: in out Socket_Type;
                 Address: in out Sock_Addr_Type;
                 Channel: in out Stream_Access);
    entry Check_Status;
    entry Set_Username;
    entry Chat;
  end Client;

  Task body Client is
  begin
    accept Setup( Client_Socket: in out Socket_Type;
                  Address: in out Sock_Addr_Type;
                  Channel: in out Stream_Access) do
      Create_Socket(Client_Socket);
      Address.Addr := Inet_Addr("127.0.0.1");
      Address.Port := 4000;
      Connect_Socket(Client_Socket, Address);
      Channel := Stream(Client_Socket);
    end Setup;

    accept Check_Status do
      declare
        Server_Message: String := String'Input(Channel);
        Length: Integer := Integer'Input(Channel);
      begin
        Put_Line(Server_Message(1..Length));
      end;
    end Check_Status;

    accept Set_Username do
      declare
        Server_Message: String := String'Input(Channel);
        Length: Integer := Integer'Input(Channel);
      begin
        Put_Line(Server_Message(1..Length));
        get_line(Client_Message, Last);
        String'Output (Channel, Client_Message(1..Last));
        Integer'Output(Channel, Last);
      end;
    end Set_Username;

    accept Chat do
      loop
        get_line(Client_Message, Last);
        String'Output (Channel, Client_Message(1..Last));
        Integer'Output(Channel, Last);
    	end loop;
    end Chat;
    Close_Socket(Client_Socket);
  end Client;

  Task Reciever is
    entry Start;
  End Reciever;

  Task body Reciever is
    Connected: Boolean := True;
  Begin
    accept Start;
    loop
      declare
        Server_Message: String := String'Input(Channel);
        Length: Integer := Integer'Input(Channel);
      begin
        if Connected = True then
          Put_Line(Server_Message(1..Length));
        else
          Exit;
        end if;
      end;
    end loop;
  end Reciever;

begin
  Initialize;
  Client.Setup(Client_Socket, Address, Channel);
  Client.Check_Status;
  Client.Set_Username;
  Reciever.Start;
  Client.Chat;
  exception
    when End_Error => Put("Disconnected From Server");
end uClient;
